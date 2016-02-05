package com.paf.pageobjects;

import com.google.common.base.Function;
import com.paf.setup.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

import static com.paf.setup.SeleniumDriver.getDriver;

/**
 * Created by devnatar on 02/04/2016.
 */
public abstract class PafPage<T> {
    private static final String BASE_URL = "https://www.paf.com";
    private static final int LOAD_TIMEOUT = 30;
    private static final int REFRESH_RATE = 2;

    public T openPage(Class<T> clazz) {
        T page = PageFactory.initElements(getDriver(), clazz);
        getDriver().get(BASE_URL + getPageUrl());
        ExpectedCondition pageLoadCondition = ((PafPage) page).getPageLoadCondition();
        waitForPageToLoad(pageLoadCondition);
        return page;
    }


    public void waitForPageToLoad(ExpectedCondition pageLoadCondition) {

        Wait wait = new FluentWait(getDriver())
                .withTimeout(LOAD_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(REFRESH_RATE, TimeUnit.SECONDS);

         wait.until(pageLoadCondition);
    }

    public WebElement fluentWait(final By locator) {
        SeleniumDriver.getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(SeleniumDriver.getDriver())
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement ele = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });

        return ele;
    }

    ;

    /**
     * Provides condition when page can be considered as fully loaded.
     *
     * @return
     */
    protected abstract ExpectedCondition getPageLoadCondition();

    /**
     * Provides page relative URL/
     *
     * @return
     */
    public abstract String getPageUrl();

}

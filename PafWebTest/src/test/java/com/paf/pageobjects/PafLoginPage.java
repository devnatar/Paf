package com.paf.pageobjects;

import com.paf.setup.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by devnatar on 02/04/2016.
 */
public class PafLoginPage extends PafPage<PafLoginPage> {

    @FindBy(css = "#accountButton-notLoggedIn")
    WebElement accountField;

    @FindBy(id = "username")
    WebElement loginField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "LoginButton")
    WebElement commitButton;

    @FindBy(id = "invalid_username_password")
    WebElement errorBox;

    @FindBy(id = "username_is_empty")
    WebElement usernameEmpty;

    @FindBy(id = "password_is_empty")
    WebElement passwordEmpty;

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(accountField);
    }

    @Override
    public String getPageUrl() {
        return "?locale=fi_FI";
    }


    public void login(String login, String password) {

        fluentWait(By.cssSelector("#accountButton-notLoggedIn"));

        accountField.click();
        loginField.sendKeys(login);
        passwordField.sendKeys(password);

        commitButton.click();


    }

    public boolean isLoginError() {

        fluentWait(By.id("invalid_username_password"));

        return errorBox.isEnabled();
    }

    public boolean isUsernameMissingError() {

        fluentWait(By.id("username_is_empty"));

        return usernameEmpty.isEnabled();
    }

    public boolean isPasswordMissingError() {

        fluentWait(By.id("password_is_empty"));

        return passwordEmpty.isEnabled();
    }

    public boolean isLoginSuccess() {

        fluentWait(By.cssSelector("#span-timeDisplay"));
        return SeleniumDriver.getDriver().getTitle().toString().contains("My Paf");
    }


    public String getErrorMessage() {

        return errorBox.getText();
    }


}

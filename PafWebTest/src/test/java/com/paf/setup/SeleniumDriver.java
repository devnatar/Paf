package com.paf.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by devnatar on 02/04/2016.
 */

public class SeleniumDriver {

    static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {

            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            driver = new ChromeDriver();

        }
        return driver;
    }


}

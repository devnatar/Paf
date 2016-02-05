package com.paf.test;

import com.paf.pageobjects.PafHomePage;
import com.paf.pageobjects.PafLoginPage;
import org.junit.AfterClass;
import org.junit.Test;

import static com.paf.setup.SeleniumDriver.getDriver;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by devnatar on 02/04/2016.
 */
public class PafLoginTest {


    @AfterClass
    public static void tearDown() {
        getDriver().close();
    }

    @Test
    public void successful_login_with_credentials() {
        PafLoginPage loginPage = new PafHomePage().open().goToLoginPage();
        loginPage.login("Devasad", "F+");
        assertThat(loginPage.isLoginSuccess()).isTrue();
    }

    @Test
    public void should_not_login_with_wrong_credentials() {
        PafLoginPage loginPage = new PafHomePage().open().goToLoginPage();
        loginPage.login("devnatar", "finland12");
        assertThat(loginPage.isLoginError()).isTrue();
    }

    @Test
    public void should_not_login_only_with_username_credentials() {
        PafLoginPage loginPage = new PafHomePage().open().goToLoginPage();
        loginPage.login("", "sds");
        assertThat(loginPage.isUsernameMissingError()).isTrue();
    }

    @Test
    public void should_not_login_only_with_password_credentials() {
        PafLoginPage loginPage = new PafHomePage().open().goToLoginPage();
        loginPage.login("devnatar", "");
        assertThat(loginPage.isPasswordMissingError()).isTrue();
    }

    @Test
    public void should_not_login_without_any_credentials() {
        PafLoginPage loginPage = new PafHomePage().open().goToLoginPage();
        loginPage.login("", "");
        assertThat(loginPage.isUsernameMissingError()).isTrue();
    }

}

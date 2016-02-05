package com.paf.pageobjects;


import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by devnatar on 02/04/2016.
 */
public class PafHomePage extends PafPage<PafHomePage> {


    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.titleContains("Peliautomaatti, bingo, kasino, pokeri ja vedonlyönti netissä - Play among friends - Paf.com");
    }

    @Override
    public String getPageUrl() {
        return "";
    }

    public PafLoginPage goToLoginPage() {

        return new PafLoginPage().openPage(PafLoginPage.class);
    }

    public PafHomePage open() {
        return new PafHomePage().openPage(PafHomePage.class);
    }
}

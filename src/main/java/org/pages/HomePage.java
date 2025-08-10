package org.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderElements;

public class HomePage extends ParentPage{
    private Logger logger = LogManager.getLogger(getClass());

    @FindBy(xpath = "//a[@href='/en-us/t/halo-collection/']")
    private WebElement buttonShopNow;

    @FindBy(xpath = "//img[@alt='close icon']")
    private WebElement closeCookiesBanner;

    @FindBy(xpath = "//button[@id='my-account-switcher']")
    private WebElement buttonMyAccount;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "en-us/";
    }

    public HomePage openHomePageAndCloseCookiesBanner() {
        webDriver.get(baseURL);
        logger.info("Home page is opened");
        clickOnElement(closeCookiesBanner);
        return this;
    }

    public HeaderElements getHeaderElements() {
        return new HeaderElements(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        checkUrl();
        isElementDisplayed(buttonMyAccount);
        getHeaderElements().checkAllHeaderElementsVisible();
        return this;
    }

    public HomePage buttonMyAccountIsVisible() {
        isElementDisplayed(buttonMyAccount);
        return this;
    }
}

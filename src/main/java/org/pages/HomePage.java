package org.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.DiscountPopUp;
import org.pages.elements.HeaderElements;

import java.util.List;


public class HomePage extends ParentPage{
    private Logger logger = LogManager.getLogger(getClass());

    @FindBy(xpath = "//a[@href='/en-us/t/halo-collection/']")
    private WebElement buttonShopNow;

    @FindBy(xpath = "//img[@alt='close icon']")
    private WebElement closeCookiesBanner;

    @FindBy (xpath = "//div[@data-testid='hero-video-ctp-overlay']")
    private WebElement haloVideo;

    @FindBy (xpath = "//p[text()='UA HALO COLLECTION']")
    private WebElement haloTitleHomePage;

    @FindBy (xpath = "//a[@href='/en-us/t/halo-collection/']")
    private WebElement shopNowHaloButton;

    @FindBy (xpath = "//p[text()='UA Halo Collection']")
    private WebElement secondHaloTitile;

    @FindBy (xpath = "(//div[contains(@class,'swiper-wrapper')])[1]//div[contains(@class,'swiper-slide swiper-slide-visible')]")
    private List<WebElement> sliderTile;


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
    public DiscountPopUp getDiscountPopUp() {
        return new DiscountPopUp(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        checkUrl();
        getHeaderElements().checkAllHeaderElementsVisible();
        return this;
    }

    public HomePage buttonMyAccountIsVisible() {
        isElementDisplayed(getHeaderElements().buttonMyAccount);
        return this;
    }

    public HomePage buttonLoginIsVisible() {
        isElementDisplayed(getHeaderElements().buttonLogin);
        return this;
    }

    public HomePage logOutFromAccount() {
        getHeaderElements().clickOnButtonMyAccount().clickOnButtonLogOut();
        return this;
    }

    public HomePage checkNewCollectionElementsIsVisible(){
        checkIsElementDisplayed(haloVideo);
        checkTextInElement(haloTitleHomePage, "UA HALO COLLECTION");
        checkIsElementDisplayed(shopNowHaloButton);
        checkTextInElement(secondHaloTitile, "UA Halo Collection");
        for (WebElement el : sliderTile){
            checkIsElementDisplayed(el);
        }

        return this;
    }

    public HaloPage clickOnShopNowButton() {
        clickOnElement(shopNowHaloButton);
        return new HaloPage(webDriver);
    }
}

package org.pages;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.DiscountPopUp;
import org.pages.elements.HeaderElements;
import org.utils.Utils_Custom;

import java.util.List;

import static org.utils.Utils_Custom.waitABit;


public class MensPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//li[text()='Men']")
    private WebElement mensHeaderOne;

    @FindBy(xpath = "//h1[text()=\"Men's\"]")
    private WebElement mensHeaderTwo;

    @FindBy(xpath ="//div[contains(@class,'swiper-slide swiper-slide-visible swiper-slide-fully-visible')]")
    private List<WebElement> productCarouselItems;

    @FindBy(xpath = "//a[@class='ProductTile_product-item-link__tSc19']")
    private List<WebElement> allProductLinks;

    @FindBy(xpath = "//span[text()='Now Trending']")
    private WebElement sortDropdown;

    @FindBy(xpath = "//a[text()='Next']")
    private WebElement buttonNext;

    public MensPage(WebDriver webDriver) {
        super(webDriver);
    }
    public HeaderElements getHeaderElements() {
        return new HeaderElements(webDriver);
    }

    public DiscountPopUp getDiscountPopUp() {
        return new DiscountPopUp(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "c/mens/";
    }

    public MensPage checkIsRedirectToMensPage(){
        waitABit(1);
        checkUrl();
        checkTextInElement(mensHeaderOne, "Men");
        checkTextInElement(mensHeaderTwo, "Men's");
        for (WebElement element : productCarouselItems) {

            if (!element.isDisplayed()) {
                System.out.println("Element not displayed: " + element.getText());
            }
            Assert.assertTrue("Element is not displayed: " + element.getText(),
                    element.isDisplayed());
            logger.info(element.getText() + " is displayed");
        }
        checkTextInElement(sortDropdown, "Now Trending");
        return this;

    }

    public MensPage verifyDiscountPopUpOnMensPage() {
        boolean isPopUpVisible = false;
        int currnetPage = 1;
        while (!isPopUpVisible){
            waitABit(3);
            if (getDiscountPopUp().isDiscountPopUpVisible()){
                checkTextInElement(getDiscountPopUp().discountPopUp, "Get 15% Off^");
                isPopUpVisible = true;
                getDiscountPopUp().acceptDiscountPopUp();
                checkTextInElement(getDiscountPopUp().successTextForPopUpDiscount, "Check Your Texts");
                clickOnElement(getDiscountPopUp().buttonContinueShopping);
                checkUrlWithParam(currnetPage);

                break;
            }
            currnetPage++;
            scrollToElement(buttonNext);
            clickOnElement(buttonNext);
        }

        return this;
    }
}


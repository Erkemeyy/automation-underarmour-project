package org.pages;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.DiscountPopUp;
import org.pages.elements.HeaderElements;

import java.util.List;

import static org.utils.Utils_Custom.waitABit;


public class MensPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//li[text()='Men']")
    private WebElement mensPageBreadcrumbs;

    @FindBy(xpath = "//h1[text()=\"Men's\"]")
    private WebElement mensPageHeader;

    @FindBy(xpath ="//div[contains(@class,'swiper-slide swiper-slide-visible swiper-slide-fully-visible')]")
    private List<WebElement> productCarouselItems;

    @FindBy(xpath = "//a[@class='ProductTile_product-item-link__tSc19']")
    private List<WebElement> allProductLinks;

    @FindBy(xpath = "//span[text()='Now Trending']")
    public WebElement sortDropdown;

    @FindBy(xpath = "//a[text()='Next']")
    private WebElement buttonNext;

    @FindBy(xpath = "//a[@class='ProductTile_product-item-link__tSc19']")
    private List<WebElement> productTitles;

    @FindBy(xpath = "//section[@id='category-list']//a[@class='text-body font-semibold']")
    public List<WebElement> mensPageCategoryList;

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
        return "en-us/c/mens/";
    }

    public MensPage checkIsRedirectToMensPage(){
        getDiscountPopUp().waitAndCloseDiscountPopUpAfterLogout();
        checkUrl();
        checkIsElementDisplayed(mensPageBreadcrumbs);
        checkTextInElement(mensPageHeader, "Men's");
        for (WebElement element : productCarouselItems) {

            if (!element.isDisplayed()) {
                logger.info("Element not displayed: " + element.getText());
            }
            Assert.assertTrue("Element is not displayed: " + element.getText(),
                    element.isDisplayed());
            logger.info(element.getText() + " is displayed");
        }
        checkTextInElement(sortDropdown, "Now Trending");
        checkProductsArePresent(productTitles);
        checkCategoryList(mensPageCategoryList);
        return this;

    }

    public MensPage applyToDiscountPopUpOnMensPage() {
        boolean isPopUpVisible = false;
        int currnetPage = 1;
        webDriverWait15.until(ExpectedConditions.visibilityOf(getDiscountPopUp().discountPopUp));
        logger.info("Discount popup appeared.");
        while (!isPopUpVisible){
            waitABit(3);
            if (getDiscountPopUp().isDiscountPopUpVisible()){
                checkTextInElement(getDiscountPopUp().discountPopUp, "Get 15% Off^");
                isPopUpVisible = true;
                getDiscountPopUp().acceptDiscountPopUp();
                checkTextInElement(getDiscountPopUp().successTextForPopUpDiscount, "Check Your Texts");
                webDriverWait10.until(ExpectedConditions.elementToBeClickable(getDiscountPopUp().buttonContinueShopping));
                clickOnElement(getDiscountPopUp().buttonContinueShopping);
                checkUrl();

                break;
            }
            currnetPage++;
            scrollToElement(buttonNext);
        }

        return this;
    }
}


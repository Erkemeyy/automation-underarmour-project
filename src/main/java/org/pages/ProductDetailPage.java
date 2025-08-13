package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.AddToBagPopUp;
import org.pages.elements.DiscountPopUp;
import org.pages.elements.HeaderElements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.utils.Utils_Custom.waitABit;

public class ProductDetailPage extends ParentPage{

    private Logger logger = Logger.getLogger(getClass());

    @FindBy (xpath="//h1[text()='UA Expanse']")
    private WebElement productName;

    @FindBy (xpath="//div[@class='PriceDisplay_price-display__PU2nD PriceDisplay_enhanced-price-display__wC9j3']")
    private WebElement shortsPrice;

    @FindBy (xpath = "//div[@id='swatches-container']")
    private WebElement shortsColourSwitcher;

    @FindBy (xpath = "//div[@class='SizeSwatchesSection_size-swatches__WT8Z_ false']")
    private WebElement shortSizeSwitcher;

    @FindBy (xpath = "//div[@class='bfx-remove-element ShipOrPickup_ship-or-pickup-wrapper-root__Z4tbu']")
    private WebElement deliveryChoice;

    @FindBy (xpath = "//div[@id='add-to-bag-container']")
    private WebElement addToBagButton;

    @FindBy (xpath = "//div[@class='VariantAtcPanel_product__action-buttons__t281_']//button[@class='Button_btn__8I_Ow Button_btn__secondary__Qhg0F  ProductFavoriteButtonRedeign_favorite__wPKVF']")
    private WebElement buttonFavorite;

    @FindBy (xpath = "//div[@class='VariantAtcPanel_product__action-buttons__t281_']//button[@class='Button_btn__8I_Ow Button_btn__secondary__Qhg0F  ProductShareButton_share__aOvLD  undefined']")
    private WebElement buttonShare;


    public ProductDetailPage(WebDriver webDrive){
        super(webDrive);
    }

    public AddToBagPopUp getAddToBagPopUp(){
        return new AddToBagPopUp(webDriver);
    }

    public MensSwimwearPage getMensSwimwearPage(){
        return new MensSwimwearPage(webDriver);
    }

    public DiscountPopUp getDiscountPopUp(){
        return new DiscountPopUp(webDriver);
    }

    @Override
    public String getRelativeURL() {
        return "en-us/p/swimwear/ua_expanse_mens_2-in-1_boardshorts/1370030.html?dwvar_1370030_color=001";
    }

    public ProductDetailPage checkIsRedirectToProductDetailPage(){
        webDriverWait10.until(ExpectedConditions.elementToBeClickable(addToBagButton));
        String nameOfProduct = String.valueOf(getMensSwimwearPage().productsTitleOneMensPage.get(0));
        String onlyNameInProduct = keepBeforeLF(nameOfProduct);
        checkUrl();
       // getDiscountPopUp().waitAndCloseDiscountPopUpAfterLogout();
        checkTextInElementWithMultipleRaws(productName,getMensSwimwearPage().productsTitleOneMensPage.get(0).getText());
        checkIsElementDisplayed(shortsPrice);
        checkIsElementDisplayed(shortsColourSwitcher);
        checkIsElementDisplayed(shortSizeSwitcher);
        checkIsElementDisplayed(deliveryChoice);
        checkIsElementDisplayed(addToBagButton);
        checkIsElementDisplayed(buttonFavorite);
        checkIsElementDisplayed(buttonShare);
        return this;

    }

    public static String keepBeforeLF(String s) {
        if (s == null) return "";
        int i = s.indexOf('\n');
        return (i >= 0 ? s.substring(0, i) : s).trim();
    }

    public ProductDetailPage clickOnAddToBagButton() {
        clickOnElement(addToBagButton);
        waitABit(10);
        return this;
    }
}

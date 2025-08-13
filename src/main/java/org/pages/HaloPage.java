package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.DiscountPopUp;
import org.pages.elements.HeaderElements;

import java.util.List;

import static org.utils.Utils_Custom.waitABit;

public class HaloPage extends ParentPage{

    @FindBy (xpath = "//a[contains(@class,'ProductTile_product-item-link')]")
    private List<WebElement> haloProducts;

    public HaloPage(WebDriver webdriver){
        super(webdriver);
    }

    @Override
    public String getRelativeURL(){
        return "en-us/t/halo-collection/";
    }

    public HeaderElements getHeaderElements() {
        return new HeaderElements(webDriver);
    }

    public DiscountPopUp getDiscountPopUp() {
        return new DiscountPopUp(webDriver);
    }

    public HaloPage checkIsRedirecktToHaloPage() {
        getDiscountPopUp().waitAndCloseDiscountPopUpAfterLogout();
        checkUrl();
        WebElement pageTitle = webDriverWait10.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[text()='UA HALO']")
        ));
        checkIsElementDisplayed(pageTitle);
        checkTextInElement(pageTitle, "UA HALO");
        checkProductsOnPage(haloProducts);
        waitABit(3);
        return this;
    }
}

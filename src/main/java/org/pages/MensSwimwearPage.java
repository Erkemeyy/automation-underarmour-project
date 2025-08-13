package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.DiscountPopUp;

import java.util.List;

import static org.utils.Utils_Custom.waitABit;

public class MensSwimwearPage extends ParentPage{
    @FindBy (xpath = "(//a[text()='Swimwear' and @href='/en-us/c/mens/clothing/swimsuits/'])[last()]")
    private WebElement buttonSwimwear;

    @FindBy (xpath = "//ol/li[1]/a")
    private WebElement menBreadcrumb;

    @FindBy (xpath = "//ol/li[@aria-current='page']")
    private WebElement swimwearBreadcrumb;

    @FindBy (xpath = "//h1[text()=\"Men's Swimsuits\"]")
    private WebElement mensSwimwearPageHeader;

    @FindBy (xpath = "//a[contains(@class,'ProductTile_product-item-link__tSc19')]")
    private List<WebElement> productsTitleOneMensPage;


    public MensSwimwearPage(WebDriver webDriver){
        super(webDriver);
    }

    @Override
    public String getRelativeURL() {
        return "en-us/c/mens/clothing/swimsuits/";
    }

    public MensPage getMensPage(){
        return new MensPage(webDriver);
    }
    public DiscountPopUp getDiscountPopUp() {
        return new DiscountPopUp(webDriver);
    }

    public MensSwimwearPage clickOnMensSwimwearPage(){
        clickOnElement(buttonSwimwear);
        return this;
    }


    public MensSwimwearPage checkIsRedirectToMensSwimwearPage() {
        waitABit(1);
        checkUrl();
        isBreadcrumbOnPageDisplayed(menBreadcrumb, swimwearBreadcrumb);
        checkTextInElement(mensSwimwearPageHeader, "Men's Swimsuits");
        checkTextInElement(getMensPage().sortDropdown, "Now Trending");
        checkCategoryList(getMensPage().mensPageCategoryList);
        checkNumberOfProductsOnPage(productsTitleOneMensPage);
        getDiscountPopUp().waitAndCloseDiscountPopUpAfterLogout();
        return this;
    }

    public ProductDetailPage clickOnFirstProductOnThePage() {
        clickOnElement(productsTitleOneMensPage.get(0));
        return new ProductDetailPage(webDriver);
    }
}

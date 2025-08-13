package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderElements;

import java.util.List;

public class CartPage extends ParentPage{

    @FindBy (xpath = "//span[@class='Cart_cart-title__DWz30']")
    private WebElement cartTitle;

    @FindBy (xpath = "//span[@class='Cart_number-of-items__pj2yl']")
    private WebElement numberOfItems;

    @FindBy (xpath = "//a[@class='CartItem_name__17jrI bfx-product-name']")
    private WebElement productTitleOnCartPage;

    @FindBy (xpath = "//button[@name='quantity-combobox']")
    private WebElement quantityButton;

    @FindBy (xpath = "//button[@class='Button_btn__8I_Ow Button_btn__text__Tnlwv  ']")
    private List<WebElement> buttonManageProductInCart;

    public CartPage(WebDriver webDriver){
        super(webDriver);
    }

    @Override
    public String getRelativeURL(){
        return "en-us/cart/";
    }

    public HeaderElements getHeaderElements() {
        return new HeaderElements(webDriver);
    }


    public CartPage checkIsCartPageAndThereIsOneProduct() {
        checkUrl();
        checkTextInElement(cartTitle, "Your Bag");
        checkTextInElementWithMultipleRaws(numberOfItems, "1 Item");
        checkIsElementDisplayed(quantityButton);
        checkTextInElement(productTitleOnCartPage, "UA Expanse");
        for (WebElement el : buttonManageProductInCart){
            checkIsElementDisplayed(el);
        }
        return this;
    }
}

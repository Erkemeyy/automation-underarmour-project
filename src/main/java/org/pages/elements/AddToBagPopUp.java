package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CartPage;
import org.pages.CommonActionsWithElements;
import static org.utils.Utils_Custom.waitABit;

public class AddToBagPopUp extends CommonActionsWithElements {
    @FindBy(xpath = "//h3[@class='AddToCartConfirmationContent_cart-modal-header__4qYDT']//span")
    private WebElement addToBagPopUpTitle;

    @FindBy (xpath = "//div[@class='AddToCartConfirmationContent_product-name__FhbDN']")
    private WebElement productTitleOnAddToBagPopUp;

    @FindBy (xpath = "//div[@class='AddToCartConfirmationContent_product-sub-header___eRNp']")
    private WebElement productSubTitleOnAddToBagPopUp;

    @FindBy (xpath = "//div[text()='Size: SM']")
    private WebElement productSizeOnAddToBagPopUp;

    @FindBy (xpath = "//div[@class='AddToCartConfirmationContent_grouped-data__EDKLG']/div[last()]")
    private WebElement productPriceOnAddToBagPopUp;

    @FindBy (xpath = "//a[@class='Button_btn__8I_Ow Button_btn__button-link__5Jt_5 Button_btn__primary__7tg_G undefined']")
    private WebElement viewBagButtonOnAddToBagPopUp;

    @FindBy (xpath = "//button[@data-testid='continue-shopping-btn']")
    private WebElement viewContinueShoppingOnAddToBagPopUp;


    public AddToBagPopUp(WebDriver webDriver){
        super(webDriver);
    }

    public AddToBagPopUp checkIsAddToBagPopUpAppeared() {
        checkTextInElementWithMultipleRaws(addToBagPopUpTitle, "Item Added To Bag");
        checkTextInElement(productTitleOnAddToBagPopUp, "UA Expanse");
        checkTextInElement(productSubTitleOnAddToBagPopUp, "Men's 2-in-1 5\" Boardshorts");
        checkIsElementDisplayed(productSizeOnAddToBagPopUp);
        checkIsElementDisplayed(viewBagButtonOnAddToBagPopUp);
        checkIsElementDisplayed(viewContinueShoppingOnAddToBagPopUp);
        //checkTextInElement(productPriceOnAddToBagPopUp, "$65"); тут можна було б зробити перевірку очікуваної ціни але ціна не знаходиться в середині дом тега і його нереально дістати

        return this;
    }

    public CartPage clickOnViewBagButton() {
        clickOnElement(viewBagButtonOnAddToBagPopUp);
        waitABit(10);
        return new CartPage(webDriver);
    }
}

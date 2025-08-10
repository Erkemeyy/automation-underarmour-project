package org.pages.elements;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.CommonActionsWithElements;
import org.pages.LoginPage;
import org.pages.MensPage;

import java.util.List;

import static org.utils.Utils_Custom.waitABit;

public class HeaderElements extends CommonActionsWithElements{
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//a[@href='/en-us/c/mens/']")
    private WebElement buttonMenSection;

    @FindBy(xpath = "//li[@class='Navigation_nav__list-item__8TwC0 undefined']")
    private List<WebElement> headerLinks;

    @FindBy(xpath = "//div[@id='nav-bar']//a[contains(@class,'logo')]")
    private WebElement UALogo;

    @FindBy(xpath="//button[@class='Button_btn__8I_Ow Button_btn__primary__7tg_G  HeaderUtility_header-account-link__UxE33']")
    public WebElement buttonLogin;

    @FindBy(xpath = "//button[@id='my-account-switcher']")
    public WebElement buttonMyAccount;

    @FindBy(xpath = "//div[contains(@class,'UserDropMenu')]//button[@data-testid='logout-button']")
    private WebElement buttonLogOut;

    @FindBy(xpath = "//div[contains(@class,'Header_desktop')]//input[@id='search-input']")
    private WebElement inputSearch;

    @FindBy(xpath = "//a[@class='Header_nav-icon-button__oA_oE Header_nav-icon-favorites__UpEd_']")
    private WebElement buttonLikedProducts;

    @FindBy(xpath = "//div[contains(@class,'Header_desktop')]//a[@id='shopping-bag']")
    private WebElement buttonShoppingBag;

    public HeaderElements(WebDriver webDriver) {
        super(webDriver);
    }

    public MensPage clickOnButtonMenSection() {
        clickOnElement(buttonMenSection);
        waitABit(1);
        return new MensPage(webDriver);
    }

    public HeaderElements checkAllHeaderElementsVisible() {
        checkIsElementDisplayed(UALogo);
        checkIsElementDisplayed(inputSearch);
        checkIsElementDisplayed(buttonLikedProducts);
        checkIsElementDisplayed(buttonShoppingBag);
        for (WebElement element : headerLinks) {

            if (!element.isDisplayed()) {
                System.out.println("Element not displayed: " + element.getText());
            }
            Assert.assertTrue("Element is not displayed: " + element.getText(),
                    element.isDisplayed());
            logger.info(element.getText() + " is displayed");
        }

        return this;
    }

    public LoginPage clickOnLoginButton() {
        clickOnElement(buttonLogin);
        return new LoginPage(webDriver);
    }

    public HeaderElements clickOnButtonMyAccount() {
        clickOnElement(buttonMyAccount);
        return this;
    }

    public HeaderElements clickOnButtonLogOut() {
        clickOnElement(buttonLogOut);
        return this;
    }
}

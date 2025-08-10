package org.pages.elements;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.CommonActionsWithElements;
import org.pages.HomePage;
import org.pages.MensPage;

import java.time.Duration;

import static org.utils.Utils_Custom.waitABit;

public class DiscountPopUp extends CommonActionsWithElements {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//h3[@class='Dialog_dialog-title--with-icon__6Af2k undefined']")
    public WebElement discountPopUp;

    @FindBy(xpath = "//span[text()='Check Your Texts']")
    public WebElement successTextForPopUpDiscount;

    @FindBy(xpath = "//button[@class='Button_btn__8I_Ow Button_btn__primary__7tg_G  SMSMarketingModal_continue__SWsdy']")
    public WebElement buttonContinueShopping;

    @FindBy(xpath = "//input[@id='sms-email']")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@id='sms-phone']")
    private WebElement inputPhone;

    @FindBy(xpath = "//button[text()='Subscribe Now']")
    private WebElement buttonSubscribeNow;


    @FindBy(xpath = "//button[@data-testid='dialog-close-button']")
    private WebElement buttonClosePopUp;

    public DiscountPopUp(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isDiscountPopUpVisible() {
        return isElementDisplayed(discountPopUp);
    }

    public DiscountPopUp enterTextIntoInputEmailPopUP(String email) {
        clearAndEnterTextToElement(inputEmail, email);
        return this;
    }

    public DiscountPopUp enterTextIntoInputPhone(String phone) {
        clearAndEnterTextToElement(inputPhone, phone);
        return this;
    }

    public void acceptDiscountPopUp() {
        enterTextIntoInputEmailPopUP("test_email@example.com");
        enterTextIntoInputPhone("6625639481");
        webDriverWait10.until(ExpectedConditions.visibilityOf(buttonSubscribeNow));
        clickOnElement(buttonSubscribeNow);
        logger.info("Discount pop-up was accepted");

    }

    public HomePage waitAndCloseDiscountPopUpAfterLogout() {
        logger.info("Waiting up to 15 seconds for discount popup...");

        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(discountPopUp));
            logger.info("Discount popup appeared.");

            // Чекаємо на кнопку закриття
            webDriverWait5.until(ExpectedConditions.elementToBeClickable(buttonClosePopUp));
            logger.info("Close button is clickable. Closing popup...");
            buttonClosePopUp.click();

            // Переконаємось, що попап зник
            webDriverWait5.until(ExpectedConditions.invisibilityOf(discountPopUp));
            logger.info("Discount popup closed.");

        } catch (TimeoutException e) {
            logger.warn("Discount popup did not appear after logout. Continuing test...");
        }
        return new HomePage(webDriver);
    }
}

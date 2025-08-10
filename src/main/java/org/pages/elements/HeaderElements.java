package org.pages.elements;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.MensPage;

import java.util.List;

public class HeaderElements extends CommonActionsWithElements{
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//a[@href='/en-us/c/mens/']")
    private WebElement buttonMenSection;

    @FindBy(xpath = "//li[@class='Navigation_nav__list-item__8TwC0 undefined']")
    private List<WebElement> headerLinks;

    @FindBy(xpath = "//div[@id='nav-bar']//a[contains(@class,'logo')]")
    private WebElement UALogo;

    public HeaderElements(WebDriver webDriver) {
        super(webDriver);
    }

    public MensPage clickOnButtonMenSection() {
        clickOnElement(buttonMenSection);
        return new MensPage(webDriver);
    }

    public HeaderElements checkAllHeaderElementsVisible() {
        checkIsElementDisplayed(UALogo);
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
}

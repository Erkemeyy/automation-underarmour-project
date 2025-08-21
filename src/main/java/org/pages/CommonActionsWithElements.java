package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.utils.ConfigProvider;


import java.time.Duration;
import java.util.List;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait5, webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait5 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_IMPLICIT_WAIT()));
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
    }

    protected void scrollToElement(WebElement webElement) {
        try{
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).perform();
            logger.info("Scrolled to element: " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10
                    .withMessage("Element is not clickable: " + webElement)
                    .until(ExpectedConditions.elementToBeClickable(webElement));
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(getElementName(webElement) + " element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement){
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Error while working with element: " + e.getMessage());
        Assert.fail("Error while working with element: " + e.getMessage());
    }

    protected void checkIsElementDisplayed(WebElement webElement) {
        Assert.assertTrue("Element is not displayed: " + webElement.getText(), isElementDisplayed(webElement));
    }

    protected void checkIsElementIsNotDisplayed(WebElement webElement) {
        Assert.assertFalse("Element is displayed, but it should not be", isElementDisplayed(webElement));
    }

    protected void checkTextInElement(WebElement webElement, String expectedText) {

        String actualText = webElement.getText();
        Assert.assertEquals("Text in element does not match expected text", expectedText, actualText);
        logger.info("Text in element matches expected text: " + expectedText);
    }


    protected void checkTextInElementWithMultipleRaws(WebElement webElement, String expectedText ){
        String actualText = webElement.getText();
        Assert.assertTrue("Element is not displayed: " + actualText, actualText.contains(expectedText));
        logger.info("The product name " + expectedText + " displayed as expected");

    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + " element is displayed");
            } else {
                logger.info(getElementName(webElement) + " element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not found, so it is not displayed");
            return false;
        }
    }

    protected void clearAndEnterTextToElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was entered in element "  + getElementName(webElement));
        } catch (Exception e) {
            logger.error("Error while working with element");
            Assert.fail("Error while working with element");
        }
    }

    public void checkProductsArePresent(List<WebElement> webElement) {
        Assert.assertTrue("No products found on Men's page", webElement.size() > 0);
        logger.info("There are " + (webElement.size() + 1)  + " products on this page");
    }

    public void checkCategoryList(List<WebElement> webElement){
        Assert.assertFalse("No elements found for locator: " + webElement, webElement.isEmpty());

        for (WebElement element : webElement) {
            Assert.assertTrue("Element is not displayed: " + element.getText(),
                    element.isDisplayed());
            logger.info(element.getText() + " is displayed");
        }
    }

    public void isBreadcrumbOnPageDisplayed(WebElement webElementOne, WebElement webElementTwo) {
        Assert.assertEquals("Men breadcrumb text mismatch", "Men", webElementOne.getText());
        Assert.assertEquals("Men breadcrumb link mismatch", "/en-us/c/mens/", webElementOne.getAttribute("href").replace("https://www.underarmour.com", ""));
        Assert.assertEquals("Swimwear breadcrumb text mismatch", "Swimwear", webElementTwo.getText());
        logger.info("Breadcrumbs are displayed");
    }


    public void checkNumberOfProductsOnPage(List<WebElement> webElement) {
        int nuberOfProductsOnpage = 0;
        int expectedProducts =  turnStringElementIntoNumber();
        for(WebElement el : webElement){
            nuberOfProductsOnpage++;
        }
        Assert.assertEquals("Number of products on page:" + nuberOfProductsOnpage + "don't match the element", nuberOfProductsOnpage,expectedProducts);
        logger.info("The number of products on page: " + nuberOfProductsOnpage + " is match expected result: " + expectedProducts);


    }

    private int turnStringElementIntoNumber(){

        String text = webDriver.findElement(By.xpath("//p[contains(@class,'text-body')]")).getText();
        String numberOnly = text.replaceAll("[^0-9]", "");
        int itemCount = Integer.parseInt(numberOnly);
        return itemCount;
    }

    public void checkProductsOnPage(List<WebElement> haloProducts) {
        Assert.assertFalse("No products found on the Halo Collection page.", haloProducts.isEmpty());
    }

}


package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.DiscountPopUp;
import org.pages.elements.HeaderElements;

import static org.data.TestData.INPUT_TEXT_FOR_SEARCH;
import static org.utils.Utils_Custom.waitABit;

public class SearchResultsPage extends ParentPage{

    @FindBy (xpath = "//div[text()='Search Results']")
    private WebElement searchResultsHeaderOne;

    @FindBy(xpath = "//h1[contains(text(),'gloves')]")
    private WebElement searchResultsHeaderTwo;

    public SearchResultsPage(WebDriver webDriver) {
        super(webDriver);
    }


    @Override
    protected String getRelativeURL() {
        return "en-us/search/?q=" + INPUT_TEXT_FOR_SEARCH;
    }

    public HeaderElements getHeaderElements() {
        return new HeaderElements(webDriver);
    }

    public DiscountPopUp getDiscountPopUp() {
        return new DiscountPopUp(webDriver);
    }

    public MensPage getMensPage() {
        return new MensPage(webDriver);
    }

    public SearchResultsPage checkIsRedirectToSearchReseltsPage(){
        checkUrl();
        getDiscountPopUp().waitAndCloseDiscountPopUpAfterLogout();
        checkTextInElement(searchResultsHeaderOne, "Search Results");
        checkTextInElement(searchResultsHeaderTwo, '"'+ INPUT_TEXT_FOR_SEARCH+'"');
        checkTextInElement(getMensPage().sortDropdown, "Now Trending");
        return this;
    }

    public HomePage clickOnElementHomePage() {
        waitABit(30);
        clickOnElement(getHeaderElements().UALogo);
        return new HomePage(webDriver);
    }
}

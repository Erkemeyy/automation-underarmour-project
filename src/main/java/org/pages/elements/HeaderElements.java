package org.pages.elements;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.*;

import java.util.List;

import static org.data.TestData.INPUT_TEXT_FOR_SEARCH;
import static org.utils.Utils_Custom.waitABit;

public class HeaderElements extends CommonActionsWithElements{
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//a[@href='/en-us/c/mens/']")
    private WebElement buttonMenSection;

    @FindBy(xpath = "//li[@class='Navigation_nav__list-item__8TwC0 undefined']")
    private List<WebElement> headerLinks;

    @FindBy(xpath = "//div[@id='nav-bar']//a[contains(@class,'logo')]")
    public WebElement UALogo;

    @FindBy(xpath="//button[@class='Button_btn__8I_Ow Button_btn__primary__7tg_G  HeaderUtility_header-account-link__UxE33']")
    public WebElement buttonLogin;

    @FindBy(xpath = "//button[@id='my-account-switcher']")
    public WebElement buttonMyAccount;

    @FindBy(xpath = "//div[contains(@class,'UserDropMenu')]//button[@data-testid='logout-button']")
    private WebElement buttonLogOut;

    @FindBy(xpath = "//div[contains(@class,'Header_desktop')]//input[@id='search-input']")
    public WebElement inputSearch;

    @FindBy(xpath = "//a[@class='Header_nav-icon-button__oA_oE Header_nav-icon-favorites__UpEd_']")
    private WebElement buttonLikedProducts;

    @FindBy(xpath = "//div[contains(@class,'Header_desktop')]//a[@id='shopping-bag']")
    private WebElement buttonShoppingBag;

    @FindBy(xpath = "//div[contains(@class, 'SearchBar_search-panel__WGh8A SearchBar_search-panel-open__lpUs3 SearchBar_zero-search-panel__mdXUh')]//h2[@class='SearchSuggestionResults_section-title__BlmuZ']")
    private WebElement searchSuggestionsHeader;

    @FindBy(xpath = "//div[contains(@class, 'SearchBar_search-panel__WGh8A SearchBar_search-panel-open__lpUs3 SearchBar_zero-search-panel__mdXUh')]//a[@data-testid='see-all-results']")
    private WebElement buttonSeeAllResults;

    @FindBy(xpath = "//div[contains(@class, 'SearchBar_search-panel__WGh8A SearchBar_search-panel-open__lpUs3 SearchBar_zero-search-panel__mdXUh')]//h2[contains(@class, 'ZeroStateContent_section-title__iOOjT')]")
    private WebElement headerTrendingSearch;

    @FindBy(xpath = "//div[contains(@class, 'SearchBar_search-panel__WGh8A SearchBar_search-panel-open__lpUs3 SearchBar_zero-search-panel__mdXUh')]//h2[text()='Popular products']")
    private WebElement headerPopularProducts;

    @FindBy(xpath = "//div[contains(@class, 'SearchBar_search-panel__WGh8A SearchBar_search-panel-open__lpUs3 SearchBar_zero-search-panel__mdXUh')]//a[@class='ZeroStateContent_search-link__AKmUx']")
    private List<WebElement> popularSections;

    @FindBy(xpath = "//div[contains(@class, 'SearchBar_search-panel__WGh8A SearchBar_search-panel-open__lpUs3 SearchBar_zero-search-panel__mdXUh')]//div[@class='ZeroStateContent_product-item__K9P6X']")
    private List<WebElement> popularProductsInSearchBar;

    @FindBy(xpath = "//div[contains(@class, 'SearchBar_search-flyout__K7Kiu')]//div[@class='SearchSuggestionResults_product-item__KQf_e']")
    public List<WebElement> suggestedProductsInSearchBar;



    public HeaderElements(WebDriver webDriver) {
        super(webDriver);
    }

    public DiscountPopUp getDiscountPopUp() {
        return new DiscountPopUp(webDriver);
    }

    public MensPage clickOnButtonMenSection() {
        waitABit(5);
        clickOnElement(buttonMenSection);
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

    public HeaderElements clickOnInputSearch() {
        clickOnElement(inputSearch);
        return this;
    }


    public HeaderElements searchForSpecificProduct() {
        clearAndEnterTextToElement(inputSearch, INPUT_TEXT_FOR_SEARCH);
        webDriverWait15.until(ExpectedConditions.elementToBeClickable(searchSuggestionsHeader));
        checkTextInElement(searchSuggestionsHeader, "Top Products");
        checkIsElementDisplayed(buttonSeeAllResults);

        return this;
    }

    public SearchResultsPage checkDefaultSearchBar() {
        checkTextInElement(headerTrendingSearch, "Trending Searches");
        //checkTextInElement(headerPopularProducts, "Popular Products");
        checkIsPopularSectionsDisplayed();
        checkIsElementsDisplayedInSearch(popularProductsInSearchBar);
        searchForSpecificProduct();
        checkIsElementsDisplayedInSearch(suggestedProductsInSearchBar);
        clickOnElement(buttonSeeAllResults);

        return new SearchResultsPage(webDriver);
    }

    private void checkIsPopularSectionsDisplayed() {

        Assert.assertFalse("Popular sections list is empty!", popularSections.isEmpty());
        for (WebElement section : popularSections) {
            Assert.assertTrue("Popular section is not displayed: " + section.getText(),
                    section.isDisplayed());
        }
        logger.info("All popular sections are displayed. Count: " + popularSections.size());

    }

    public void checkIsElementsDisplayedInSearch(List<WebElement> webElement) {
        Assert.assertFalse("Elements list is empty for locator: " + webElement, webElement.isEmpty());
        for (WebElement el : webElement) {
            Assert.assertTrue("Element is not displayed: " + el.getText(), el.isDisplayed());
        }
        logger.info("All elements for locator are displayed. Count: " + webElement.size());
    }


    public HeaderElements clickOnHomePageIcon() {
        clickOnElement(UALogo);
        return this;
    }
}

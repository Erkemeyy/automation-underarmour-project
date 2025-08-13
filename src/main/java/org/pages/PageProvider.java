package org.pages;

import org.openqa.selenium.WebDriver;

public class PageProvider {
    private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public HomePage getHomePage() {
        return new HomePage(webDriver);
    }

    public MensPage getMensPage() {
        return new MensPage(webDriver);
    }

    public LoginPage getLoginPage() {
        return new LoginPage(webDriver);
    }

    public SearchResultsPage getSearchResultsPage(){
        return new SearchResultsPage(webDriver);
    }

    public MensSwimwearPage getMensSwimwearPage(){
        return new MensSwimwearPage(webDriver);
    }

    public ProductDetailPage getProductDetailPage(){
        return new ProductDetailPage(webDriver);
    }

    public CartPage getCartPage(){
        return new CartPage(webDriver);
    }

    public HaloPage getHaloPage(){
        return new HaloPage(webDriver);
    }






}

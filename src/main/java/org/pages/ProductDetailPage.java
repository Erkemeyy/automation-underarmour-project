package org.pages;

import org.openqa.selenium.WebDriver;

public class ProductDetailPage extends ParentPage{
    public ProductDetailPage(WebDriver webDrive){
        super(webDrive);
    }

    @Override
    public String getRelativeURL() {
        return "en-us/p/swimwear/ua_expanse_mens_2-in-1_boardshorts/1370030.html?dwvar_1370030_color=001";
    }

    public ProductDetailPage checkIsRedirectToProductDetailPage(){
        checkUrl();
        return this;

    }

}

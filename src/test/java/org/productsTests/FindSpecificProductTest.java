package org.productsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class FindSpecificProductTest extends BaseTest {

    @Test
    public void testSearchSpecificProduct(){
        pageProvider.getHomePage()
                .openHomePageAndCloseCookiesBanner()
                .checkIsRedirectToHomePage()
                .getHeaderElements().clickOnInputSearch()
                .checkDefaultSearchBar()
                .checkIsRedirectToSearchReseltsPage()
        ;
    }

    @After
    public void backToHomePage(){
        pageProvider.getSearchResultsPage()
                .clickOnElementHomePage()
                .checkIsRedirectToHomePage();
    }


}

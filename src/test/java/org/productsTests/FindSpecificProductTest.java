package org.productsTests;

import org.apache.log4j.Logger;
import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class FindSpecificProductTest extends BaseTest {
    Logger logger = Logger.getLogger(getClass());

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
        logger.info("Post condition - log out from account");
        pageProvider.getSearchResultsPage()
                .clickOnElementHomePage()
                .checkIsRedirectToHomePage();
    }


}

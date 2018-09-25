package ru.appium.autotests.PageObjectModel.tests;


import org.junit.Test;
import ru.appium.autotests.PageObjectModel.pages.SearchPage;

public class Task_3_New extends CoreTestCase {

    @Test
    public void Task_3_New(){

        SearchPage searchPage = new SearchPage(driver);
        searchPage.initSearchInput();
        searchPage.typeSearchLine("Java");
        searchPage.waitForSearchResult();
        searchPage.closeSearchResult();

        System.out.println("TEST END");
    }

}

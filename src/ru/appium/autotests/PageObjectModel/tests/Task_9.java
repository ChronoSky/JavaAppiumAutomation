package ru.appium.autotests.PageObjectModel.tests;


import org.junit.Test;
import ru.appium.autotests.PageObjectModel.pages.SearchPage;

public class Task_9 extends CoreTestCase {

    @Test
    public void Task_9(){

        SearchPage searchPage = new SearchPage(driver);
        searchPage.initSearchInput();
        searchPage.typeSearchLine("Java");

        searchPage.checkCountSearchResaultsMoreThan(2);
        searchPage.selectArticleByNameAndDescription("Java", "Island of Indonesia");

        System.out.println("TEST END");
    }

}

package ru.appium.autotests.PageObjectModel.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.appium.autotests.PageObjectModel.core.MainPageObject;
import ru.appium.autotests.PageObjectModel.pages.ArticlePage;
import ru.appium.autotests.PageObjectModel.pages.MyListsPage;
import ru.appium.autotests.PageObjectModel.pages.SearchPage;

import java.util.List;

public class Task_5_New extends CoreTestCase {


    @Test
    public void Task_5_New() throws InterruptedException {

        SearchPage searchPage = new SearchPage(driver);

        searchPage.initSearchInput();
        searchPage.typeSearchLine("Java");
        searchPage.selectArticleByIndex(0);

        ArticlePage articlePage = new ArticlePage(driver);
        articlePage.createReadingListAndAddArticle("List1");
        articlePage.closeArticle();

        searchPage.initSearchInput();
        searchPage.typeSearchLine("Java");
        searchPage.selectArticleByIndex(1);
        articlePage.addArticleToReadingList("List1");
        articlePage.closeArticle();
        searchPage.openMyLists();

        MyListsPage myListsPage = new MyListsPage(driver);
        myListsPage.selectListByName("List1");
        myListsPage.checkCountArticlsInListEquals(2);
        myListsPage.deleteArticleByIndex(0);
        myListsPage.checkCountArticlsInListEquals(1);
        String listTabText = myListsPage.getAticleNameInListByIndex(0);
        myListsPage.selectArticleInListByIndex(0);

        articlePage.checkTitleNameEquals(listTabText);

        System.out.println("TEST END");
    }


}

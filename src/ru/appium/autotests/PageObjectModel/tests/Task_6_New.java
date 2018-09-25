package ru.appium.autotests.PageObjectModel.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.appium.autotests.PageObjectModel.pages.ArticlePage;
import ru.appium.autotests.PageObjectModel.pages.SearchPage;
import ru.appium.autotests.sandbox.BaseTest;

import java.util.List;

public class Task_6_New extends BaseTest{

    @Test
    public void Task_6() throws InterruptedException {

        SearchPage searchPage = new SearchPage(driver);

        searchPage.initSearchInput();
        searchPage.typeSearchLine("Java");
        searchPage.selectArticleByIndex(0);

        ArticlePage articlePage = new ArticlePage(driver);

        articlePage.checkTitleExist();

        System.out.println("TEST END");
    }


}

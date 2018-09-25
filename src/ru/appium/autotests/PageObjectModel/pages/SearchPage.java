package ru.appium.autotests.PageObjectModel.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.appium.autotests.PageObjectModel.core.MainPageObject;

import java.util.List;

import static junit.framework.TestCase.fail;

public class SearchPage extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
            RESULT_CONTEINERS = "org.wikipedia:id/page_list_item_container",
            CLOSE_X_BUTTON_ID = "org.wikipedia:id/search_close_btn",
            XPATH_MY_LISTS_BUTTON = "//*[@content-desc='My lists']",
            XPATH_MY_LISTS_TITLE = "//*[@resource-id='org.wikipedia:id/single_fragment_toolbar']/*[@text='My lists']";


    public SearchPage(AppiumDriver driver) {
        super(driver);
    }

    public void initSearchInput(){
        click(By.xpath(SEARCH_INIT_ELEMENT), "Не найдено поле с текстом 'Search Wikipedia'");
        waitForElementPresent(By.xpath(SEARCH_INPUT), "Не найдено поле с текстом 'Search…'");
    }

    public void typeSearchLine(String value){
        sendKeys(By.xpath(SEARCH_INPUT),value, "Не найден элемент с текстом 'Search…' на экране");
    }

    public void waitForSearchResult(){
        if (getCountSearchResaults()>0) return;
        fail("Не найдено ни одной статьи");
    }

    public void closeSearchResult() {
        click(By.id(CLOSE_X_BUTTON_ID), "Кнопка 'X' не найдена");
        waitForElementPresent(By.xpath(SEARCH_INPUT), "Элемент с текстом 'Search…' не присуисивует на экране");
        if (getCountSearchResaults()!=0) {fail("Результат поиска отменен");};
    }


    public int getCountSearchResaults(){
        return getElementList(By.id(RESULT_CONTEINERS)).size();
    }

    public void selectArticleByIndex(int index) {
        List<WebElement> searchList = getElementList(By.id(RESULT_CONTEINERS));
        searchList.get(index).click();
    }

    public void openMyLists() {
        click(By.xpath(XPATH_MY_LISTS_BUTTON),"Не найдена иконка 'Список вкладок'");
        waitForElementPresent(By.xpath(XPATH_MY_LISTS_TITLE),"Не открылся раздел 'Список вкладок'");
    }


}
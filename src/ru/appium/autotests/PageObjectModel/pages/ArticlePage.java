package ru.appium.autotests.PageObjectModel.pages;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import ru.appium.autotests.PageObjectModel.core.MainPageObject;


public class ArticlePage extends MainPageObject {

    private static final String
            ID_TITLE_ARTICLE = "org.wikipedia:id/view_page_title_text",
            XPATH_OPTION_BUTTON = "//*[@content-desc='More options']",
            XPATH_OPTION_MENU = "//*[@text='Add to reading list']",
            ID_GOT_IT = "org.wikipedia:id/onboarding_button",
            XPATH_FAVOURITE_TAB_TEXT_INPUT = "//*[@text='My reading list']",
            XPATH_CONFIRM_BUTTON_ADD_TAB = "//*[@text='OK']",
            XPATH_ARTICLE_CLOSE_BUTTON = "//*[@content-desc='Navigate up']",
            XPATH_READING_LIST_MENU = "//*[@text='{SUBSTRING}']";

    public ArticlePage(AppiumDriver driver) {
        super(driver);
    }

    // ***************  TEMPLATES METHODS  ******************

    private String generatorReadingListMenuItem(String readingListName){
        return XPATH_READING_LIST_MENU.replace("{SUBSTRING}", readingListName);
    }

    // ***************  TEMPLATES METHODS  ******************

    public void checkTitleExist() {
        Assert.assertTrue("Не найден титул страницы" , getElementList(By.id(ID_TITLE_ARTICLE)).size()!=0);
    }

    public void selectOption(String optionName) throws InterruptedException {
        click(By.xpath(XPATH_OPTION_BUTTON), "Не найден элемент 'Опции (три точки)'");
        Thread.sleep(1000);
        click(optionMenu(optionName), "Не найден элемент c текстом '"+ optionName +"'");
    }

    private By optionMenu(String optionName){
        switch(optionName){
            case "Add to reading list" : return By.xpath(XPATH_OPTION_MENU);
            default:
                Assert.fail("Option Name not listed in the set");
                return By.xpath(".");
        }

    }

    public void confirmStartAddArticle() {
        click(By.id(ID_GOT_IT),"Не найдена кнопка 'GOT IT'");
    }


    public void setNemaFavouriteTab(String tabName){
        clearAndSendKeys(By.xpath(XPATH_FAVOURITE_TAB_TEXT_INPUT),tabName,"Не найден элемент для вввода названия сохраненной статьи");
    }

    public void confirmAddArticle(){
        click(By.xpath(XPATH_CONFIRM_BUTTON_ADD_TAB),"Не найдена кнопка 'ОК'");
    }

    public void createReadingListAndAddArticle(String readingList) throws InterruptedException {
        selectOption("Add to reading list");
        confirmStartAddArticle();
        setNemaFavouriteTab(readingList);
        confirmAddArticle();
    }

    public void closeArticle() {
        click(By.xpath(XPATH_ARTICLE_CLOSE_BUTTON), "Кнопка 'X' не найдена");
    }

    public void addArticleToReadingList(String readingList) throws InterruptedException {
        selectOption("Add to reading list");
        click(By.xpath(generatorReadingListMenuItem("List1")),"Не найден элемент с текстом 'Create new'");
    }



    public void checkTitleNameEquals(String listTabText) {
        String titleTabText = waitForElementPresent(By.id(ID_TITLE_ARTICLE),"не найден титул страницы").getText();
        Assert.assertTrue("Назвниа титула статьи не совпадает !!!", listTabText.equals(listTabText));
    }
}

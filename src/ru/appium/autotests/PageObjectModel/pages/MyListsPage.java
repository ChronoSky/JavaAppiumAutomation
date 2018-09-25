package ru.appium.autotests.PageObjectModel.pages;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.appium.autotests.PageObjectModel.core.MainPageObject;

import java.util.List;

public class MyListsPage extends MainPageObject {

    private static final String
            XPATH_LIST_ITEM_NAME = "//*[@resource-id='org.wikipedia:id/item_title'and @text='{SUBSTRING}']",
            ID_LIST_ARTICLS_ITEM = "org.wikipedia:id/page_list_item_title";


    public MyListsPage(AppiumDriver driver) {
        super(driver);
    }

    // ***************  TEMPLATES METHODS  ******************

    public String getXpathListName(String listName){
        return XPATH_LIST_ITEM_NAME.replace("{SUBSTRING}", listName);
    }

    // ***************  TEMPLATES METHODS  ******************

    public void selectListByName(String listName) {
        click(By.xpath(getXpathListName(listName)),"Не найдена список вкладок с именем '"+ listName +"'");
    }

    public void checkCountArticlsInListEquals(int count) {
        List<WebElement> listTabs =  getElementList(By.id(ID_LIST_ARTICLS_ITEM));
        Assert.assertTrue("В списке содержится вкладок отличное от " + count,listTabs.size()==count );
    }

    public void deleteArticleByIndex(int index) {
        List<WebElement> listTabs =  getElementList(By.id(ID_LIST_ARTICLS_ITEM));
        swipeElementToLeft(listTabs.get(index));

    }

    public String getAticleNameInListByIndex(int index) {
        List<WebElement> listTabs =  getElementList(By.id(ID_LIST_ARTICLS_ITEM));
        return listTabs.get(index).getText();
    }

    public void selectArticleInListByIndex(int index) {
        List<WebElement> listTabs =  getElementList(By.id(ID_LIST_ARTICLS_ITEM));
        click(listTabs.get(index));
    }
}

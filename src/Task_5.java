import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Task_5 extends BaseTest{

    @Test
    public void Task_5() throws InterruptedException {

        // добавление 1 вкладки
        driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]").click();
        sendKeys(By.xpath("//*[contains(@text, 'Search…')]"),"Java", "Не найден элемент с текстом 'Search…' на экране");
        waitForElementNotPresent(By.xpath("//*[contains(@text, 'Search…')]"), "Элемент с текстом 'Search…' все еще присуисивует на экране", 10);
        List<WebElement> searchList = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        String stateName = searchList.get(0).getText();
        searchList.get(0).click();
        checkElementAttributeEquals(By.id("org.wikipedia:id/view_page_title_text"),"text", stateName);
        click(By.xpath("//*[@content-desc='More options']"),"Не найден элемент 'Опции (три точки)'");
        Thread.sleep(1000);
        click(By.xpath("//*[@text='Add to reading list']"),"Не найден элемент c текстом 'Add to reading list'");
        click(By.id("org.wikipedia:id/onboarding_button"),"Не найдена кнопка 'GOT IT'");
        clearAndSendKeys(By.xpath("//*[@text='My reading list']"),"List_1","Не найден элемент для вввода названия сохраненной статьи");

        // добавляем статью в созданный лист
        click(By.xpath("//*[@text='OK']"),"Не найдена кнопка 'ОК'");
        // выход на начальную страницу
        click(By.xpath("//*[@content-desc='Navigate up']"), "Кнопка 'X' не найдена");

        // добавление 2 вкладки
        driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]").click();
        sendKeys(By.xpath("//*[contains(@text, 'Search…')]"),"Java", "Не найден элемент с текстом 'Search…' на экране");
        waitForElementNotPresent(By.xpath("//*[contains(@text, 'Search…')]"), "Элемент с текстом 'Search…' все еще присуисивует на экране", 10);
        searchList = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        String stateName2 = searchList.get(1).getText();
        searchList.get(1).click();
        checkElementAttributeEquals(By.id("org.wikipedia:id/view_page_title_text"),"text", stateName);
        click(By.xpath("//*[@content-desc='More options']"),"Не найден элемент 'Опции (три точки)'");
        Thread.sleep(1000);
        click(By.xpath("//*[@text='Add to reading list']"),"Не найден элемент c текстом 'Add to reading list'");
        click(By.xpath("//*[@text='List_1']"),"Не найден элемент с текстом 'Create new'");
        // выход на начальную страницу
        click(By.xpath("//*[@content-desc='Navigate up']"), "Кнопка 'X' не найдена");

        // открываем список папок с вкладками
        click(By.xpath("//*[@content-desc='My lists']"),"Не найдена иконка 'Список вкладок'");
        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/single_fragment_toolbar']/*[@text='My lists']"),"Не открылся раздел 'Список вкладок'");
        click(By.xpath("//*[@content-desc='My lists']"),"Не найдена иконка 'Список вкладок'");
        click(By.xpath("//*[@resource-id='org.wikipedia:id/item_title'and @text='List_1']"),"Не найдена список вкладок с именем 'List_1'");

        List<WebElement> listTabs =  driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        Assert.assertTrue("В списке содержится вкладок отличное от 2",listTabs.size()==2 );

        swipeElementToLeft(listTabs.get(0));

        listTabs =  driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        Assert.assertTrue("В списке содержится вкладок отличное от 1",listTabs.size()==1 );
        String listTabText = listTabs.get(0).getText();
        click(listTabs.get(0));
        String titleTabText = waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),"не найден титул страницы").getText();
        Assert.assertTrue("!!!", listTabText.equals(titleTabText));

        System.out.println("TEST END");
    }


}

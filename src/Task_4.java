import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Task_4 extends BaseTest{

    @Test
    public void Task_4(){
        driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]").click();
        //1
        sendKeys(By.xpath("//*[contains(@text, 'Search…')]"),"Java", "Не найден элемент с текстом 'Search…' на экране");

        //2
        waitForElementNotPresent(By.xpath("//*[contains(@text, 'Search…')]"), "Элемент с текстом 'Search…' все еще присуисивует на экране", 10);
        List<WebElement> searchList = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        for (WebElement el : searchList){
            Assert.assertTrue("Результат поиска, с текстом ["+ el.getText() +"], не содержит текст Java", el.getText().contains("Java"));
        }

        System.out.println("TEST END");
    }


}

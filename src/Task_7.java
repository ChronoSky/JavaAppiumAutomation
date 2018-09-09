import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Task_7 extends BaseTest{

    @Test
    public void Task_7() throws InterruptedException {

        // добавление 1 вкладки
        click(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Не найден элемент 'Search Wikipedia'");

        sendKeys(By.xpath("//*[contains(@text, 'Search…')]"),"Java",
                "Не найден элемент с текстом 'Search…' на экране");

        waitForElementNotPresent(By.xpath("//*[contains(@text, 'Search…')]"),
                "Элемент с текстом 'Search…' все еще присуисивует на экране",
                10);

        List<WebElement> searchList = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        String stateName = searchList.get(0).getText();
        searchList.get(0).click();

        checkElementAttributeEquals(By.id("org.wikipedia:id/view_page_title_text"),"text", stateName);

        driver.rotate(ScreenOrientation.LANDSCAPE);

        checkElementAttributeEquals(By.id("org.wikipedia:id/view_page_title_text"),"text", stateName);

        System.out.println("TEST END");
    }


}

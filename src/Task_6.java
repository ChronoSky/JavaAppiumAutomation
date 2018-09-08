import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Task_6 extends BaseTest{

    @Test
    public void Task_6() throws InterruptedException {

        // добавление 1 вкладки
        click(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Не найден элемент 'Search Wikipedia'");
        sendKeys(By.xpath("//*[contains(@text, 'Search…')]"),"Java",
                "Не найден элемент с текстом 'Search…' на экране");
        waitForElementNotPresent(By.xpath("//*[contains(@text, 'Search…')]"),
                "Элемент с текстом 'Search…' все еще присуисивует на экране", 10);

        List<WebElement> searchList = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        Assert.assertTrue("Не найдено ни одной статьи", searchList.size()!=0);
        searchList.get(0).click();

        assertElementPresent(By.id("org.wikipedia:id/view_page_title_text"), driver, "Не найден титул страницы");

        System.out.println("TEST END");
    }

    private void assertElementPresent(By locator, WebDriver driver, String error_message){
        Assert.assertTrue(error_message, driver.findElements(locator).size()!=0);
    }

}

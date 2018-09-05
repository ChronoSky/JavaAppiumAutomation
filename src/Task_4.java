import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class Task_4 {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Java\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

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

    private void sendKeys(By locator, String value, String error_message){
        waitForElementPresent(locator,error_message,10).sendKeys(value);
    }

    private void click(By locator, String error_message){
        waitForElementPresent(locator,error_message,10).click();
    }

    private WebElement waitForElementPresent(By locator, String error_message, long timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private WebElement waitForElementPresent(By locator, String error_message){
        return waitForElementPresent(locator, error_message, 10);
    }

    private boolean waitForElementNotPresent(By locator, String error_message, long timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}

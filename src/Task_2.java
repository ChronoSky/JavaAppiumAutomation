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

public class Task_2 {
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
    public void Task_2() {
        driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]").click();
        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"), "Не найден элемент Search", 10);
        checkElementAttributeEquals(By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"), "text" , "Search…");
        System.out.println("TEST END");
    }

    protected void checkElementAttributeEquals(By locator, String attName, String value) {
        String text = waitForElementPresent(locator,  "Элемент не найден").getAttribute(attName);
        Assert.assertTrue("Занчение атрибута '"+ attName +"' не соответствует ожидаемому [act:= "+ text +" | exp := "+ value +"]", text.equals(value));
    }

    private WebElement waitForElementPresent(By locator, String error_message, long timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private WebElement waitForElementPresent(By locator, String error_message){
        return waitForElementPresent(locator, error_message, 10);
    }
}

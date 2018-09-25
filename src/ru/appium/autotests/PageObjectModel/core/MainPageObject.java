package ru.appium.autotests.PageObjectModel.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {

    private AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public void sendKeys(By locator, String value, String error_message){
        waitForElementPresent(locator,error_message,10).sendKeys(value);
    }

    public WebElement clear(By locator, String error_message){
        WebElement element =  waitForElementPresent(locator,error_message,10);
        element.clear();
        return element;
    }

    public void clearAndSendKeys(By locator , String value, String error_message) {
        clear(locator, error_message).sendKeys(value);
    }

    public void click(By locator, String error_message){
        waitForElementPresent(locator,error_message,10).click();
    }

    public void click(WebElement element){
        element.click();
    }


    public WebElement waitForElementPresent(By locator, String error_message, long timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForElementPresent(By locator, String error_message){
        return waitForElementPresent(locator, error_message, 10);
    }

    public boolean waitForElementNotPresent(By locator, String error_message, long timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int s_y = (int)(size.height * 0.8);
        int e_y = (int)(size.height * 0.2);

        action.press(x, s_y).waitAction(timeOfSwipe).moveTo(x, e_y).release().perform();

    }

    public List<WebElement> getElementList(By locator){
        return driver.findElements(locator);
    }

    public void swipeElementToLeft(By locator, String error_message){
        WebElement element = waitForElementPresent(locator, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int midle_y = (upper_y + lower_y) / 2 ;

        TouchAction action = new TouchAction(driver);
        action.press(right_x, midle_y)
                .waitAction(150)
                .moveTo(left_x, midle_y)
                .release()
                .perform();

    }

    public void swipeElementToLeft(WebElement element){
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int midle_y = (upper_y + lower_y) / 2 ;

        TouchAction action = new TouchAction(driver);
        action.press(right_x, midle_y)
                .waitAction(150)
                .moveTo(left_x, midle_y)
                .release()
                .perform();

    }

    public void checkElementAttributeEquals(By locator, String attName, String value) {
        waitForElementPresent(locator,  "Элемент не найден").getAttribute(attName).equals(value);
    }

    public static String GetWebElementXpath(WebElement El) throws AssertionError{
        if ((El instanceof WebElement)){
            Object o = El;
            String text = o.toString();
            text = text.substring( text.indexOf("xpath: ")+7,text.length()-1);
            return text;
        }else   {   Assert.fail("Argument is not an WebElement, his actual class is:"+El.getClass());       }
        return "";
    }

    public static By convertWebElementToByReference(WebElement element)
    {
        By byLocator = null;
        String elementDescription = element.toString();
        String elementTypeAndValue[] = (elementDescription.substring(elementDescription.lastIndexOf("-> ") + 3, elementDescription.lastIndexOf("]"))).split(":");

        switch (elementTypeAndValue[0].trim())
        {
            case "id": byLocator = By.id(elementTypeAndValue[1].trim());
                break;

            case "xpath": byLocator = By.xpath(elementTypeAndValue[1].trim());
                break;

            case "link text": byLocator = By.linkText(elementTypeAndValue[1].trim());
                break;

            case "tag name": byLocator = By.tagName(elementTypeAndValue[1].trim());
                break;

            case "class name": byLocator = By.className(elementTypeAndValue[1].trim());
                break;

            case "partial link text": byLocator = By.partialLinkText(elementTypeAndValue[1].trim());
                break;

            case "name": byLocator = By.name(elementTypeAndValue[1].trim());
                break;

            case "css selector": byLocator = By.cssSelector(elementTypeAndValue[1].trim());
                break;

            default:
                throw new RuntimeException("Invalid locator type: " + elementTypeAndValue[0].trim());
        }

        return byLocator;
    }
}

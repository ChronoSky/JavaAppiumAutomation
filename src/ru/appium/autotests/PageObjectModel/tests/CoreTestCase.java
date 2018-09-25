package ru.appium.autotests.PageObjectModel.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.appium.autotests.PageObjectModel.core.MainPageObject;

import java.io.FileReader;
import java.net.URL;
import java.util.Properties;

public class CoreTestCase {

    protected AppiumDriver driver;

    @Before
    public void setUp() throws Exception{

        String workingDir = System.getProperty("user.dir");
        Properties properties = new Properties();
        properties.load(new FileReader(workingDir + "/src/ru/appium/autotests/PageObjectModel/resources/enviroment.properties"));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", properties.getProperty("app.server.platformName"));
        capabilities.setCapability("deviceName", properties.getProperty("app.server.deviceName"));
        capabilities.setCapability("platformVersion", properties.getProperty("app.server.platformVersion"));
        capabilities.setCapability("automationName", properties.getProperty("app.server.automationName "));
        capabilities.setCapability("appPackage", properties.getProperty("app.server.appPackage"));
        capabilities.setCapability("appActivity", properties.getProperty("app.server.appActivity"));
        capabilities.setCapability("app", properties.getProperty("app.server.app"));

        driver = new AndroidDriver(new URL(properties.getProperty("driver.url")), capabilities);
        MainPageObject mainPageObject = new MainPageObject(driver);

        this.rotateScreenPortrait();
    }

    @After
    public void tearDown() {
        // поворот экрана в исходное состояние
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.quit();
    }


    protected void rotateScreenPortrait(){
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }
}

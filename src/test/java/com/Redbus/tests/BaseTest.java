package com.Redbus.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected Properties properties;

    @BeforeMethod
    public void setUp() throws IOException {
        properties = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        properties.load(fis);

        String browserName = properties.getProperty("browser");
        System.out.println(browserName);
        if (browserName.equalsIgnoreCase("chrome")) {
        	ChromeOptions options = new ChromeOptions();
        	options.addArguments("--disable-notifications");
            System.setProperty("webdriver.chrome.driver", properties.getProperty("chromeDriverPath"));
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", properties.getProperty("firefoxDriverPath"));
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", properties.getProperty("InternetExplorerDriverPath"));
            driver = new InternetExplorerDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(properties.getProperty("globalWait")), TimeUnit.SECONDS);
        driver.get(properties.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

package com.Redbus.utils;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Properties properties;
    protected static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver, Properties properties) {
        this.driver = driver;
        this.properties = properties;
        wait = new WebDriverWait(driver, Integer.parseInt(properties.getProperty("globalWait")));
    }

    public WebElement waitForElementToBeVisible(By locator) {
        logger.info("Waiting for element to be visible: " + locator.toString());
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    protected List<WebElement> waitForElementsToBeVisible(By locator) {
        logger.info("Waiting for elements to be visible: " + locator);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    
    protected List<WebElement> waitForElementsToBeVisible(WebElement element) {
        logger.info("Waiting for elements to be visible: " + element);
        return wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public void click(By locator) {
        logger.info("Clicking on element: " + locator.toString());
        waitForElementToBeVisible(locator).click();
    }
    
    public void click(WebElement element) {
    	logger.info("Clicking on web element");
    	element.click();
    }

    public boolean isElementDisplayed(By locator) {
        logger.info("Checking if element is displayed: " + locator.toString());
        return waitForElementToBeVisible(locator).isDisplayed();
    }
    
    protected void clickElementInList(By locator, int index) {
        logger.info("Clicking element in list at index: " + index + " for locator: " + locator);
        List<WebElement> elements = waitForElementsToBeVisible(locator);
        if (index >= 0 && index < elements.size()) {
            elements.get(index).click();
        } else {
            logger.error("Index out of bounds for clickElementInList");
        }
    }
}


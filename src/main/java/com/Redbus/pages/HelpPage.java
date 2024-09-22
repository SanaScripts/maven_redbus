package com.Redbus.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.Redbus.utils.BasePage;

import java.util.Properties;

public class HelpPage extends BasePage {

    private By helpTitle = By.xpath("//h1[contains(text(),'Help')]");  // XPath for the Help page title

    public HelpPage(WebDriver driver, Properties properties) {
        super(driver, properties);
    }

    public boolean isHelpPageDisplayed() {
        return isElementDisplayed(helpTitle);
    }

}

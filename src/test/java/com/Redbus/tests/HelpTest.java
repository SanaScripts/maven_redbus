package com.Redbus.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.Redbus.pages.HomePage;
import com.Redbus.pages.HelpPage;
import com.Redbus.utils.BasePage;

public class HelpTest extends BaseTest {

    @Test
    public void testClickHelpOption() {
        HomePage homePage = new HomePage(driver, properties);
        homePage.goTo();
        homePage.clickHelpOption();

        HelpPage helpPage = new HelpPage(driver, properties);
        
        // Assertion to check if the user is navigated to the "Help" page
        Assert.assertTrue(helpPage.isHelpPageDisplayed(), "Failed to navigate to Help page");
    }
}


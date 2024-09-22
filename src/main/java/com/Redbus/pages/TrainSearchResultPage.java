package com.Redbus.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.Redbus.utils.BasePage;

public class TrainSearchResultPage extends BasePage {
	private By searchResults = By.className("search_tupple_wrap");
	
	public TrainSearchResultPage(WebDriver driver, Properties properties) {
		super(driver, properties);
	}
	
    public void waitForSearchResults() {
        waitForElementsToBeVisible(searchResults);
    }

    public boolean isSearchResultsDisplayed() {
        return isElementDisplayed(searchResults);
    }
}

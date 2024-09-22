package com.Redbus.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Redbus.utils.BasePage;

import java.util.List;
import java.util.Properties;

	
 public class SearchResultPage extends BasePage {

	    private By searchResults = By.id("result-section");
	    private By busDetails = By.className("bus-item-details");
	    private By sortingSection = By.className("sort-sec");
	    

	    public SearchResultPage(WebDriver driver, Properties properties) {
	        super(driver, properties);
	    }

	    public void waitForSearchResults() {
	        waitForElementToBeVisible(searchResults);
	    }

	    public boolean isSearchResultsDisplayed() {
	        return isElementDisplayed(searchResults);
	    }
	    
	    public void sortSearchResults(String criteria, String order) {
	       List<WebElement> sortCriteria = waitForElementToBeVisible(sortingSection).findElements(By.className("f-12"));
	       
	       for(int i = 0; i < sortCriteria.size(); i++) {
	    	   if(sortCriteria.get(i).getText().equals(criteria)) {
	    		     if(order.equals("asc")) {
	    		    	 sortCriteria.get(i).click();
	    		     }else {
	    		    	 sortCriteria.get(i).click();
	    		    	 waitForElementToBeVisible(sortingSection).findElements(By.className("f-12"));
	    		    	 sortCriteria.get(i).click();
	    		     }
	    	   }
	       }
	    }
}


package com.Redbus.pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Redbus.utils.BasePage;

public class TrainTicketPage extends BasePage{
	
	private By sourceInput = By.id("src");
	private By destInput = By.id("dst");
	private By searchButton = By.className("search-btn");
	private By calendarButton = By.className("home_calendar");
	private By destDropDown = By.className("dst_search_wrapper");
	private By sourceDropDown = By.className("src_search_wrapper");
	private By stationCode = By.className("stn_code");
	
	public TrainTicketPage(WebDriver driver, Properties properties) {
		super(driver, properties);
	}
	
	public void searchTrain(String source, String sourceStnCode, String destination, String destStnCode) {
		waitForElementToBeVisible(sourceInput).sendKeys(source);
		
		waitForElementToBeVisible(destInput).sendKeys(destination);
        try {
        	Thread.sleep(5000);
        }catch(Exception ex) {
       	System.out.println(ex);
       }
	List<WebElement> destElements = waitForElementToBeVisible(destDropDown).findElements(By.className("stn_code"));
		for(int i = 0; i < waitForElementToBeVisible(destDropDown).findElements(By.className("stn_code")).size(); i++) {
			destElements = waitForElementToBeVisible(destDropDown).findElements(By.className("stn_code"));
			System.out.println(destElements.get(0).getText());
			if(destElements.get(i).getText().equals(destStnCode)) {
				destElements.get(i).click();
				break;
			}
		}
		click(searchButton);
	}
}

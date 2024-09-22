package com.Redbus.pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Redbus.utils.BasePage;

public class OffersPage extends BasePage{
	
	private By OfferTile = By.className("offerBlock");
	private By closeButton = By.id("offerClose");
	
	public OffersPage(WebDriver driver, Properties properties) {
		super(driver, properties);
	}
	
	public void selectOffer() {
		List<WebElement> offers = waitForElementsToBeVisible(OfferTile);
		offers.get(0).click();
		waitForElementToBeVisible(closeButton).click();
		
	}
}

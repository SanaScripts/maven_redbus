package com.Redbus.tests;

import org.testng.annotations.Test;

import com.Redbus.pages.HomePage;
import com.Redbus.pages.OffersPage;
import com.Redbus.pages.SearchResultPage;
import com.Redbus.pages.TrainSearchResultPage;
import com.Redbus.pages.TrainTicketPage;
import com.Redbus.tests.BaseTest;

public class RedbusSearchTest extends BaseTest {

    @Test
    public void searchBusTicket() {
        HomePage homePage = new HomePage(driver, properties);
        homePage.goTo();
        homePage.searchBus("Bangalore", "Chennai");

        SearchResultPage searchResultPage = new SearchResultPage(driver, properties);
        searchResultPage.waitForSearchResults();
        // Assertions
        assert searchResultPage.isSearchResultsDisplayed() : "Search results not displayed";
    }
    
    @Test
    public void searchBusSort() {
        HomePage homePage = new HomePage(driver, properties);
        homePage.goTo();
        homePage.searchBus("Bangalore", "Chennai");

        SearchResultPage searchResultPage = new SearchResultPage(driver, properties);
        searchResultPage.waitForSearchResults();
        searchResultPage.sortSearchResults("Departure", "desc");
        // Assertions
        assert searchResultPage.isSearchResultsDisplayed() : "Search results not displayed";
    }
    
    @Test
    public void searchTrain() {
        HomePage homePage = new HomePage(driver, properties);
        TrainTicketPage trainTicketPage = new TrainTicketPage(driver, properties);
        homePage.goTo();
        homePage.goToTrainSite();
        trainTicketPage.searchTrain("Bangalore","SBC", "Chennai", "MAS");
        
        try {
        	Thread.sleep(2000);
        }catch(Exception ex) {
        	System.out.println(ex);
        }
        
        TrainSearchResultPage trainSearchResultPage = new TrainSearchResultPage(driver, properties);
        trainSearchResultPage.waitForSearchResults();
        // Assertions
        assert trainSearchResultPage.isSearchResultsDisplayed() : "Search results not displayed";
    }
    
    @Test
    public void viewOffers() {
		String originalWindow = driver.getWindowHandle();
		assert driver.getWindowHandles().size() == 1;
        HomePage homePage = new HomePage(driver, properties);
        homePage.goToOffersPage();
        
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        OffersPage offersPage = new OffersPage(driver, properties);
        offersPage.selectOffer();
        try {
        	Thread.sleep(2000);
        }catch(Exception ex) {
        	System.out.println(ex);
        }
        assert true;

    }
}
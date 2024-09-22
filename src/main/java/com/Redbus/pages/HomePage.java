package com.Redbus.pages;
import com.Redbus.utils.BasePage;

import java.text.ParseException;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private By sourceInput = By.id("src");
    private By destinationInput = By.id("dest");
    private By searchButton = By.id("search_button");
    private By calendarButton = By.id("onwardCal");
    private By dateText = By.className("dateText");
    private By yearText = By.className("yearText");
    private By calendarNavigatorHeader = By.className("DayNavigator__CalendarHeader-qj8jdz-1");
    private By calendarNavigator = By.className("DayNavigator__IconBlock-qj8jdz-2");
    private By dateElements = By.className("DayTiles__CalendarDaysSpan-sc-1xum02u-1");
    private By locationList = By.className("sc-dnqmqq");
    private By railTicketSite = By.id("rail_tickets_vertical");
    private By offersButton = By.className("OfferSection__ViewAllText-sc-16xojcc-1");
    private By blogButton = By.id("blog_footer");
    

    public HomePage(WebDriver driver, Properties properties) {
        super(driver, properties);
    }

    public void goTo() {
        driver.navigate().to(properties.getProperty("url"));
    }
    public void clickHelpOption() {
        click(By.xpath("//a[text()='Help']"));  // XPath of the Help option
    }
    
    public void goToTrainSite() {
    	waitForElementToBeVisible(railTicketSite).click();
    }
    
    public void goToOffersPage() {
    	waitForElementToBeVisible(offersButton).click();
    }
    
    public void selectDate(String date) {
    	try{
    		   // split the date 
		    	String []dateArray = date.split(" ", 4);
		    	String currDate = java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("dd"));
		    	//click(calendarButton);
		    	//String year = driver.findElement(yearText).getText();
		    	//click(calendarNavigatorHeader);
		    	List<WebElement> calNavigator = driver.findElements(calendarNavigator);
		    	System.out.println(calNavigator);
		    	System.out.println(calNavigator.get(1).getText());
		    	String currCalNavigator[] = calNavigator.get(1).getText().split("\n", 2);
		    	String currCal[] = currCalNavigator[0].split(" ", 3);
		    	List<WebElement> dates;
		    	int i = 0;
		    	// checking if the current year is > selected year
		    	if(Integer.parseInt(dateArray[2]) < Integer.parseInt(currCal[1])) {
		    		System.out.println("Year is less than the curr year " + dateArray[2]);
		    	}else if(getNumberFromMonthName(dateArray[1]) < getNumberFromMonthName(currCal[0]) && Integer.parseInt(dateArray[2]) == Integer.parseInt(currCal[1])) {
		    		 System.out.println("Month is less than curr month");
		    	}
		    	else {
		    		while(true){
		    			currCalNavigator = calNavigator.get(1).getText().split("\n", 2);
		    			currCal = currCalNavigator[0].split(" ", 3);
		    			if(dateArray[2].equals(currCal[1]) && getNumberFromMonthName(dateArray[1]) ==  getNumberFromMonthName(currCal[0])) {
		    				break;
		    			}
		    			click(driver.findElements(calendarNavigator).get(2));
		    			System.out.println(driver.findElements(calendarNavigator).get(1).getText());
		    			Thread.sleep(500);
		    		}
		    	}
		    	
		    	// selecting date
		    	if(Integer.parseInt(dateArray[0]) < Integer.parseInt(currDate)) {
		    		System.out.println("Date is < current date");
		    	}else {
		    		dates = driver.findElements(dateElements);
		    		for(i = 0; i < dates.size(); i++) {
		    			if(dates.get(i).getText().equals(dateArray[0])) {
		    				dates.get(i).click();
		    				break;
		    			}
		    		}
		    	}
		    	
	    	}catch(Exception ex) {
	    		System.out.println(ex);
	    	}
    	
//    	for(int i = 0; i < dates.size(); i++) {
//    		if(driver.findElement(dateText).getText().contains("Apr")) {
//    			System.out.println(dates.get(i).getText());
//    			System.out.println(calNavigator.get(1).getText());
//    		}
//    	}
    }

    public void searchBus(String source, String destination) {
        waitForElementToBeVisible(sourceInput).sendKeys(source);
        waitForElementToBeVisible(locationList);
        waitForElementToBeVisible(sourceInput).sendKeys(Keys.ENTER);
        waitForElementToBeVisible(destinationInput).sendKeys(destination);
        waitForElementToBeVisible(locationList);
        waitForElementToBeVisible(destinationInput).sendKeys(Keys.ENTER);
        selectDate("23 Apr 2024");
        click(searchButton);
    }
    
    public static int getNumberFromMonthName(String monthName) throws ParseException {
    	 
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MMM")
                                        .withLocale(Locale.ENGLISH);
        TemporalAccessor temporalAccessor = dtFormatter.parse(monthName);
        return temporalAccessor.get(ChronoField.MONTH_OF_YEAR);
    }
}

package com.qa.democart.pages;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.democart.Uitilities.ElementUtil;

public class ResultsPage {
	
	private WebDriver driver;
	private ElementUtil eutil;
	
	private By resultsPageHeader = By.xpath("//div[@id='content']/h1");
	private By resultsPage= By.cssSelector("div.caption a");
	//private By mainPdt = By.xpath("//a[text()='MacBook']");
	
	
	
	
	
	
	public ResultsPage(WebDriver driver)
		{
		this.driver= driver;
		 eutil= new ElementUtil(driver);
	}
	
	
	public String checkSearchResultsText() {
		
		String rPagetext = eutil.getElementText(resultsPageHeader);
		return rPagetext;
	}
	
	public boolean searchResultsSize()
	
	{
		
		List<WebElement> results= eutil.getElements(resultsPage);
		System.out.println("results page no of itmes is "+results.size());
		if(results.size()>1) {
			
			return true;
			
			}
		return false;
	}

	public ProductInfoPage clickMainProduct(String mainPdt)
	{
			
		List<WebElement> results= eutil.getElements(resultsPage);
		for(WebElement element: results)
		{
			if(element.getText().trim().equals(mainPdt))
					{
						element.click();
						break;
					}
		}
		
	
	return new ProductInfoPage(driver);
	}
	
	
	
	
	
	
	

}

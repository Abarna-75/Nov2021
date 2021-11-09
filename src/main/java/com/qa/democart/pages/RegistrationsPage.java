package com.qa.democart.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.qa.democart.Uitilities.ElementUtil;

public class RegistrationsPage {
	
	public WebDriver driver;
	public ElementUtil eutil;
	Map<String, String> myMap;
	
	private By pdtInfoTable = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][1]");
	private By priceInfoTable = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][2]");

	
	
	public RegistrationsPage(WebDriver driver)
	{
		this.driver= driver;
		eutil = new ElementUtil(driver);
		
	}
	
	@Test
	
	public void fillMyHashMap()
	{
		myMap = new HashMap<String,String>();
		
		myMap.put("name", "MacBookPro");
	
		List<WebElement> listofPdt= driver.findElements(pdtInfoTable);
		ArrayList<String> listText = new ArrayList<String>();
		
		
		for(WebElement e: listofPdt)
		{
			String str= e.getText();
			listText.add(str);
			
		}
		
		for(String s:listText) {
			
			String[] metadata= s.split(":");
			myMap.put(metadata[0], metadata[1]);
			
		}
		System.out.println(myMap);
		
		
		
	}
	
}

package com.qa.democart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.democart.Uitilities.Constants;
import com.qa.democart.Uitilities.ElementUtil;

public class AccountsPage {

	private By accountPageheaders= By.xpath("//div[@id='content']/h2");
	private By logoutLink = By.xpath("//div[@class='list-group']//a[text()='Logout']");
	private By myAccountLink= By.linkText("My Account");
	private By searchField= By.xpath("//input[@type='text']");
	private By searchBtn= By.xpath("//div[@id='search']//button[@type='button']");
	
	private WebDriver driver;
	private ElementUtil eutil;
	
	
	
	public AccountsPage(WebDriver driver)
	{
		this.driver=driver;
		eutil= new ElementUtil(driver);
		
	}
	
	public boolean checkURL()
	{
		return eutil.waitTillURLcontains(Constants.ACCOUNTS_PAGE_url_FRACTION,10);
	}
	
	public  String checkAccountsPageTitle()
	{
		 return eutil.waitTillTitleIs(Constants.ACCOUNTS_PAGE_TITLE,10);
	}
	
	public List<String> checkAccountPageheader()
	{
		List<String> myAccountPageHeaders= eutil.waitTillAllElementsVisible(accountPageheaders, 10);
		return myAccountPageHeaders;
		
	}
		
	public boolean checkLogoutLink()
	{
		return eutil.isLinkVisible(logoutLink);
	}
	
	public boolean checkMyAccountLink()
	{
		return eutil.isElementPresent(myAccountLink);
	}
	
	public ResultsPage doSearch(String productName)
	{
		eutil.doSendKeys(searchField, productName);
		eutil.doClickElementActions(searchBtn);
		return new ResultsPage(driver);
	}
	
	
	
	

	
	
	
}

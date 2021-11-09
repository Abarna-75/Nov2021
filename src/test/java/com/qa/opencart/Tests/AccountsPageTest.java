package com.qa.opencart.Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.democart.Uitilities.ApplicationErrors;
import com.qa.democart.Uitilities.Constants;

public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void setAccountsPage()
	{
		aPage= loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
	}	 
		
	@Test(priority=1)
	public void accountURLTest()
	{
				
		Assert.assertTrue(aPage.checkURL(), ApplicationErrors.URL_NOT_MATCHING);
	}
	
	@Test(priority=2)
	public void accountPageTitleTest()
	
	{
		Assert.assertEquals(aPage.checkAccountsPageTitle(), Constants.ACCOUNTS_PAGE_TITLE,ApplicationErrors.TITLE_NOT_MATCHING);
	}
	
	@Test(priority =3)
	public void checkAccountPageheaderTest()
	{
		Assert.assertEquals(aPage.checkAccountPageheader(), Constants.EXP_ACCOUNTS_PAGE_HEADERS, ApplicationErrors.LOGINHEADER_NOT_MATCHING);
		
	}
	
		
	@Test(priority =3)
	public void isLogoutLinkPresentTest()
	{
		Assert.assertTrue(aPage.checkLogoutLink(), ApplicationErrors.LINK_NOT_VISIBLE);
		
	}
	@Test(priority=4)
	public void ischeckMyAccountTest()
	{
		Assert.assertTrue(aPage.checkMyAccountLink(),ApplicationErrors.LINK_NOT_VISIBLE);
	}
	@DataProvider
	public Object[][] getSearchData()
	{
		return new Object[][] {
			{"Macbook Pro"},
			{"MacBoook air"},
			{"Appple iPad"}};
		
	}
	
	@Test(priority=5, dataProvider="getSearchData")
	public void searchItemsTest(String productName)
	{
		rPage= aPage.doSearch(productName);
		String searchResult= rPage.checkSearchResultsText();
		Assert.assertTrue(searchResult.contains(productName));
			
	}
	
	
	
	
	@DataProvider
	public Object[][] getPdtselectData()
	{
	
		return new Object[][] {
			{"Macbook", "MacBook Air"},
			{"Macbook", "MacBook Pro"},
			{"Apple", "Apple Cinema 30\""}
			
			
		};
	}
	@Test(priority=6, dataProvider="getPdtselectData")
	public void clickMainPdtTest(String pdtName, String mainPdtName)
		{
		
		rPage= aPage.doSearch(pdtName);
		pInfoPage= rPage.clickMainProduct(mainPdtName);
		String header = pInfoPage.pdtHeaderText();
		System.out.println("Main Pdt header is " + header);
		Assert.assertEquals(header,mainPdtName);
	}
	
	@Test(priority= 7)
	public void ProductInfoImgsTest()
	{
				
		Assert.assertTrue(	pInfoPage.ProductInfoImgs());
		
	}
	
	
	
	
	
	
}

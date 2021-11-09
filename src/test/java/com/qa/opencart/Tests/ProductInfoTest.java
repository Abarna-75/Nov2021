package com.qa.opencart.Tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.democart.Uitilities.Constants;

public class ProductInfoTest extends BaseTest {
	
	
	@BeforeClass
	
	public void productInfoPageSetup()
	{
		aPage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}

	
	@Test(enabled = false)
	public void imagesTest()
	{
		rPage= aPage.doSearch("MacBook");
		pInfoPage=rPage.clickMainProduct("MacBook Pro");
		Assert.assertTrue(pInfoPage.ProductInfoImgs());
		
	}
	
	@Test(enabled=false)
	public void pdtInfoTest()
	{
		rPage= aPage.doSearch("MacBook");
		pInfoPage=rPage.clickMainProduct("MacBook Pro");
	
		Map<String, String>  actualMap= pInfoPage.addtoMyHashMap();
		System.out.println("the length of the Map is"+ actualMap.size());
		Assert.assertEquals(actualMap.get("MyProductName"),"MacBook Pro");
		Assert.assertEquals(actualMap.get("Brand"),"Apple");
		Assert.assertEquals(actualMap.get("Product Code"),"Product 18");
		Assert.assertEquals(actualMap.get("price"),"$2,000.00");
			
		
	}
	
	@Test
	
	public void addToCartTest()
	{
		rPage= aPage.doSearch("MacBook");
		pInfoPage=rPage.clickMainProduct("MacBook Pro");
		String successMsg= pInfoPage.addToCart();
		System.out.println(successMsg);
		Assert.assertTrue(successMsg.contains("Success"));
	}
	
	
	
	
}



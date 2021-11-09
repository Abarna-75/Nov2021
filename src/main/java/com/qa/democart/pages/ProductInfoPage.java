package com.qa.democart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.democart.Uitilities.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eutil;
	
	private By imageLinks= By.cssSelector("div.col-sm-8 img");
	private By mainPdtheader= By.cssSelector("div#content h1");
	private By productMetaData= By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By priceMetaData= By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By qtyBtnLocator = By.cssSelector("#input-quantity");
	private By addToCartlocator = By.cssSelector("button#button-cart");
	private By successMsglocator= By.xpath("//*[starts-with(text(),'Success')]");
	
	
	private Map<String, String> myPdtMap;
	
	public ProductInfoPage(WebDriver driver)
	{
		this.driver= driver;
		eutil= new ElementUtil(driver);
		
	}
	
	public String pdtHeaderText()
	{
		String headerText = eutil.getElementText(mainPdtheader);
		return headerText;
	
	}
	
	public boolean ProductInfoImgs()
	{
		List<WebElement> productimgList= eutil.getElements(imageLinks);
		if(productimgList.size()>1)
		{
			return true;
		}
		return false;
	}
	
	public Map<String, String> addtoMyHashMap()
	{
		myPdtMap= new HashMap<String, String>();
		
		myPdtMap.put("MyProductName", pdtHeaderText());
//		MacBook Pro
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: Out Of Stock
//		$2,000.00
//		Ex Tax: $2,000.00
		 List<WebElement> pdtInfoList= eutil.getElements(productMetaData);
		 
		
		for(WebElement e: pdtInfoList)
		{
			String metaArray[] = e.getText().split(":");
			String metaKey= metaArray[0].trim();
			String metaValue= metaArray[1].trim();
			myPdtMap.put(metaKey, metaValue);
		}
		
		List<WebElement> priceInfoList= eutil.getElements(priceMetaData);
		
		String price= priceInfoList.get(0).getText().trim();
		String taxprice= priceInfoList.get(1).getText().trim();
		myPdtMap.put("price", price);
		myPdtMap.put("taxprice", taxprice);
		System.out.println(myPdtMap);
		return myPdtMap;
		
		
	}
	
	public String addToCart()
	{
		
		eutil.doSendKeys(qtyBtnLocator,"5");
		eutil.clickLinks(addToCartlocator);
		WebElement element =eutil.waitUntilElementVisible(successMsglocator, 10);
	
		String successMsg=	element.getText();
		return successMsg;
		
			}
	
	
}

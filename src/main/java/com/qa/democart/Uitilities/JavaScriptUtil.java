package com.qa.democart.Uitilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	WebDriver driver;
	
	public JavaScriptUtil(WebDriver driver)
	{
		this.driver= driver;
	}
	
	
	public void flash(WebElement element)
	{
		String elementBgColor= element.getCssValue("backgroundColor");
		
		for(int i=0; i<16; i++)
		{
			changeColor("rgb(255,255,0)", element);
			
			changeColor(elementBgColor,element);
		} 
	}
	
	
	public void changeColor(String color, WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//to est the backgroundColor of an element in javascript = element.style.backgroundColor= 'color'
		js.executeScript("arguments[0].style.backgroundColor ='"+ color+"'",element);
	}

	
	public String getJavaScriptTitle(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String jsTitle=js.executeScript("return document.title;").toString();
		return jsTitle;
		
		}
	
	public void refreshBrowser()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("history.go(0)");
		//selenium command driver.navigate().refresh()
	
	}
	public void getTextOfEntirePAge()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String innerText= js.executeScript("return documentElement.InnerText;").toString();
		System.out.println(innerText);
		
	}
	
	
	public void generateMyOwnAlert(String msg)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("alert('"+msg+"')");
		//similar to alert('shiva shiva')
		
		driver.switchTo().alert().accept();
		
				
	}
	
	
	public void jsExecutorClickElement(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click",element);
	}
	
	
	/*3 methods to click an element -  
	1. javascript executor --> problem is , this one works on the Dom and not on the visibility
	of the element in browser. so if the element is visible or not, it performs the click action
	
	even if the element is there on DOM, there is no gurantee that element is visible on the page 
	
	javascript executor is 100% gurantee that click will be performed ; it is helpful when selenium is not able 
	to interact with an element and throws an uninteractable exception. so no solution for that. we can use JS executor during that time 
	
	2. normal -- driver.findElement().click() method
	
	this is normal selenium click which is replicating an user's action. the element may be hidden or not interactable 
	so whats the use of Jsexecutor clicking on the DOM
	
	selenium is like an end user ;
	
	3. Actions class click 
	actions act= new Actions(driver)
	act.click(element).perform();


*/
	
	//no inbuilt method for scrolling in Selenium. so gotta use javascript executor 
	
	public void scrollWebPage()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");// to scroll down 
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)"); //to scroll up	
		
		
	}
	
	public void scrollUpToHeight()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,700)"); //upto user given height 
	}
	
	public void scrollIntoView(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);// to scroll until an element is found
		
		
	}
	
	public void drawBorder(WebElement element )
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border= '5px solid green'",element);
	}
	
	
	
}

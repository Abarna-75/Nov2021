package com.qa.democart.Uitilities;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.democart.Uitilities.JavaScriptUtil;

import com.qa.democart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver sharada;
	JavaScriptUtil jsUtil;
	

	// driver.findElement(By.id).sendkeys("");

	//By fNameLocator = By.id("Form_submitForm_subdomain");
	//By lNameLocator = By.id("Form_submitForm_Name");

	public ElementUtil(WebDriver sharada) {
		this.sharada = sharada;
		jsUtil = new JavaScriptUtil(sharada);
	}

	public WebElement getElement(By eleLocator) {
		WebElement element = sharada.findElement(eleLocator);
		if(Boolean.parseBoolean(DriverFactory.highlight.trim()))
		{
			jsUtil.flash(element);
		}

			
		return element;
	

	}
	
	public void drawElementBorder(By locator)
	{
		WebElement element = getElement(locator);
		jsUtil.drawBorder(element);
	}
	
	public WebDriver getPage(String url)
	{
		sharada.get(url);
		return sharada;
	}

	public void doSendKeys(By eleLocator, String text) {
		
		WebElement element= getElement(eleLocator);
		element.clear();
		element.sendKeys(text);

	}
	
	public boolean isLinkVisible(By locator)
	{
		return getElement(locator).isDisplayed();
	}

	public void clickLinks(By linkLocator) {
		getElement(linkLocator).click();
		 
		 
	}
	
	public String getPageTitle()
	{
		return sharada.getTitle();
	}

	public String getElementText(By locator) {
	    return	getElement(locator).getText();
	}

	public String getElementAttribute(By locator, String attName) {

		return getElement(locator).getAttribute(attName);
	}

	/* method to find all links */

	public List<WebElement> getElements(By linksLocator) {

		return sharada.findElements(linksLocator);
	}

	public void printLinkList(By locator) {

		List<WebElement> allLinks = getElements(locator);

		for (int i = 0; i < allLinks.size(); i++) {
			String text = allLinks.get(i).getText();
			System.out.println(text);
		}
	}

	public boolean isElementPresent(By locator) {
		List<WebElement> elements = getElements(locator);

		if (elements.size() > 0) {
			System.out.println("Element present");
			return true;
		} else

			System.out.println("Element not present");
		return false;

	}

	public void selectDropdownByValue(By locator, String value) {

		WebElement dropdownName = getElement(locator);
		Select sel = new Select(dropdownName);
		sel.selectByValue(value);

	}

	public void  getWindowHandles ()
	{
		Set<String> browserhandles = sharada.getWindowHandles();
		Iterator<String> it = browserhandles.iterator();
		String parentWindowID = it.next();
		System.out.println(parentWindowID);

		String childWindowID = it.next();
		System.out.println(childWindowID);
		
		sharada.switchTo().window(childWindowID);
		System.out.println(sharada.getCurrentUrl()); //to get child window url 
		sharada.close();
		sharada.switchTo().window(parentWindowID); // to switch back to parent tab
		System.out.println(sharada.getCurrentUrl());
		
		
	
	
	}
	public void selectDropdownByIndexValue(By locator, int indexVal) {

		WebElement dropdownName = getElement(locator);
		Select sel = new Select(dropdownName);
		sel.selectByIndex(indexVal);

	}

	public void selDropdownByVisibleText(By locator, String visibleText) {

		WebElement dropdownName = getElement(locator);
		Select sel = new Select(dropdownName);
		sel.selectByVisibleText(visibleText);

	}

	// to get all options of a Drop down menu

	public void selectDropdownOptions(By locator) {
		WebElement dd = getElement(locator);
		Select selOptions = new Select(dd);
		List<WebElement> ddOptions = selOptions.getOptions();
		System.out.println(ddOptions.size());

		List<String> simpleList = new ArrayList<>();

		for (WebElement e : ddOptions) {
			String text = e.getText();
			System.out.println(text);
			simpleList.add(text);

		}
		System.out.println("***********Printing the arrayList**********");
		System.out.println(simpleList);

		if (ddOptions.size() > 1) {
			System.out.println("Test case 1 Passed");
		}

		if (simpleList.contains("Baby")) {
			System.out.println("Test case 2 Passed");
		}

	}

	// to get the list of all options of drop down without using Select class and choose an option

	public void choosedropDownOptions(By locator, String value)

	{
		List<WebElement> ddList = getElements(locator);

		for (WebElement e : ddList) {

			String text = e.getText();
	
			if (text.equals(value)) {
				e.click();
				break;

			}

		}

	}
	
	// To select multiple options of the dropdown by getting input from user 
	
	public void chooseMultiSelectDropdown(By locator, String... values)
	{		
		 List<WebElement> ddlist = getElements(locator);
		 
		 if(!values[0].equalsIgnoreCase("all"))
				 {
		  for(WebElement e: ddlist)

		 {
			 String text = e.getText();
			 System.out.println(text);
			 
			 for(int i=0; i<values.length;i++)
			 {
				 if(text.equals(values[i]))
				 {
					 e.click();
					 break;
				 }
			 }
			 
		 }
				 }	
		  
		  else
		  {
			  for(WebElement e: ddlist)
			  {
				  e.click();
			  }
		  }
			
				  
			  
		 
	      
		 
	}

	//Actions class- Three level Mouse over and click the third item 
	
	public void ThreeLevelMouseOver(By Parentlocator, By childLocator1, By childLocator2) throws InterruptedException
	{
		Actions act = new Actions(sharada);
	    act.moveToElement(getElement(Parentlocator)).perform();
	    Thread.sleep(7000);
	    
	    
	    act.moveToElement(getElement(childLocator1)).perform();
	    Thread.sleep(5000);
	    
	    getElement(childLocator2).click();
	    
	    
	}
	
	
	// Drag and drop - Actions class 
	
	public void dragAndDropElement(By sourceLocator, By targetLocator)
	{
		WebElement sourceElement = getElement(sourceLocator);
		WebElement targetElement = getElement(targetLocator);
		Actions act = new Actions(sharada);
		act.clickAndHold(sourceElement)
		.moveToElement(targetElement)
		.release(targetElement)
		.build().perform();
		
	}
	
	
	//Actions class - Rightclick to click a menu and choose an Element in the list 
	
	public void myContextClick(By locator,By listLocator, String value)
	{
		WebElement rightClick = getElement(locator);
		Actions act = new Actions(sharada);
		act.contextClick(rightClick).perform();
		
		 List<WebElement> itemList =getElements(listLocator);
	    System.out.println(itemList.size());
	    
	     for(WebElement e: itemList)
	     {
	    	 String text = e.getText();
	    	 System.out.println(text);
	    	 if(text.equals(value))
	    	 {
	    		 e.click();
	    		 break;
	    		 
	    	 }
	     }
		
	}
	
	//Actions class  - difference between Actions class.sendkeys and Webelement.sendkeys 
	
	
	public void doSendKeysActions(By locator,String value)
	{
		WebElement element = getElement(locator);
		Actions act = new Actions(sharada);
		act.sendKeys(element, value).perform();
	}
	
	public void doClickElementActions(By locator)
	{
		WebElement element = getElement(locator);
		Actions act = new Actions(sharada);
		act.click(element).perform();
	}
		 
	
	public void doHandleAlert(By locator)
	{
		
		WebElement submitBtn= getElement(locator);
		submitBtn.click();
		Alert alert= sharada.switchTo().alert();
		String text = alert.getText();
		System.out.println(text);
		alert.accept();
		//alert.dismiss();
		//alert.sendKeys("value to be given if there was a textbox inside alert");
		sharada.switchTo().defaultContent();  // to switch back to the main webpage where the control was before the alert popped up
		
	}
	
	
	//code to upload files - attribute should be type='file'
	public void uploadFile(By locator, String fileLocation,By locator2, By locator3)
	{
		WebElement element = getElement(locator);
		element.sendKeys(fileLocation);
		WebElement element2 = getElement(locator2);
		element2.sendKeys("Saraswathi amma");
		WebElement button = getElement(locator3);
		button.click();
		 
		
	}
	

	public By getMyLocator(String locatorType, String locatorValue)
	{
					
	   By locator = null;
	   
	   switch(locatorType)
	   {
		
	   case"id":
		   locator=By.id(locatorValue);
	   break;
	   
	   case"name":
		   locator=By.name(locatorValue);
	   break;
	   
	   case"tagName": 
		   locator= By.tagName(locatorValue);
	   break;
		
	   case"className":
		   locator=By.className(locatorValue);
	   break;
		
	   case"xpath":
		   locator=By.xpath(locatorValue);
	   break;
		
	   case"cssSelector": 
		   locator=By.cssSelector(locatorValue);
	   break;
	   
	   case"linkText": 
		   locator=By.linkText(locatorValue);
	   break;
	   
	   case"partialLinkText": 
		   locator=By.partialLinkText(locatorValue);
	   break;
	   
	   default: System.out.println("Please pass the right locator type");
	   break;
	   }
	   
	   return locator;
	   
		
	}
	

	public void waitUntilElementPresent(By locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(sharada,timeout);
		WebElement element= wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		element.click();
	}
	

	public void waituntilAlertPresent(int timeout)
	{
		WebDriverWait wait = new WebDriverWait(sharada,timeout);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());  // is equivalent to driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		
		
	}
	
	public WebElement waitUntilElementVisible(By locator,int timeout)
	{
		WebDriverWait wait = new WebDriverWait(sharada,timeout);
		WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}
	
	
	public WebElement waituntilElementClickable(WebElement element, int timeout)
	{
		WebDriverWait wait= new WebDriverWait(sharada,timeout);
		WebElement ele= wait.until(ExpectedConditions.elementToBeClickable(element));
		return ele;
	}
	
	public List<String>  waitTillAllElementsVisible(By locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(sharada,timeout);
			 List<WebElement> footerLinks= wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			 List<String> str = new ArrayList<String>();
			 
			 for(WebElement e: footerLinks)
			 {
				 String text = e.getText();
				 str.add(text);
				 
			 }
			 
			// Collections.sort(str);
			 
			 for(int i=0; i<str.size();i++)
			 {
				 System.out.println(str.get(i));
			 }
			 
			 return str;
	
			
	
	}

	
	public void waitTillElementBecomesClickable(By locator, int timeout)
	
	{
		WebDriverWait wait = new WebDriverWait(sharada, 10);
		WebElement clickableElement= wait.until(ExpectedConditions.elementToBeClickable(locator));
		clickableElement.click();
		
	}

	public String waitTillTitleIs(String titleValue, int timeout) {
		
		
		WebDriverWait wait = new WebDriverWait(sharada,timeout);
		//wait.until(ExpectedConditions.titleContains(titleFraction));
		
		if( wait.until(ExpectedConditions.titleIs(titleValue)))
		{
			return sharada.getTitle();
		}
		return null;

	}

	public boolean waitTillURLcontains(String urlValue, int timeout) {
		WebDriverWait wait = new WebDriverWait(sharada,timeout);
		if( wait.until(ExpectedConditions.urlContains(urlValue)))
		{
			return true;	
			}
		return false;
	}
	
	

	/*
	 * WebElement userName= creator.findElement(By.id("Form_submitForm_subdomain"));
	 * userName.sendKeys("Brahmam"); WebElement firstName=
	 * creator.findElement(By.id("Form_submitForm_Name"));
	 * firstName.sendKeys("Eashwara");
	 */

}



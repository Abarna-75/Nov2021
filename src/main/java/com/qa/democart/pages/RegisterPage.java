package com.qa.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.democart.Uitilities.ElementUtil;

public class RegisterPage {

	WebDriver driver;
	ElementUtil eutil;
	
	private By fname= By.xpath("//input[@type='text' and @name='firstname']");
	private By lname= By.xpath("//input[@type='text' and @name='lastname']");
	private By email= By.xpath("//input[@type='email' and @name='email']");
	private By telephone = By.xpath("//input[@type='tel' and @name='telephone']");
	private By password= By.xpath("//input[@type='password' and @name='password']");
	private By confirmPwd= By.xpath("//input[@type='password' and @name='confirm']");
	private By subscribeYes =By.xpath("//label[@class='radio-inline']/input[@value='1']");
	private By subscribeNo= By.xpath("//label[@class='radio-inline']/input[@value='0']");
	private By privacyCheckbox= By.xpath("(//input[@type='checkbox' and @name='agree'])");
	private By continueBtn= By.xpath("(//input[@type='submit' and @value='Continue'])");
	private By registerSuccess= By.xpath("//div[@id='content']/h1");
	private By secondContinueBtn= By.xpath("//a[text()='Continue']");		
	private By logoutBtn= By.xpath("//div[@class='list-group']//a[text()='Logout']");
	private By registerLink= By.xpath("//div[@class='list-group']//a[text()='Register']");
			
			
	
	
	public RegisterPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		eutil= new ElementUtil(driver);
		
	}


	
	public Boolean doRegister(
			String firstName,
			String lastName,
			String email, 
			String telephone,
			String password, 
			String subscribe)
	{
		eutil.doSendKeys(this.fname, firstName);
		eutil.doSendKeys(this.lname, lastName);
		eutil.doSendKeys(this.email,email);
		eutil.doSendKeys(this.telephone, telephone);
		eutil.doSendKeys(this.password, password);
		eutil.doSendKeys(this.confirmPwd, password);
		
		if(subscribe.equals("yes"))
		{
		eutil.doClickElementActions(subscribeYes);
		}
		else
			eutil.doClickElementActions(subscribeNo);
		
		eutil.clickLinks(privacyCheckbox);
		eutil.doClickElementActions(continueBtn);
		String successMsg= eutil.getElementText(registerSuccess);
		if(successMsg.contains("Created"))
		{

			
			 eutil.drawElementBorder(secondContinueBtn);
		eutil.doClickElementActions(secondContinueBtn);
		 eutil.drawElementBorder(logoutBtn);
		eutil.doClickElementActions(logoutBtn);
		eutil.doClickElementActions(registerLink);
			
		return true;
		}
		
		return false;
		
		
		
	}
	
	
	
	
	
}

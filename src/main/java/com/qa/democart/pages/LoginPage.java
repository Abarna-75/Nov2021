package com.qa.democart.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.democart.Uitilities.Constants;
import com.qa.democart.Uitilities.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eutil;
	
	
	private By userNameLocator = By.id("input-email");
	private By passwordLlocator= By.id("input-password");
	private By loginBtn= By.xpath("//input[@type='submit']");
	private By forgotPwdLink = By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
	private By loginPageHeader = By.xpath("//div[@class='well'][1]/h2[text()='Returning Customer']");
	private By registerLink= By.xpath("(//a[text()='Register'])[position()=2]");


	//constructor 
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eutil= new ElementUtil(driver);
	}

	
	public String getLoginPageTitle()
	{
		
		return eutil.waitTillTitleIs(Constants.LOGIN_PAGE_TITLE,10);
				
	}
	
	public String getLoginPageHeader()
	{
		WebElement pageHeader = eutil.waitUntilElementVisible(loginPageHeader,10);
		String pageHeaderText = pageHeader.getText();
		return pageHeaderText;
		
	}
	public boolean isForgotPwdLinkvisible()
	
	{
		return eutil.isLinkVisible(forgotPwdLink);
	}
	
	@Step("login to application with username{0} and password as {1}")
	public AccountsPage doLogin(String un, String pwd)
		{
			eutil.waitUntilElementPresent(userNameLocator, 10);
		    eutil.doSendKeys(userNameLocator, un);
		    eutil.waitUntilElementPresent(passwordLlocator, 10);
		    eutil.doSendKeys(passwordLlocator, pwd);
			eutil.doClickElementActions(loginBtn);
			return new AccountsPage(driver);
		
	}
	
	
	public RegisterPage navigateToRegistrationPage()
	{
	
		eutil.clickLinks(registerLink);
		return new RegisterPage(driver);
	}

}

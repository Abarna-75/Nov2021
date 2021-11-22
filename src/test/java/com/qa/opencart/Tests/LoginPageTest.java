package com.qa.opencart.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.democart.Uitilities.ApplicationErrors;
import com.qa.democart.Uitilities.Constants;
import com.qa.democart.pages.AccountsPage;
import com.qa.democart.pages.RegisterPage;
import com.qa.opencart.listeners.TestAllureListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("epic id 101: design login page ")
@Story("User Story # EPIPDS:1001 - Login page funcationality")
@Listeners(TestAllureListener.class)

public class LoginPageTest extends BaseTest{
	

	
	@Description("Login page -Forgot password link detection test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=1)
	public void verifyForgotPwdLinkTest()
	{
		
		Assert.assertTrue(loginpage.isForgotPwdLinkvisible());
		
	}
	
	@Description("Login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void getLoginPagetitleTest()
	{
		String	loginPageTitle=	loginpage.getLoginPageTitle();
		System.out.println(loginPageTitle);
		Assert.assertEquals(loginPageTitle,Constants.LOGIN_PAGE_TITLE,ApplicationErrors.TITLE_NOT_MATCHING);
	}
	
	@Description("Login page Header test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=3)
	public void verifyLoginPageHeader()
	
	{
		String text = loginpage.getLoginPageHeader();
		Assert.assertEquals(text, Constants.LOGIN_PAGE_HEADER,ApplicationErrors.LOGINHEADER_NOT_MATCHING);
	}
	
	
	@Description("Check the login functionality")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=4)
	public void loginTest()
	{
		
		
		//loginpage.doLogin("naveen20animation20@gmail.com", "Selenium12345");
		aPage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	//	aPage= loginpage.doLogin(System.getProperty("username"), System.getProperty("password"));
		Assert.assertTrue(aPage.checkLogoutLink());
	
	}
	



}

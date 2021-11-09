package com.qa.opencart.Tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegistrationPageTest extends BaseTest{

	
	@BeforeClass
	public void registerPageSetUp()
	{
		registerPage= loginpage.navigateToRegistrationPage();
		
		
	}
	
	public String randomEmail()
	{
		Random random = new Random();

		String email = "testautomation" + random.nextInt(5000)+ "@gmail.com" ;
		System.out.println(email);
		return email;
		
	}
	
	
	@DataProvider
	public  Object[][] testLoginsData()
	{
		return new Object[][]{
				
				{"Ramana", "Maharishi", "75757575", "test", "yes"},
				{"Shiva", "Maharishi","7885975","test","yes"},
				{"Shivanya", "Maharishi","5879623975","test","no"}		
							
		};
	}
		
		
		
		
	@Test(dataProvider = "testLoginsData")
	public void doRegisterTest(
			String firstName,
			String lastName,
			String telephone,
			String password, 
			String subscribe)
	{
		Assert.assertTrue(registerPage.doRegister(firstName,lastName,randomEmail(),telephone,password,subscribe));
	}
	

	
	
	
}
	
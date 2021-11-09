package com.qa.opencart.Tests;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import  org.testng.annotations.BeforeTest;

import com.qa.democart.factory.DriverFactory;
import com.qa.democart.pages.AccountsPage;
import com.qa.democart.pages.LoginPage;
import com.qa.democart.pages.ProductInfoPage;
import com.qa.democart.pages.RegisterPage;
import com.qa.democart.pages.ResultsPage;

public class BaseTest {
	
   WebDriver driver;
   DriverFactory df;
   LoginPage loginpage;
   Properties prop;
   AccountsPage aPage;
   ResultsPage rPage;
   ProductInfoPage pInfoPage;
   RegisterPage registerPage;
   
   @BeforeTest
	public void setup()
	{
		 df=new DriverFactory();
		 prop= df.initProperties();
		 driver= df.initDriver(prop);
		 loginpage= new LoginPage(driver);
	}
	
	
	
	
	@AfterTest
	public void tearDown()
	{
		//driver.quit();
	}
	
	
	
	

}

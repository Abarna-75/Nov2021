package com.qa.democart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.qa.democart.factory.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	
	public WebDriver driver;

	public static String highlight;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	
	//@return
	//this method is used to initialize the driver
	

	public WebDriver initDriver(Properties prop)
		{
		optionsManager =  new OptionsManager(prop);
		
		String browserName= prop.getProperty("browserName");
		highlight =prop.getProperty("highlight");
		System.out.println("the chosen browser is" + browserName);
		
		
		if(browserName.equalsIgnoreCase("chrome"))
		{  
			WebDriverManager.chromedriver().setup();
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			
			WebDriverManager.firefoxdriver().setup();
			tldriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
		}
		else
			
			System.out.println("Please pass the right browser name ");
		
		
		getmyThreadLocalDriver().manage().deleteAllCookies();
		getmyThreadLocalDriver().manage().window().maximize();
		getmyThreadLocalDriver().get(prop.getProperty("url"));
		return getmyThreadLocalDriver(); 
}
	
	//@return 
	//this method is used to get the Thread local driver after setting it up 
	
	public WebDriver getmyThreadLocalDriver()
	{
		
		return tldriver.get();
	}
	/**
	 * Take screenshot of failed test cases
	 */
	public String getScreenshot()
	{
		File srcFile = ((TakesScreenshot)getmyThreadLocalDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/testScreenshots/" + System.currentTimeMillis() + ".png";
		File destinationFile = new File(path);
		

		try {
			FileUtils.copyFile(srcFile, destinationFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path;
	}
	
	

	
	public Properties initProperties()
	{
		Properties prop=null;
		FileInputStream finput =null;
		String env =System.getProperty("env");
		System.out.println("running on environment:"+env);
		
		try {
			if(env==null)
		
			{
				System.out.println("running on Production environment:");
			
					finput= new FileInputStream("./src\\test\\resources\\configuration\\config.properties");
				
			}
			
			else {
				System.out.println("running on environment:  "+env);
			switch(env)
					{
					
					case"qa":
						
						finput= new FileInputStream("./src\\test\\resources\\configuration\\config.properties");
						break;
									
					case "staging":
					   	finput= new FileInputStream("./src\\test\\resources\\configuration\\cte1test.properties");	
					   	break;
					   	
					default:
						System.out.println("please pass a valid environment name");
						throw new Exception("NOENVIRONMENTFOUNDEXCEPTION");
						
						
					
					}
		 	     
			}
		}
	
		
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		try {
			prop = new Properties();
			prop.load(finput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	
	
}

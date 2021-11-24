package com.qa.democart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	
	public WebDriver driver;

	public static String highlight;
	public OptionsManager optionsManager;
	public Properties prop;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	
	//@return
	//this method is used to initialize the driver
	  

	public WebDriver initDriver(Properties prop)
		{
		
		this.prop=prop;
		optionsManager =  new OptionsManager(prop);
		
		String browserName= prop.getProperty("browserName");
		highlight =prop.getProperty("highlight");
		System.out.println("the chosen browser is  " + browserName);
		
		
		if(browserName.equalsIgnoreCase("chrome"))
		{  
			WebDriverManager.chromedriver().setup();
			
			
			if(Boolean.parseBoolean(prop.getProperty("remote")))
			{
				init_RemoteDriver("chrome");
			}
			else
				
				tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
		}
		 
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			
			WebDriverManager.firefoxdriver().setup();
			
			if(Boolean.parseBoolean((prop.getProperty("remote"))))
					{
									init_RemoteDriver("firefox");
					}
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
	
	private void init_RemoteDriver(String browserName) {
	
		
		System.out.println("running on remote browser = "+ browserName);
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
		
		DesiredCapabilities dcaps= DesiredCapabilities.chrome();
		dcaps.setCapability(ChromeOptions.CAPABILITY,optionsManager.getChromeOptions());
		
		try {
			tldriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), dcaps));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			DesiredCapabilities fcaps= DesiredCapabilities.firefox();
			fcaps.setCapability(FirefoxOptions.FIREFOX_OPTIONS,optionsManager.getFireFoxOptions());
			try {
				tldriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),fcaps));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}

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
						
						finput= new FileInputStream("./src\\test\\resources\\configuration\\qa.properties");
						break;
									
					case "preprod":
					   	finput= new FileInputStream("./src\\test\\resources\\configuration\\preprod.properties");	
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

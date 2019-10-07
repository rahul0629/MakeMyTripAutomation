package com.mmtautomation.test;

import java.awt.Robot;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BaseClass {

	public static WebDriver d=null;
	public static Properties Configprop=null; 
	public static Properties Elmtprop=null; 
	public static WebDriverWait w=null;
	public static Robot robot=null;
	
	
	public static void intializeDriver() throws Exception
	{
		
		System.out.println("Driver Intialization");
		Configprop=new Properties();
		FileInputStream fisCon=new FileInputStream("C:/Users/rahul/MMTProj/TestData.properties");
		Configprop.load(fisCon);
		
		
		Elmtprop=new Properties();
		FileInputStream fisElm=new FileInputStream("C:/Users/rahul/MMTProj/ElementDetails.properties");
		Elmtprop.load(fisElm);


		if(Configprop.getProperty("browser").equalsIgnoreCase("chrome" ))
		{
			System.setProperty("webdriver.chrome.driver", "E:/Selenium/Software/chromedriver_win32/old/chromedriver.exe");
			d=new ChromeDriver();
			d.manage().window().maximize();
			d.manage().deleteAllCookies();
			d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			d.get(Configprop.getProperty("url"));
			w=new WebDriverWait(d,10);
		}
		else if(Configprop.getProperty("browser").equalsIgnoreCase("firefox" ))
		{
			System.setProperty("webdriver.chrome.driver", "E:/Selenium/Software/chromedriver_win32/chromedriver.exe");
			d=new FirefoxDriver();
			d.manage().window().maximize();
			d.manage().deleteAllCookies();
			d.get(Configprop.getProperty("url"));
		}
		
		else
			System.out.println("Enter browser information");

	}
	
	public void login() throws Exception
	{
		d.findElement(By.cssSelector(Elmtprop.getProperty("cssidtrg"))).click();
		w.until(ExpectedConditions.visibilityOf(d.findElement(By.cssSelector(Elmtprop.getProperty("cssid"))))).sendKeys(Configprop.getProperty("loginid"));
		w.until(ExpectedConditions.elementToBeClickable(By.cssSelector(Elmtprop.getProperty("cssContinue")))).submit();
		
		try {
		    w.until(ExpectedConditions.visibilityOf(d.findElement(By.xpath(Elmtprop.getProperty("xpathpwd"))))).sendKeys(Configprop.getProperty("loginpwd"));
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			w.until(ExpectedConditions.visibilityOf(d.findElement(By.xpath(Elmtprop.getProperty("xpathpwd"))))).sendKeys(Configprop.getProperty("loginpwd"));
		}
		w.until(ExpectedConditions.elementToBeClickable(By.cssSelector(Elmtprop.getProperty("csslgnbutton")))).submit();
		try {
			w.until(ExpectedConditions.visibilityOf(d.findElement(By.cssSelector(Elmtprop.getProperty("csslgnText")))));
			Assert.assertEquals((d.findElement(By.cssSelector(Elmtprop.getProperty("csslgnText")))).getText(), "Hey Traveller");
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			w.until(ExpectedConditions.visibilityOf(d.findElement(By.cssSelector(Elmtprop.getProperty("csslgnText")))));
			Assert.assertEquals((d.findElement(By.cssSelector(Elmtprop.getProperty("csslgnText")))).getText(), "Hey Traveller");
		}
		
	}


	






}

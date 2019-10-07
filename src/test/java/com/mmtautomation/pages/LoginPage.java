package com.mmtautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.mmtautomation.test.BaseClass;

public class LoginPage extends BaseClass{
	
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

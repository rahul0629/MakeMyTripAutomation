package com.mmtautomation.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.mmtautomation.test.BaseClass;

public class FlightsPage extends BaseClass{

	public static boolean flag=false;

	public void findFlight() throws InterruptedException, AWTException
	{
		robot=new Robot();
		w.until(ExpectedConditions.visibilityOf(d.findElement(By.linkText(Configprop.getProperty("linkFlight"))))).click();
		if(Configprop.getProperty("TripType").equalsIgnoreCase("SingleTrip"))
		{
			w.until(ExpectedConditions.visibilityOf(d.findElement(By.xpath("TripTypeOne")))).click();
		}
		else if(Configprop.getProperty("TripType").equalsIgnoreCase("RoundTrip"))
		{
			w.until(ExpectedConditions.visibilityOf(d.findElement(By.xpath(Elmtprop.getProperty("TripTypeRound"))))).click();
		}
		else if(Configprop.getProperty("TripType").equalsIgnoreCase("MultiCity"))
		{
			w.until(ExpectedConditions.visibilityOf(d.findElement(By.xpath(Elmtprop.getProperty("TripTypeMulti"))))).click();
		}
		else
			System.out.println("Enter Proper Trip Type");


		w.until(ExpectedConditions.visibilityOf(d.findElement(By.cssSelector(Elmtprop.getProperty("FromCity"))))).click();
		w.until(ExpectedConditions.visibilityOf(d.findElement(By.cssSelector(Elmtprop.getProperty("FromCityInput"))))).sendKeys(Configprop.getProperty("FromCity"));
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
		w.until(ExpectedConditions.elementToBeClickable(d.findElement(By.cssSelector(Elmtprop.getProperty("ToCityInput"))))).sendKeys(Configprop.getProperty("ToCity"));
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_ENTER);


		WebElement element = d.findElement(By.xpath(Elmtprop.getProperty("FromDate")));
		Actions actions = new Actions(d);
		actions.moveToElement(element).click().build().perform();

		Thread.sleep(5000);

		String beforeXpath="//*[@class='DayPicker-Months']/div[";
		String afterXpath="]/div[@class='DayPicker-Caption']/div";

		while(flag==false)
		{
			for(int i=1;i<=2;i++)
			{
				System.out.println(d.findElement(By.xpath(beforeXpath+i+afterXpath)).getText());
				if((d.findElement(By.xpath(beforeXpath+i+afterXpath)).getText().equalsIgnoreCase("December2019")))
				{
					for(int k=1;k<=5;k++)
					{
						for (int j=1;j<=7;j++)
						{
							System.out.println(d.findElement(By.xpath(beforeXpath+i+afterXpath+"/../following-sibling::div[@class='DayPicker-Body']//div["+k+"]/div["+j+"]")).getAttribute("aria-label"));
						}
					}
					
					
					flag=true;
					break;
				}

			}
			
			if(flag==false)
			{
				WebElement next=d.findElement(By.cssSelector(".DayPicker-NavButton.DayPicker-NavButton--next"));
				actions.click(next).build().perform();
				actions.click(next).build().perform();
			}

		}









	}

}



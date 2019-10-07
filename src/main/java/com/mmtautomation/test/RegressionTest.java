package com.mmtautomation.test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.mmtautomation.pages.FlightsPage;

public class RegressionTest extends BaseClass {
	
	public static boolean login=false;
	public static FlightsPage Flp=new FlightsPage();
	
	
	@Test
	public void FindFlightTest() throws Exception
	{
		
		intializeDriver();
		
		if(!login)
		{
			login();
			login=true;
		}
		Flp.findFlight();
		
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		d.quit();
	}

		
	
	
	

	

}

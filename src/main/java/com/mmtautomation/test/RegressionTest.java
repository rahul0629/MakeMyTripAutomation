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
		
		System.out.println("This is find flight Test");
		intializeDriver();
		
		if(!login)
		{
			login();
			login=true;
			System.out.println("Login is successfull");
		}
		Flp.findFlight();
		
	}
	
	
	@AfterMethod
	public void tearDown() throws Exception
	{
		if(d==null)
		{
			intializeDriver();
		}
			
		d.quit();
	}

		
	
	
	

	

}

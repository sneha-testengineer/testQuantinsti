package main.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class InititizeSelenium {
	public static WebDriver driver;
	
	public static  WebDriver initilizeDriver() {
		
		if(Utilities.getMavenProperties("browser").equalsIgnoreCase("chrome"))
			driver =new ChromeDriver();
		return driver;
	}
	
	

}

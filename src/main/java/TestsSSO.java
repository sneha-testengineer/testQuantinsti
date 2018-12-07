package main.java;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class TestsSSO {
	 
	
	public static WebDriver driver;
	
	  @BeforeSuite 
	    public void SetDriverPaths()
	    {
		  File file = new File("driver/chromedriver.exe");
		  System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		 
	    }
	  
	  
	  @BeforeTest
	  public void initiizeWebDriver()
	  {
		 driver= InititizeSelenium .initilizeDriver();
	  }
	  
	  @Test
	  public void testApplication()
	  {
		  driver.get("https://accounts.quantinsti.com/account");
		       
		  waitForElementToBeClickable(driver.findElement(By.xpath("//*[@name='email']")),5000);
		    
		     driver.findElement(By.xpath("//*[@name='email']")).sendKeys(Utilities.getMavenProperties("username"));
		     driver.findElement(By.xpath("//*[@name='password']")).sendKeys(Utilities.getMavenProperties("password"));
		    
		     waitForElementToBeClickable(driver.findElement(By.xpath("//div/input[@name='email']")),5000);
		     String email=driver.findElement(By.xpath("//div/input[@name='email']")).getText();
		     if(email.equals(Utilities.getMavenProperties("username")))
		    	 System.out.println("Username matches with emailId");
		    	 else
		    		 System.out.println("EmailId verification fails");
		     driver.findElement(By.xpath("//*[@name='mobile']")).sendKeys("7472399829");
		     Select background= new Select (driver.findElement(By.xpath("//*[@name='professionalBackground']")));
		     background.selectByIndex(1);
		     Select graduation= new Select (driver.findElement(By.xpath("//*[@name='professionalBackground']")));
		     graduation.selectByIndex(1); 
		     Select expereince= new Select (driver.findElement(By.xpath("//*[@name='exp']")));
		     expereince.selectByIndex(1);
		     Select learning= new Select (driver.findElement(By.xpath("//*[@name='isTrade']")));
		     learning.selectByIndex(1);
		     driver.findElement(By.xpath("//*[@name='displayName']")).sendKeys("ABC");
		     DateFormat dateFormat = new SimpleDateFormat( "dd_MMM_yyyy__hh_mm_ssaa");
				String screenShotName = "form"+dateFormat+".jpg";
		  			Utilities.takeScreenShot(driver,screenShotName);
		     driver.findElement(By.xpath("//div/button[@type='submit']")).click();
		     driver.findElement(By.xpath("//div[@class='swal2-buttonswrapper']/button[@type='button']")).click();	     
		     waitForElementToBeClickable( driver.findElement(By.xpath("//a[@href='services']")),5000);
		     
		      driver.findElement(By.xpath("//a[@href='services']")).click();
		      driver.findElement(By.xpath("//button[(@text.contains('launch')]")).click();
		      Set<String> allWindowHandles = driver.getWindowHandles();
		     		      
		      for(String handle : allWindowHandles)
		      {
		      driver.switchTo().window(handle);
		      }
		      waitForElementToBeClickable( driver.findElement(By.xpath("//a[@href,'https://www.quantinsti.com/']")),5000);
		      String title= driver.findElement(By.xpath("//a[@href,'https://www.quantinsti.com/']")).getText();
		      
		      if(title.equals("https://www.quantinsti.com/"))
		    	  System.out.println("Title is verified");
		      driver.switchTo().defaultContent();
		      waitForElementToBeClickable( driver.findElement(By.xpath("//a[(@text.contains('Logout')]")),5000);
		      driver.findElement(By.xpath("//a[(@text.contains('Logout')]")).click();
	  
	  }
	  
	  @AfterSuite
	  public static void killDriver()
	  {
		  driver.quit();
	  }
	  
	  
	  public WebElement waitForElementToBeClickable(WebElement ele, int timeOutPeriod) {
			System.out.println("Waiting for webelement" + ele);
			WebDriverWait driverWait = new WebDriverWait(driver, timeOutPeriod);
			driverWait.pollingEvery(10, TimeUnit.MICROSECONDS);
			return driverWait.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver driver) {
					try {
						
						if (ele.isEnabled() && ele.isDisplayed())
							return ele;
						else
							return null;
					} catch (NoSuchElementException ex) {
						return null;
					} catch (StaleElementReferenceException ex) {
						return null;
					} catch (NullPointerException ex) {
						return null;
					}
				}

			});

		}
	

	

}

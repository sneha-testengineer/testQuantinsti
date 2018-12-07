package main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;



public class Utilities {
	 public static String filePath = new File("").getAbsolutePath()+"\\target\\Screenshots";
	 public static String absolutePath = new File("").getAbsolutePath();
	
	public static String getMavenProperties(String key) {
		String value = null;
	    InputStream in = null;
		
		
		
		Properties mavenProps = new Properties();
		

		if (value == null) {
			try {
				 in = new FileInputStream(
						new File(absolutePath + "/config/maven.properties"));
				
				mavenProps.load(in);
				value = mavenProps.getProperty(key);
				
												

			} catch (Exception x) {
				
				System.out.println("***Not able to load propeties from maven");
				x.printStackTrace();
				}
			}
		return value;
		
	}

	public static void takeScreenShot(WebDriver driver, String screenShotName) {

			try {
				File file = new File(filePath);
				if (!file.exists()) {
					System.out.println("File created " + file);
					file.mkdir();
				}

				File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				File targetFile = new File(filePath, screenShotName);
				FileUtils.copyFile(screenshotFile, targetFile);

				//return screenShotName;
				String imagePath = "file:///"+ filePath+"\\"+ screenShotName;
				
				System.out.println("Screenshot can be found : " + imagePath);
				
				
				
			} catch (Exception e) {
				System.out.println("An exception occured while taking screenshot " + e);
				
			}
	}
}

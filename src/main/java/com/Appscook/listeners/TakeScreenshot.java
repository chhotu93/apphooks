package com.Appscook.listeners;

import java.io.File;

import com.Appscook.webDriverFactory.WebDriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

@Slf4j
public class TakeScreenshot extends WebDriverFactory
{
	public static String captuerScreenshot(WebDriver driver,String screenshotName) {
		try {

			//Convert web driver object to TakeScreenshot

			TakesScreenshot scrShot =((TakesScreenshot)driver);

			//Call getScreenshotAs method to create image file

			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

			//Move image file to new destination

			File DestFile=new File("./Screenshot/"+screenshotName+System.currentTimeMillis()+".png");

			//Copy file at destination

			FileUtils.copyFile(SrcFile, DestFile);

			log.info("Screenshot Captured");

		} catch (Exception e) {
			log.info("Exception while taking Screesnhot"+e.getMessage());
		}
		return screenshotName;
	}
}

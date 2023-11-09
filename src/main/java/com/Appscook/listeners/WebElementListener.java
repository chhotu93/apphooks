package com.Appscook.listeners;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

@Slf4j
public abstract class WebElementListener implements WebDriverEventListener {

	public void beforeNavigateTo(String url, WebDriver driver) {
		log.info("Before navigating to: '" + url + "'");
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		log.info("Navigated to:'" + url + "'");
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		log.info("Trying to click on: " + element.toString());
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		log.info("Clicked on: " + element.toString());
	}

	public void beforeNavigateBack(WebDriver driver) {
		log.info("Navigating back to previous page");
	}

	public void afterNavigateBack(WebDriver driver) {
		log.info("Navigated back to previous page");
	}

	public void beforeNavigateForward(WebDriver driver) {
		log.info("Navigating forward to next page");
	}

	public void afterNavigateForward(WebDriver driver) {
		log.info("Navigated forward to next page");
	}

	public void onException(Throwable error, WebDriver driver) {
		log.info("Exception occured: " + error);
		try {
			TakeScreenshot.captuerScreenshot(driver,"Exception");
		} catch (Exception e) {
			log.info("Exception:"+e);
		}
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		log.info("Trying to find Element By : " + by.toString());
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		log.info("Found Element By : " + by.toString());
	}

	/*
	 * non overridden methods of WebListener class
	 */
	public void beforeScript(String script, WebDriver driver) {
	}

	public void afterScript(String script, WebDriver driver) {
	}

	@Override
	public void afterAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		// TODO Auto-generated method stub

	}

}
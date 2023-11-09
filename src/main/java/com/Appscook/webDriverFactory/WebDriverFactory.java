package com.Appscook.webDriverFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import com.Appscook.listeners.TakeScreenshot;
import com.Appscook.listeners.WebElementListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

@Slf4j
public class WebDriverFactory {
  public static WebDriver driver;
  public static Properties prop;
  public static EventFiringWebDriver eventFiringWebDriver;
  public static WebElementListener eventListener;
  public static Logger logger;
  public static ExtentTest logger1;
  public static ExtentHtmlReporter htmlReporter;
  public static ExtentReports extent;
  public static String url;
  public JavascriptExecutor js = (JavascriptExecutor) driver;

  @BeforeMethod
  public void setup() throws MalformedURLException {
    initialization();
  }

  @BeforeSuite
  public void ReportSetup() throws IOException {
    htmlReporter =
        new ExtentHtmlReporter(
            System.getProperty("user.dir") + "/target/STMExtentReport.html");
    extent = new ExtentReports();
    extent.attachReporter(htmlReporter);
    extent.setSystemInfo("Application Name", "appscook");
    extent.setSystemInfo("User Name", "chhotu singh");
    extent.setSystemInfo("Envirnoment", "Dev");
    htmlReporter.config().setDocumentTitle("appscook Automation Testing Report");
    htmlReporter.config().setReportName("appscook Automation Testing");
    htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
    htmlReporter.config().setTheme(Theme.STANDARD);
  }

  public WebDriverFactory() {
    try {
      prop = new Properties();
      FileInputStream ip =
          new FileInputStream(
              System.getProperty("user.dir")
                  + "/src/main/java/com/"
                  + "/Appscook/config/Conf.properties");
      prop.load(ip);
      logger = Logger.getLogger("appscook");
      PropertyConfigurator.configure("src/main/java/com/Appscook/config/log4j.properties");
      url = prop.getProperty("url");

    } catch (FileNotFoundException e) {
      log.info("File Not Found Exception: " + e);
    } catch (IOException e) {
      log.info("IOException: " + e);
    }
  }

  @BeforeMethod
  public static void initialization() {
    String browserName = prop.getProperty("browser");
    if (browserName.equals("Chrome")) {
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--remote-allow-origins=*");
      WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver(options);
      driver.manage().window().setSize(new Dimension(1440, 900)); // full screen
    } else if (browserName.equals("FireFox")) {
      WebDriverManager.firefoxdriver().setup();
      driver = new FirefoxDriver();
    }
    eventFiringWebDriver = new EventFiringWebDriver(driver);
    eventListener =
        new WebElementListener() {
          @Override
          public void beforeGetText(WebElement webElement, WebDriver webDriver) {}

          @Override
          public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {}
        };
    eventFiringWebDriver.register(eventListener);
    driver = eventFiringWebDriver;
    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    driver.get(url);
  }

  @AfterMethod
  public void getResult(ITestResult result) throws Exception {
    if (result.getStatus() == ITestResult.FAILURE) {
      logger1.log(
          Status.FAIL,
          MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
      logger1.log(
          Status.FAIL,
          MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
      String screenshotPath = TakeScreenshot.captuerScreenshot(driver, result.getName());
      logger1.fail(
          "Test Case Failed Snapshot is below " + logger1.addScreenCaptureFromPath(screenshotPath));
    } else if (result.getStatus() == ITestResult.SKIP) {
      logger1.log(
          Status.SKIP,
          MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
    } else if (result.getStatus() == ITestResult.SUCCESS) {
      logger1.log(
          Status.PASS,
          MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
    }
  }

  @AfterMethod
  public void Exit() {
    driver.quit();
  }

  @AfterSuite
  public void endReport() {
    extent.flush();
  }

  public void waitForElementToAppear(By findBy) {
    WebDriverWait wait = new WebDriverWait(driver, 20);
    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
  }

  public void waitForWebElementToAppear(WebElement findBy) {
    WebDriverWait wait = new WebDriverWait(driver, 20);
    wait.until(ExpectedConditions.visibilityOf(findBy));
  }

  public void waitForElementToBeVisible(WebElement findBy) {
    WebDriverWait wait = new WebDriverWait(driver, 20);
    wait.until(ExpectedConditions.visibilityOf(findBy));
  }

  public void waitForElementToBeInVisible(WebElement findBy) {
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.invisibilityOf(findBy));
  }

  public void waitForElementToBeClickable(WebElement findBy) {
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.elementToBeClickable(findBy));
  }

  public void javaScriptClick(WebElement ele) {
    js.executeScript("arguments[0].click();", ele);
  }

  public void scrollIntoView(WebElement ele) {
    js.executeScript("arguments[0]. scrollIntoView(true);", ele);
  }

  public void openNewTab() {
    js.executeScript("window.open()");
  }

  public void scrollToUP() {
    js.executeScript("window.scrollBy(0, -250);");
  }

  public void switchTab(int i) {
    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

    // Switch to the new tab
    driver.switchTo().window(tabs.get(i));
  }
}

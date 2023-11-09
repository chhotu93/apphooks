package com.Appscook.pageObjectTest.botUserTest;

import com.Appscook.pageObjects.botUserObj.LoginPage;
import com.Appscook.pageObjects.botUserObj.SettingPage;
import com.Appscook.webDriverFactory.WebDriverFactory;
import com.Appscook.listeners.TakeScreenshot;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

@Slf4j
public class SettingPageTest extends WebDriverFactory {
  LoginPage loginPage;
  SettingPage settingPage;

  public SettingPageTest() {
    super();
  }

  @BeforeMethod
  public void setup() throws MalformedURLException {
    loginPage = new LoginPage();
    settingPage = new SettingPage();
  }

  @Test(priority = 0, description = "Validation of Logo On appscook home Page")
  public void logoTest() {
    logger1 = extent.createTest("Validation of Logo On  home Page");
    boolean LogTest = loginPage.LogoPresent();
    Assert.assertTrue(LogTest, "Logo is  present in appscook.com Application");
    logger.info("Logo is  present in appscook.com Application");
    TakeScreenshot.captuerScreenshot(driver, "logo");
  }

  @Test(
      priority = 1,
      description = "click on setting option and fill the StudentEvaluation details")
  public void StudentEvaluationPageTest() throws InterruptedException {
    logger1 = extent.createTest("click on  Setting option On  home Page");
    settingPage.fillEvaluationCandidateForm();
    logger.info("Click on  candidateEvaluation option");
    TakeScreenshot.captuerScreenshot(driver, "candidateEvaluation");
  }

  @Test(priority = 2, description = " Selection process test ")
  public void selectionProcessPageTest() throws InterruptedException {
    logger1 = extent.createTest("click on selectionProcess On  home Page");
    settingPage.selection();
    logger.info("go to selectionProcess page");
    TakeScreenshot.captuerScreenshot(driver, "selectionProcess");
  }

  @Test(priority = 3, description = "admission process test")
  public void admissionProcessPageTest() throws InterruptedException {
    logger1 = extent.createTest("click on  admissionProcess on  home Page");
    settingPage.admissionProcess();
    logger.info("go to  admissionProcess page");
    TakeScreenshot.captuerScreenshot(driver, "admissionProcess");
  }
}

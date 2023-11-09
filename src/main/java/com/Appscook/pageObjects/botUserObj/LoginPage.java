package com.Appscook.pageObjects.botUserObj;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.Appscook.webDriverFactory.WebDriverFactory;
import org.testng.Assert;

import java.util.List;

@Slf4j
public class LoginPage extends WebDriverFactory {

  @FindBy(css = "[src='assets/img/logo.png']")
  WebElement logoOfApplication;

  @FindBy(name = "username")
  WebElement emailTextBox;

  @FindBy(name = "password")
  WebElement passwordTextBox;

  @FindBy(css = "[type*='submit']")
  WebElement signInButton;

  public LoginPage() {
    PageFactory.initElements(driver, this);
  }

  public boolean LogoPresent() {
    return logoOfApplication.isDisplayed();
  }
}

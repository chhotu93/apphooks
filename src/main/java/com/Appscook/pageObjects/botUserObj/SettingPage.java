package com.Appscook.pageObjects.botUserObj;

import com.Appscook.webDriverFactory.WebDriverFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SettingPage extends WebDriverFactory {
  @FindBy(css = "#sidebar > li.ng-scope.hasChild > a")
  public WebElement settingOption;

  @FindBy(xpath = "(//span[text()='STUDENT EVALUATION'])[2]")
  public WebElement candidateEvaluationOption;

  @FindBy(id = "ddlAcdmcYrInAdmisionSetting")
  public WebElement academicYearDropDown;

  @FindBy(css = "option[ng-repeat*='year in acdemicYear']")
  public List<WebElement> YearList;

  @FindBy(css = "a[href*='selectionProcess']")
  public WebElement SelectionProcessOption;

  @FindBy(css = ".iCheck-helper")
  public WebElement showAllCheckbox;

  //  @FindBy(css = "[ng-click='evaluateStudent(studentDetail)']")
  //  public WebElement evaluateButton;

  @FindBy(css = "input[type='number']")
  public List<WebElement> ratingNumber;

  @FindBy(css = "button[ng-click*='saveEvaluation']")
  public WebElement saveEvaluationButton;

  @FindBy(
      xpath =
          "//*[@ng-repeat='studentDetail in studentDetails']//following::button[@ng-click='evaluateStudent(studentDetail)']")
  public List<WebElement> evaluateStudent;

  @FindBy(css = "[ng-click='cancelEvaluateStudent()']")
  public WebElement closePopupButton;

  @FindBy(css = "[data-bb-handler='cancel']")
  public WebElement cancelPopupButton;

  @FindBy(css = "[data-bb-handler='Send']")
  public WebElement yesPopupButton;

  @FindBy(css = "[class*='btn btn-green'][ng-click*='saveOrUpdateEvaluation']")
  public WebElement saveButton;

  @FindBy(css = "[ng-click*='printstudentEvaluation()']")
  public WebElement PrintButton;

  @FindBy(css = "a[href*='admissionProcess']")
  public WebElement admissionProcessOption;

  @FindBy(css = "[ng-click*='showAddNewStudentPopUp']")
  public List<WebElement> addButton;

  @FindBy(css = "[ng-click*='cancelAdmissionDetails()']")
  public WebElement closeButton;

  @FindBy(id = "stdInAdmnProcessDdl")
  public WebElement StandardList;

  @FindBy(css = "[ng-repeat*='item in days']")
  public List<WebElement> daysOfCalender;

  @FindBy(css = "[placeholder*='select']")
  public WebElement selectDropDownOption;

  public SettingPage() {
    PageFactory.initElements(driver, this);
  }

  public void fillEvaluationCandidateForm() throws InterruptedException {
    waitForElementToBeClickable(settingOption);
    settingOption.click();
    waitForElementToBeClickable(candidateEvaluationOption);
    candidateEvaluationOption.click();
    waitForElementToBeClickable(academicYearDropDown);
    waitForElementToBeClickable(YearList.get(0));
    YearList.get(1).click();
    Thread.sleep(3000);
    fillRatingForEvaluationCandidate();
  }

  public void fillRatingForEvaluationCandidate() {
    String excelFilePath = "maxRating.xlsx";
    try {
      FileInputStream file = new FileInputStream(excelFilePath);
      Workbook workbook = new XSSFWorkbook(file);
      Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
      for (int row = 1; row <= 2; row++) {
        Row dataRow = sheet.getRow(row);
        // Extract data from Excel
        //        String username = dataRow.getCell(1).getStringCellValue();
        int academic = (int) dataRow.getCell(0).getNumericCellValue();
        int problem = (int) dataRow.getCell(1).getNumericCellValue();
        int Communication = (int) dataRow.getCell(2).getNumericCellValue();
        int sports = (int) dataRow.getCell(3).getNumericCellValue();
        int exam = (int) dataRow.getCell(4).getNumericCellValue();
        javaScriptClick(ratingNumber.get(0));
        Thread.sleep(1000);
        ratingNumber.get(0).clear();
        Thread.sleep(1000);
        ratingNumber.get(0).sendKeys(String.valueOf(academic));
        Thread.sleep(500);
        ratingNumber.get(1).clear();
        ratingNumber.get(1).sendKeys(String.valueOf(problem));
        Thread.sleep(500);
        ratingNumber.get(2).clear();
        ratingNumber.get(2).sendKeys(String.valueOf(Communication));
        Thread.sleep(500);
        ratingNumber.get(3).clear();
        ratingNumber.get(3).sendKeys(String.valueOf(sports));
        Thread.sleep(500);
        ratingNumber.get(4).clear();
        ratingNumber.get(4).sendKeys(String.valueOf(exam));
        Thread.sleep(500);
        saveEvaluationButton.click();
        Thread.sleep(500);
      }
      workbook.close();
      file.close();
    } catch (Exception ignored) {
    }
  }

  public void selection() throws InterruptedException {
    javaScriptClick(SelectionProcessOption);
    Thread.sleep(2000);
    selectDropDownOption.click();
    Thread.sleep(1000);
    for (WebElement d : daysOfCalender) {
      if (d.getText().contains("3")) {
        System.out.println("Inlooptestday" + d.getText());
        d.click();
        Thread.sleep(1000);
        break;
      }
    }

    javaScriptClick(showAllCheckbox);
    Thread.sleep(2000);
    waitForElementToBeClickable(evaluateStudent.get(0));
    evaluateStudent.get(0).click();
    Thread.sleep(2000);
    closePopupButton.click();
    Thread.sleep(2000);
    cancelPopupButton.click();
    Thread.sleep(2000);
    Select objSelect = new Select(driver.findElement(By.id("selectionStatus")));
    objSelect.selectByVisibleText("None");
    Thread.sleep(2000);
    objSelect.selectByVisibleText("On hold +");
    Thread.sleep(2000);
    saveButton.click();
    waitForElementToBeClickable(cancelPopupButton);
    cancelPopupButton.click();
    Thread.sleep(2000);
  }

  public void admissionProcess() throws InterruptedException {
    javaScriptClick(admissionProcessOption);
    Thread.sleep(2000);
    Select objSelect = new Select(StandardList);
    objSelect.selectByVisibleText("LKG");
    Thread.sleep(2000);
    addButton.get(0).click();
    Thread.sleep(2000);
    closeButton.click();
    Thread.sleep(2000);
  }
}

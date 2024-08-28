package com.cybage.listener;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.cybage.util.Report;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.IOException;

public class TestListener implements ITestListener {
      private static WebDriver driver;
    @Override
    public void onTestSuccess(ITestResult result) {
        Report.test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+"-PASS",ExtentColor.GREEN));

    }

    @Override
    public void onTestFailure(ITestResult result) {
        Report.test.log(Status.FAIL, result.getThrowable().getMessage());
        Report.test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+"-Fail",ExtentColor.RED));

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Report.test.log(Status.SKIP, result.getThrowable().getMessage());
        Report.test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+"-SKIPPED",ExtentColor.YELLOW));

    }

}

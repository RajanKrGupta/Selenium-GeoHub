package com.cybage.util;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;


public class Report
{
    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    private static final Logger log = LoggerFactory.getLogger(Report.class);
    public WebDriver driver;


    public static void initializeReport()
    {
        sparkReporter =  new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/extentSparkReport.html");
        sparkReporter.config().setDocumentTitle("Geohub Automation Report");
        sparkReporter.config().setReportName("Geohub Test execution Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setTimeStampFormat("yyyy-mm-dd hh:mm:ss");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);      //attached

    }
    public static String captureScreenshot(WebDriver driver) throws IOException {
        String FileSeparator = System.getProperty("file.separator");
        String Extent_report_path = "." + FileSeparator + "Reports";
        String ScreenshotPath = Extent_report_path + FileSeparator + "screenshots";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotName = "screenshot" + Math.random() + ".png";
        String screenshotpath = ScreenshotPath + FileSeparator + screenshotName;
        FileUtils.copyFile(src, new File(screenshotpath));
        return "." + FileSeparator + "screenshots" + FileSeparator + screenshotName;
    }

    public static void endReport()
    {
        extent.flush();
    }


}
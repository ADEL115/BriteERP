package com.BriteERP.tests;

import com.BriteERP.utilities.BrowserUtils;
import com.BriteERP.utilities.ConfigurationReader;
import com.BriteERP.utilities.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestBase {

    // Zinah hi
    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;

    protected static ExtentReports report;
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentTest extentLogger;

    @BeforeClass
    public void startTest() {
        report = new ExtentReports();
        String filePath = System.getProperty("user.dir") + "/test-output/report.html";
        htmlReporter = new ExtentHtmlReporter(filePath);
        report.attachReporter(htmlReporter);
        htmlReporter.config().setReportName("BriteERP automated test reports");

        // optional
        report.setSystemInfo("Environment", "QA3");
        report.setSystemInfo("OS", System.getProperty("os.name"));
        report.setSystemInfo("Browser", ConfigurationReader.get("browser"));
        report.setSystemInfo("Testing Engineer", "Adel");
    }

    @AfterTest
    public void tearDownTest() {
        report.flush();
    }

    @BeforeMethod
    public void setUpMethod() {
        // initialize the WebDriver object in test base class using the Driver utility
        driver = Driver.get();
        driver.manage().window().maximize();
        driver.get(ConfigurationReader.get("url"));

        // setting implicit wait --> when element not found it will keep trying to find it for 10 seconds
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        actions = new Actions(driver);

        // explicit wait
        wait = new WebDriverWait(driver, 5);
    }

    @AfterMethod
    public void tearDownMethod(ITestResult result) throws InterruptedException, IOException {
        // if the test failed
        if (result.getStatus() == ITestResult.FAILURE) {
            // record the failed test
            extentLogger.fail(result.getName());
            // take screen shot and add to report0
            String screenshotLocation = BrowserUtils.getScreenshot(result.getName());
            extentLogger.addScreenCaptureFromPath(screenshotLocation);
            // capture the exception
            extentLogger.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentLogger.skip("Test case skipper: " + result.getName());
        }
        Driver.closeDriver();
    }
}

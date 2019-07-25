package main.java;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener extends BaseClass implements ITestListener
{
    private TakesScreenshot ts;
    private static File screenShotFile;
    public ExtentReports er;
    public ExtentTest logger;
    String destination;

    public void onTestStart(ITestResult result)
    {

    }

    public void onTestSuccess(ITestResult result)
    {
        System.out.println(result.getMethod()+"Test Case passed");
        logger.pass("Test case passed is :"+result.getMethod().getMethodName());
        takeScreenShot(result.getMethod().getMethodName()+"_Passed");

    }

    public void onTestFailure(ITestResult result)
    {
        System.out.println(result.getMethod()+"Test Case Failed");
        logger.fail("Test case failed is :"+result.getMethod().getMethodName());
        try {
            takeScreenShot(result.getTestClass().getName().substring(20)+ result.getMethod().getMethodName()+"_Failed");
            logger.addScreenCaptureFromPath(destination);
            }
        catch(IOException io){ System.out.println("Exception in onTestFailure function"); }
    }

    public void onTestSkipped(ITestResult result)
    {
        System.out.println(result.getMethod().getMethodName()+"Test Case skipped");
        logger.warning(result.getMethod().getMethodName()+" Test case being skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }

    public void onStart(ITestContext context)
    {
        System.out.println(context.getStartDate()+" : Test methods in this suite are "+context.getAllTestMethods());
        String extendRptPath= "./Reports/"+context.getName()+".html";
        ExtentHtmlReporter ehr = new ExtentHtmlReporter(extendRptPath);
        er= new ExtentReports();
        er.attachReporter(ehr);
        logger= er.createTest(context.getName());
    }

    public void onFinish(ITestContext context)
    {
        System.out.println(context.getName()+"Test Case finished");
        er.flush();
    }

    public void takeScreenShot(String screenshotName)
    {
        try {
            String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            ts = ((TakesScreenshot) w);
            screenShotFile = ts.getScreenshotAs(OutputType.FILE); // Saved screen shot to object
            destination = "D:/selenium/ScreenShot/"+screenshotName+dateName+".png";
            File finalDestination= new File(destination);
            FileUtils.copyFile(screenShotFile, finalDestination);
        }
        catch(IOException i)
        {
            System.out.println("Exception in take screen shot function");
        }
    }
}

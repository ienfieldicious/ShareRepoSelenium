package main.java;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;

public class TestUtil extends BaseClass
{
    private Select dropDown;


    // Code to move mouse cursor on a particular element
    public void moveCursor(WebElement destination)
    {
        Actions action= new Actions(w);
        action.moveToElement(destination);
        // Code for Log
    }

    // Code to select drop down with multiple methods

    public  void selectDropDown(WebElement locator, String method, String Value)
    {
         dropDown= new Select(locator);

        if (method.toLowerCase().contains("byvisibletext"))
        {
            dropDown.selectByVisibleText(Value);
        }

        else if (method.toLowerCase().contains("byvalue"))
        {
            dropDown.selectByValue(Value);
        }

        else if (method.toLowerCase().contains("byindex"))
        {
            dropDown.selectByIndex(Integer.valueOf(Value));
        }

    }

    public void deselectDropDown(WebElement locator)
    {
        dropDown= new Select(locator);
        dropDown.deselectAll();
    }

}
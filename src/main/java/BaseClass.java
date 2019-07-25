package main.java;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass
{

     public static WebDriver w;
     public static Properties prop =null;
     private static final File propertyFile = new File("./src/main/resources/object.property");

     public BaseClass()
     {
         loadPropertyFile();
     }

               public void loadPropertyFile()
               {
                    try { FileInputStream fis= new FileInputStream(propertyFile);
                         prop= new Properties();
                         prop.load(fis);
                         fis.close();

                    } catch (FileNotFoundException e) { e.printStackTrace(); }
                    catch (IOException io){ io.printStackTrace();}
               }

               public void navigate()
               {
                   String browser=prop.getProperty("browser"); //Declared as string object to use it in code

                   // Comparing string to identify browser type

                   if( browser.equalsIgnoreCase("chrome"))
                   {
                       System.setProperty(prop.getProperty("ChromeDriverKey"), prop.getProperty("ChromeDriverPath"));
                       w= new ChromeDriver();
                       w.get(prop.getProperty("url"));
                   }

                   else if( browser.equalsIgnoreCase("IE"))
                   {
                       System.setProperty(prop.getProperty("IEdriverkey"), prop.getProperty("IEdriverpath"));
                       w= new InternetExplorerDriver();
                       w.get(prop.getProperty("url"));
                   }

                   else
                   {
                       System.out.println("Browser is not specified");
                   }
               }

               public void closeBrowser()
               {
                   w.close();
               }

}

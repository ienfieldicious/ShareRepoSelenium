package test.java.Module_01;

import main.java.BaseClass;
import main.java.TestUtil;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirstTestCase extends BaseClass
{   TestUtil tu;

    @BeforeMethod
    public void setUp()
    {

    }

    @Test
    public void FirstTestCase()
    {
        navigate();
        Assert.assertEquals(true, false);
    }
    @Test
    public void secondTestCase()
    {
        navigate();
        Assert.assertEquals(true,true);
    }

    @AfterMethod
    public void tearDown()
    {
        closeBrowser();
    }
}

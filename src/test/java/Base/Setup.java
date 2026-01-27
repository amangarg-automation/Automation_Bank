package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Setup {
    WebDriver driver;
    private Properties prop;
    protected ExtentReports report;
    protected ExtentTest test;
    @BeforeTest
    public void setup(ITestContext context) throws IOException {
        FileInputStream fis=new FileInputStream("config.properties");
        prop=new Properties();
        prop.load(fis);
        report=ExtentManager.getInstance(context);
    }
    @BeforeMethod
    public void launchBrowser()
    {
        String browserName=prop.getProperty("browser");
        String url= prop.getProperty("url");
        driver=BrowserFactory.getBrowser(browserName);
        driver.get(url);
    }
    @AfterMethod
    public void closeBrowser()
    {
        if(driver!=null) {
            BrowserFactory.closeBrowser();
        }
    }
    @AfterTest
    public void flushReport()
    {
        report.flush();
    }
}

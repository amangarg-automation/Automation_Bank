package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class Setup {
    protected WebDriver driver;
    private static Properties prop;
    protected ExtentReports report;
    protected PDFGenerator pdfGenerator;
    protected ExtentTest test;
    protected Logger logger=LogManager.getLogger(this.getClass());
    @BeforeTest
    public void setup(ITestContext context) throws IOException {
        logger.info("--------------Started new Test Case----------------");
        FileInputStream fis=new FileInputStream("config.properties");
        prop=new Properties();
        prop.load(fis);
        report=ExtentManager.getInstance(context);
    }
    @BeforeMethod
    @Parameters("browser")
    public void launchBrowser(ITestContext context, String browser,Method method)
    {
        logger.info("-----------------------Started TC "+method.getName()+"---------------------------");
        String browserName=prop.getProperty("browser");
        String url= prop.getProperty("url");
        driver=BrowserFactory.getBrowser(browser);
        driver.get(url);
        context.setAttribute("driver",driver);
        pdfGenerator=new PDFGenerator();
    }
    @AfterMethod
    public void closeBrowser(Method method) throws IOException {
        logger.info("-----------------------completed TC "+method.getName()+"---------------------------");
        String reportFolder=System.getProperty("user.dir")+"/PDF Reports/"+method.getName();
        File dir=new File(reportFolder);
        if(!dir.exists())
        {
            dir.mkdirs();
        }
        String reportPath=reportFolder+"/"+method.getName()+"_"+System.currentTimeMillis()+".pdf";
        pdfGenerator.save(reportPath);
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

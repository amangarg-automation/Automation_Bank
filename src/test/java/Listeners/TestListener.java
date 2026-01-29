package Listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {
    WebDriver driver;
    public void onTestFailure(ITestResult result)
    {
        ITestContext context= result.getTestContext();
    driver=(WebDriver) context.getAttribute("driver");
    String screenshotPath=System.getProperty("user.dir")+"/ScreenshotFailure/"+result.getMethod().getMethodName()+"/"+System.currentTimeMillis()+".png";
    File dir=new File(System.getProperty("user.dir")+"/ScreenshotFailure/"+result.getMethod().getMethodName());
    if(!dir.exists())
    {
        dir.mkdirs();
    }
    File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot,new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

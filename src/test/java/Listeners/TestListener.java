package Listeners;

import Base.BrowserFactory;
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

    @Override
    public void onTestFailure(ITestResult result) {
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");

        if (driver == null) {
            System.out.println("Driver not found for test " + result.getMethod().getMethodName());
            return;
        }

        String screenshotPath = System.getProperty("user.dir") +
                "/ScreenshotFailure/" + result.getMethod().getMethodName() +
                "/" + System.currentTimeMillis() + ".png";

        File dir = new File(System.getProperty("user.dir") +
                "/ScreenshotFailure/" + result.getMethod().getMethodName());
        if (!dir.exists()) dir.mkdirs();

        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

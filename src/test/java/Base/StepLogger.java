package Base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class StepLogger {
    WebDriver driver;
    PDFGenerator pdfGenerator;
    public StepLogger(WebDriver driver, PDFGenerator pdfGenerator)
    {
        this.driver=driver;
        this.pdfGenerator=pdfGenerator;
    }
    public void addSteptoPDF(String description, String expected, String actual, String status) throws IOException {
        String screenshotpath="/app/screenshots/"+System.currentTimeMillis()+".png";
        File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot,new File(screenshotpath));
        pdfGenerator.captureStep(description,expected,actual,status,screenshotpath);
    }
}

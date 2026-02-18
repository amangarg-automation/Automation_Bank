package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TC04 {
    public boolean fileDownloaded(WebDriver driver)
    {
        File dir=new File("C:\\Users\\amank\\Downloads");
        String fileName="Senior_Automation_Tester_Publicis_Sapient_QA.pdf";
        String tempName="Senior_Automation_Tester_Publis_Sapient_QA.crdownload";
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        return wait.until(driver1 -> {
            if(!dir.exists()) return false;
            String[] files=dir.list();
            if(files==null) return false;
            List<String> fileList= Arrays.asList(files);
            System.out.print("File downloaded successful "+fileName);
            return (fileList.contains(fileName) && !fileList.contains(tempName));
        });
    }
    @Test
    public void verify_File_Download() throws InterruptedException, AWTException {
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver=new ChromeDriver(options);
        driver.get("https://www.ilovepdf.com/word_to_pdf");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys("C:\\Users\\amank\\Downloads\\Senior_Automation_Tester_Publicis_Sapient_QA.docx");
        driver.findElement(By.id("processTask")).click();
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.id("pickfiles"))));
        driver.findElement(By.id("pickfiles")).click();
        Assert.assertTrue(fileDownloaded(driver));
        driver.quit();
    }
}

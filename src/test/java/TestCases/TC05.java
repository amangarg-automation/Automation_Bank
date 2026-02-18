package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TC05 {
    @Test
    public void dynamicDropdown()
    {
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        WebDriver driver=new ChromeDriver(options);
        driver.get("https://www.google.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//textarea[@aria-label='Search']")).sendKeys("Automation");
        List<WebElement> elementList=driver.findElements(By.xpath("//div[@role='option'][@aria-label]"));
        String expected=elementList.get(0).getText();
        elementList.get(0).click();
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(100));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea"))));
        String actualText=driver.findElement(By.xpath("//textarea")).getAttribute("value");
        Assert.assertEquals(actualText,expected);
        driver.quit();
    }
}

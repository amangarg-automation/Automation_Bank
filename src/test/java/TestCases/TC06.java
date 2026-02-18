package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC06 {
    @Test
    public void handleInfiniteScroll() throws InterruptedException {
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        WebDriver driver=new ChromeDriver(options);
        driver.get("https://www.pinterest.com/ideas");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//input[@name='searchBoxInput']")).sendKeys("travel"+ Keys.ENTER);
    driver.findElement(By.xpath("//div[@data-test-id='non-story-pin-image']"));
        long currentHeight=(long)((JavascriptExecutor)driver).executeScript("return document.body.scrollHeight");
  int pageCount=0;
   while(true)
   {
       ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
       Thread.sleep(3000);
       pageCount++;
       long newHeight=(long)((JavascriptExecutor)driver).executeScript("return document.body.scrollHeight");
       if(currentHeight==newHeight)
       {
           break;
       }
       currentHeight=newHeight;
   }
   System.out.print(pageCount);
   driver.quit();
    }

}

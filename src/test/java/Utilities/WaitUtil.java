package Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtil {
    public static WebElement waitForElement(WebDriver driver, By locator)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static WebElement waitForElementToBeClickable(WebDriver driver,By locator)
    {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static void waitForPageReload(WebDriver driver)
    {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until((driver1->{return ((JavascriptExecutor)driver1).executeScript("return document.readyState;").equals("complete");}));
    }
    public static WebElement fluentWait(WebDriver driver,By locator)
    {
        FluentWait<WebDriver> wait=new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static WebElement retryStaleElement(WebDriver driver,By locator)
    {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
      return  wait.until((driver1 -> {
          try{
              WebElement element= driver1.findElement(locator);
              if(element.isDisplayed())
              {
                  return element;
              }
              return null;
          } catch (StaleElementReferenceException | NoSuchElementException e) {
              return null;
          }
      }));
    }
}

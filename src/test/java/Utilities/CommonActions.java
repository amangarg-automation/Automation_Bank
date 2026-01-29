package Utilities;

import com.beust.ah.A;
import io.cucumber.java.en_old.Ac;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Set;

public class CommonActions {
    public CommonActions() throws AWTException {
    }

    public void clickElement(WebElement element)
    {
        element.click();
    }
    public void addInput(WebElement element, String input)
    {
        element.sendKeys(input);
    }
    public void acceptAlert(WebDriver driver)
    {
        driver.switchTo().alert().accept();
    }
    public void dismissAlert(WebDriver driver)
    {
        driver.switchTo().alert().dismiss();
    }
    public void sendInputToAlert(WebDriver driver,String input)
    {
        driver.switchTo().alert().sendKeys(input);
    }
    public String getAlertText(WebDriver driver)
    {
       return driver.switchTo().alert().getText();
    }
    public String getTextFromElement(WebElement element)
    {
        return element.getText();
    }
    public void mouseHover(WebDriver driver,WebElement element)
    {
        Actions actions=new Actions(driver);
        actions.moveToElement(element);
    }
    public void doubleClick(WebDriver driver,WebElement element)
    {
        Actions actions=new Actions(driver);
        actions.doubleClick(element).perform();
    }
    public void scrollToElement(WebDriver driver,WebElement element)
    {
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",element);
    }
    public void scrollToTop(WebDriver driver)
    {
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,0)");
    }
    public void scrollToBottom(WebDriver driver)
    {
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }
    public void scrollByLeftOrRight(WebDriver driver,int value)
    {
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(arguments[0],0);",value);
    }
    public void scrollByUpOrDown(WebDriver driver,int value)
    {
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,arguments[0]);",value);
    }
    public void selectDynamicDropdown(WebDriver driver, By locator,String value)
    {
        WebElement element=WaitUtil.waitForElement(driver,locator);
        List<WebElement> dynamicElements=driver.findElements(locator);
        for(WebElement element1:dynamicElements)
        {
            if(element1.getText().equalsIgnoreCase(value))
            {
                element1.click();
                return;
            }
        }
    }
    public void dragAndDropdown(WebDriver driver, WebElement source, WebElement target)
    {
        Actions actions=new Actions(driver);
        actions.clickAndHold(source).moveToElement(target).build().perform();
    }
    public  void handleInfiniteScroll(WebDriver driver,By locator) throws InterruptedException {
        JavascriptExecutor js=(JavascriptExecutor) driver;
        long currentPageHeight=(long)js.executeScript("return document.body.scrollHeight");
        while(true)
        {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(500);
            long newHeight=(long)js.executeScript("return document.body.scrollHeight");
            if(newHeight==currentPageHeight)
            {
                break;
            }
            currentPageHeight=newHeight;
        }
    }
    public void selectValuefromDropdown(WebElement element,String value)
    {
        Select select=new Select(element);
        select.selectByValue(value);
    }
    public void selectTextfromDropdown(WebElement element,String text)
    {
        Select select=new Select(element);
        select.selectByVisibleText(text);
    }
    public void selectByIndex(WebElement element,int index)
    {
        Select select=new Select(element);
        select.selectByIndex(index);
    }
    public void checkRadioButton(WebElement element)
    {
        if(!element.isSelected())
        {
            element.click();
        }
    }
    public void uncheckRadioButton(WebElement element)
    {
        if(element.isSelected())
        {
            element.click();
        }
    }
    public void switchToFrame(WebDriver driver,String name)
    {
        driver.switchTo().frame(name);
    }
    public void switchToFrame(WebDriver driver, int id)
    {
        driver.switchTo().frame(id);
    }
    public void switchToFrame(WebDriver driver,WebElement element)
    {
        driver.switchTo().frame(element);
    }
    public void switchToParentFrame(WebDriver driver)
    {
        driver.switchTo().parentFrame();
    }
    public void switchToDefaultFrame(WebDriver driver)
    {
        driver.switchTo().defaultContent();
    }
    public void switchToWindowHandle(WebDriver driver,String windowHandle)
    {
        driver.switchTo().window(windowHandle);
    }
    public void switchToDefaultHandle(WebDriver driver,String defaultHandle)
    {
        Set<String> windowHandles=driver.getWindowHandles();
        for(String windowHandle:windowHandles)
        {
            if(!windowHandle.equalsIgnoreCase(defaultHandle))
            {
                driver.switchTo().window(windowHandle).close();
            }
        }
        driver.switchTo().window(defaultHandle);
    }
    public Robot robot=new Robot();
    public void keyPress()
    {
        robot.keyPress(KeyEvent.VK_ENTER);
    }
    public void releasePress()
    {
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
    public void mouseRightClick()
    {
        robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
    }
}

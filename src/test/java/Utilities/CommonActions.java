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

    public static void clickElement(WebElement element)
    {
        element.click();
    }
    public static void addInput(WebElement element, String input)
    {
        element.sendKeys(input);
    }
    public static void acceptAlert(WebDriver driver)
    {
        driver.switchTo().alert().accept();
    }
    public static void dismissAlert(WebDriver driver)
    {
        driver.switchTo().alert().dismiss();
    }
    public static void sendInputToAlert(WebDriver driver,String input)
    {
        driver.switchTo().alert().sendKeys(input);
    }
    public static String getAlertText(WebDriver driver)
    {
       return driver.switchTo().alert().getText();
    }
    public static String getTextFromElement(WebElement element)
    {
        return element.getText();
    }
    public static void mouseHover(WebDriver driver,WebElement element)
    {
        Actions actions=new Actions(driver);
        actions.moveToElement(element);
    }
    public static void doubleClick(WebDriver driver,WebElement element)
    {
        Actions actions=new Actions(driver);
        actions.doubleClick(element).perform();
    }
    public static void scrollToElement(WebDriver driver,WebElement element)
    {
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",element);
    }
    public static void scrollToTop(WebDriver driver)
    {
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,0)");
    }
    public static void scrollToBottom(WebDriver driver)
    {
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }
    public static void scrollByLeftOrRight(WebDriver driver,int value)
    {
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(arguments[0],0);",value);
    }
    public static void scrollByUpOrDown(WebDriver driver,int value)
    {
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,arguments[0]);",value);
    }
    public static void selectDynamicDropdown(WebDriver driver, By locator,String value)
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
    public static void dragAndDropdown(WebDriver driver, WebElement source, WebElement target)
    {
        Actions actions=new Actions(driver);
        actions.clickAndHold(source).moveToElement(target).build().perform();
    }
    public static void handleInfiniteScroll(WebDriver driver,By locator) throws InterruptedException {
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
    public static void selectValuefromDropdown(WebElement element,String value)
    {
        Select select=new Select(element);
        select.selectByValue(value);
    }
    public static void selectTextfromDropdown(WebElement element,String text)
    {
        Select select=new Select(element);
        select.selectByVisibleText(text);
    }
    public static void selectByIndex(WebElement element,int index)
    {
        Select select=new Select(element);
        select.selectByIndex(index);
    }
    public static void checkRadioButton(WebElement element)
    {
        if(!element.isSelected())
        {
            element.click();
        }
    }
    public static void uncheckRadioButton(WebElement element)
    {
        if(element.isSelected())
        {
            element.click();
        }
    }
    public static void switchToFrame(WebDriver driver,String name)
    {
        driver.switchTo().frame(name);
    }
    public static void switchToFrame(WebDriver driver, int id)
    {
        driver.switchTo().frame(id);
    }
    public static void switchToFrame(WebDriver driver,WebElement element)
    {
        driver.switchTo().frame(element);
    }
    public static void switchToParentFrame(WebDriver driver)
    {
        driver.switchTo().parentFrame();
    }
    public static void switchToDefaultFrame(WebDriver driver)
    {
        driver.switchTo().defaultContent();
    }
    public static void switchToWindowHandle(WebDriver driver,String windowHandle)
    {
        driver.switchTo().window(windowHandle);
    }
    public static void switchToDefaultHandle(WebDriver driver,String defaultHandle)
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
    public static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static void keyPress()
    {
        robot.keyPress(KeyEvent.VK_ENTER);
    }
    public static void releasePress()
    {
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
    public static void mouseRightClick()
    {
        robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
    }
}

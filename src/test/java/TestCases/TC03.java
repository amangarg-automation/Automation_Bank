package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import java.util.*;
public class TC03 {
    WebDriver driver=new ChromeDriver();
    @Test
    public void TC03()
    {
        driver.get("https://www.hyrtutorials.com/p/html-dropdown-elements-practice.html");
        driver.manage().window().maximize();
        Select select=new Select(driver.findElement(By.id("course")));
        List<WebElement> elements=select.getOptions();
        for(WebElement element:elements)
        {
            System.out.println(element.getText());
        }
        select.selectByVisibleText(elements.get(1).getText());
        System.out.print("selected text is: "+select.getFirstSelectedOption().getText());
        driver.quit();
    }

}

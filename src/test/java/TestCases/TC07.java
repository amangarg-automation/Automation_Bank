package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

class loginPage{
     WebDriver driver;
     WebDriverWait wait;
    public loginPage(WebDriver driver)
    {
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "nav-link-accountList-nav-line-1")
    WebElement login_button;
    @FindBy(id="ap_email_login")
    WebElement email_input_field;
    @FindBy(xpath = "//input[@type='submit']")
    WebElement continue_button;
    @FindBy(id="ap_password")
    WebElement password_input_field;
    @FindBy(id="signInSubmit")
    WebElement sign_in_button;
    @FindBy(xpath="//h4[text()='There was a problem']")
    WebElement singInError;
    public boolean verify_login()
    {
       wait.until(ExpectedConditions.elementToBeClickable(login_button));
        login_button.click();
        wait.until(ExpectedConditions.visibilityOf(email_input_field));
        email_input_field.sendKeys("amankg266@gmail.com");
        continue_button.click();
        wait.until(ExpectedConditions.visibilityOf(password_input_field));
        password_input_field.sendKeys("abcd123");
        sign_in_button.click();
        String expected_error="There was a problem";
        return expected_error.equals(singInError.getText());
    }

}
public class TC07 {
    WebDriver driver;
    @BeforeTest
            public void startBrowser()
    {
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        driver=new ChromeDriver(options);
        driver.get("https://www.amazon.in");
    }
    @AfterTest
    public void closeBrowser()
    {
        driver.quit();
    }
    @Test
    public void TC07_verify_Invalid_login()
    {
        loginPage lp=new loginPage(driver);
        Assert.assertTrue(lp.verify_login());
    }
}

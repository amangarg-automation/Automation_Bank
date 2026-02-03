package CommonPageElements;

import Base.Setup;
import Utilities.CommonActions;
import Utilities.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Setup {
    private static final By amazon_HomePage_LoginButton=By.id("nav-link-accountList-nav-line-1");;
    private static final By amazon_LoginPage_Identifier=By.xpath("//h1[contains(text(),'Sign in')]");
    private static final By amazon_LoginPage_password_page_identifier=By.cssSelector("label[for='ap_password']");
    private static final By amazon_LoginPage_Username_TextBox=By.id("ap_email_login");
    private static final By amazon_LoginPage_Continue_Button=By.className("a-button-input");
    private static final By amazon_LoginPage_Password_TextBox=By.id("ap_password");
    private static final By amazon_LoginPage_Submit_Button=By.id("signInSubmit");
    public static By get_amazon_HomePage_LoginButton()
    {
        return amazon_HomePage_LoginButton;
    }
    public static By amazon_LoginPage_Username_TextBox()
    {
        return amazon_LoginPage_Username_TextBox;
    }
    public static By amazon_LoginPage_Continue_Button()
    {
        return amazon_LoginPage_Continue_Button;
    }
    public static By amazon_LoginPage_Password_TextBox()
    {
        return amazon_LoginPage_Password_TextBox;
    }
    public static By amazon_LoginPage_Submit_Button()
    {
        return amazon_LoginPage_Submit_Button;
    }
    public static By amazon_LoginPage_Identifier()
    {
        return amazon_LoginPage_Identifier;
    }
    public static By amazon_LoginPage_password_page_identifier()
    {
        return amazon_LoginPage_password_page_identifier;
    }
    public static void clickOnLoginButton()
    {
        WaitUtil.waitForElementToBeClickable(driver,amazon_HomePage_LoginButton);
        driver.findElement(amazon_HomePage_LoginButton).click();
    }
    public static boolean verifyLoginPage()
    {
        WaitUtil.waitForElement(driver,amazon_LoginPage_Identifier);
        String expected=driver.findElement(amazon_LoginPage_Identifier).getText();
        return expected.equalsIgnoreCase("Sign in or create account");
    }
    public static void setEmail(String email)
    {
        WaitUtil.waitForElement(driver,amazon_LoginPage_Username_TextBox);
        CommonActions.addInput(driver.findElement( amazon_LoginPage_Username_TextBox),email);
    }
    public static void setPassword(String password)
    {
        WaitUtil.waitForElement(driver,amazon_LoginPage_Password_TextBox);
        CommonActions.addInput(driver.findElement(amazon_LoginPage_Password_TextBox),password);
    }
    public static boolean verifyPasswordPage()
    {
        WaitUtil.waitForElement(driver,amazon_LoginPage_password_page_identifier);
        return driver.findElement(amazon_LoginPage_password_page_identifier).isDisplayed();
    }
}


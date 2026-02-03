package CommonPageElements;

import Base.Setup;
import Utilities.WaitUtil;
import org.openqa.selenium.By;

public class BasePage extends Setup {
    private static final By amazon_User_Navigation=By.id("nav-link-accountList-nav-line-1");
    public static By amazon_User_Navigation()
    {
        return amazon_User_Navigation;
    }
    public static boolean verifyUserLogin(String username)
    {
        WaitUtil.waitForElement(driver,amazon_User_Navigation);
        String expected_UserNAme=driver.findElement(amazon_User_Navigation).getText();
        return expected_UserNAme.equalsIgnoreCase(username);
    }
}

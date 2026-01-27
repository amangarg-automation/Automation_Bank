package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {
    private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
    public WebDriver getBrowser(String browserName)
    {
        if(driver.get()==null)
        {
            switch (browserName)
            {
                case "chrome":
                    ChromeOptions options=new ChromeOptions();
                    options.addArguments("--incognito");
                    options.addArguments("--start-maximized");
                    options.addArguments("--disable-notifications");
                    Map<String,Integer> prefs=new HashMap<>();
                    prefs.put("profile.default_content_setting_values.geolocation",2);
                    options.setExperimentalOption("prefs",prefs);
                    driver.set(new ChromeDriver(options));
                    return driver.get();
                case "firefox":
                    driver.set(new FirefoxDriver());
                    return driver.get();
                case "edge":
                    driver.set(new EdgeDriver());
                    return driver.get();
                default:
                    throw new RuntimeException("No such browser available");
            }

        }
        return driver.get();
    }
    public void closeBrowser()
    {
        if(driver.get()!=null)
        {
            driver.get().quit();
            driver.remove();
        }
    }
}

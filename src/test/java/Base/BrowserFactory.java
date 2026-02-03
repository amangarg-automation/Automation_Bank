package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {
    private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
    public static WebDriver getBrowser(String browserName)
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
                    //options.addArguments("--headless");
                    Map<String,Integer> prefs=new HashMap<>();
                    prefs.put("profile.default_content_setting_values.geolocation",2);
                    options.setExperimentalOption("prefs",prefs);
                    driver.set(new ChromeDriver(options));
                    return driver.get();
                case "firefox":
                    FirefoxOptions firefoxOptions=new FirefoxOptions();
                    //firefoxOptions.addArguments("--headless");
                    driver.set(new FirefoxDriver(firefoxOptions));
                    driver.get().manage().window().maximize();
                    return driver.get();
                case "edge":
                    driver.set(new EdgeDriver());
                    return driver.get();
                default:
                    throw new RuntimeException("No such browser available "+browserName);
            }
        }
        return driver.get();
    }
    public static void closeBrowser()
    {
        if(driver.get()!=null)
        {
            driver.get().quit();
            driver.remove();
        }
    }
}

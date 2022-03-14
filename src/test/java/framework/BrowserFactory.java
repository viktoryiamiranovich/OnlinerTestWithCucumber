package framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import java.util.HashMap;

public class BrowserFactory{

    public static String browser;

    private static final String PROPERTIES_FILE = "config.properties";
    private static final String BROWSER = "browser";



    public static WebDriver createDriver(){
        WebDriver driver=null;
        PropertyReader resources = new PropertyReader(PROPERTIES_FILE);

        browser = resources.getProperty(BROWSER);
        switch (browser) {
            case "chrome" : {
                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("safebrowsing.enabled", "true");
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                break;
            }
            case "firefox" : {
                FirefoxProfile profile = new FirefoxProfile();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions.setProfile(profile));
                break;
            }
            default: Assert.fail(browser + " " + "driver is absent(");
        }
        return driver;
    }
}
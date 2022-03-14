package framework;

import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import static framework.BrowserFactory.createDriver;

public class Browser{

    public static WebDriver driver;

    private static final String IMPLICIT_WAIT = "implicit_wait";
    private static final String PROPERTIES_FILE = "config.properties";
    private static final String URL = "websiteURL";

    private static PropertyReader resources = new PropertyReader(PROPERTIES_FILE);

    public void setup(){
        driver = createDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(resources.getProperty(IMPLICIT_WAIT)), TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(resources.getProperty(IMPLICIT_WAIT)), TimeUnit.SECONDS);
        driver.get(resources.getProperty(URL));
    }

    public void driverClose()
    {
        driver.quit();
    }

}

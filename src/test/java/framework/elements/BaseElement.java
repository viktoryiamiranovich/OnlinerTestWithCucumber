package framework.elements;

import framework.Browser;
import framework.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseElement {

    private static final String IMPLICIT_WAIT = "implicit_wait";
    private static final String PROPERTIES_FILE = "config.properties";
    private static final String CONDITION_WAIT = "condition_wait";

    WebDriver driver = Browser.driver;
    WebDriverWait wait = new WebDriverWait(driver, 5);
    By locator;
    WebElement element;
    List<WebElement> elementList;
    PropertyReader resources = new PropertyReader(PROPERTIES_FILE);

    public BaseElement(By locator){
        this.locator = locator;
    }

    public void click() {
        waitUntilPresent();
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        element.click();
    }

    public void clickAndWait() {
        click();
        waitForPageToLoad();
    }

    public void scrollToElement() {
        waitUntilPresent();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public void sendKeys(String key)
    {
        waitUntilPresent();
        getElement().sendKeys(key);
    }

    public List<WebElement> getElementList(){
        if (arePresent()) return elementList;
        else return null;
    }

    public WebElement getElement(){
        waitUntilPresent();
        return element;
    }

    public WebElement getElementByLocator(By locator) {
        return new WebDriverWait(driver, Integer.parseInt(resources.getProperty(CONDITION_WAIT)))
                .until(driver -> driver.findElement(locator));
    }

    public void moveTo(){
        Actions actions = new Actions(driver);
        waitUntilPresent();
        actions.moveToElement(getElement()).build().perform();
    }

    private boolean arePresent() {
        try {
            wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
                public Boolean apply(final WebDriver driver) {
                    try {
                        elementList = driver.findElements(locator);
                        for (WebElement el : elementList) {
                            if (el != null && el.isDisplayed()) {
                                element = el;
                                return element.isDisplayed();
                            }
                        }
                        element = driver.findElement(locator);
                    } catch (Exception e) {
                        return false;
                    }
                    return element.isDisplayed();
                }
            });
        } catch (Exception e) {
            return false;
        }
        try {
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(resources.getProperty(IMPLICIT_WAIT)), TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isPresent() {
        try {
            element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean waitUntilPresent() {
        wait.until((ExpectedCondition<Boolean>) (x) -> {
            try {
                return isPresent();
            }catch (Exception e){
                return false;
            }
        });
        try {
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(resources.getProperty(IMPLICIT_WAIT)), TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<WebElement> getElements() {
        return new WebDriverWait(driver, Integer.parseInt(resources.getProperty(IMPLICIT_WAIT)))
                .until(driver -> driver.findElements(locator));
    }


    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(resources.getProperty(IMPLICIT_WAIT))));
        try {
            wait.until((ExpectedCondition<Boolean>) d -> {
                if (!(d instanceof JavascriptExecutor)) {
                    return true;
                }
                Object result = ((JavascriptExecutor) d)
                        .executeScript("return document['readyState'] ? 'complete' == document.readyState : true");
                return result instanceof Boolean && (Boolean) result;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

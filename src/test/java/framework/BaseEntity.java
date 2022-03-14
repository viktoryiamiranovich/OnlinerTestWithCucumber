package framework;

import framework.elements.BaseElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public abstract class BaseEntity {

    private static final String PROPERTIES_FILE = "config.properties";
    private static final String CONDITION_WAIT = "condition_wait";


    protected BaseElement baseElement;
    protected By formLocator;
    protected String formName;
    public WebDriver driver = Browser.driver;
    PropertyReader resources = new PropertyReader(PROPERTIES_FILE);


    public BaseEntity(By locator, String title) {
        baseElement = new BaseElement(locator);
        init(locator, title);
        assertIsOpen();
    }

    public void init(By locator, String name){
        formLocator = locator;
        formName = name;
    }

    public void assertIsOpen() {
        try{
            baseElement.waitUntilPresent();
            Assert.assertTrue(baseElement.getElementByLocator(formLocator).isDisplayed());
        } catch (Throwable e){
            System.out.println("Form " + formName + " is not opened");
        }
    }

    public void waitForFiltering(){
            WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(resources.getProperty(CONDITION_WAIT)));
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//div[@class = 'schema-product__group']"))));

    }

}

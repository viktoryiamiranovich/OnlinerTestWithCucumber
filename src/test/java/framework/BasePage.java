package framework;

import framework.elements.BaseElement;
import org.openqa.selenium.*;
import org.testng.Assert;

public abstract class BasePage {

    protected BaseElement baseElement;
    protected By formLocator;
    protected String formName;


    public BasePage(By locator, String title) {
        baseElement = new BaseElement(locator, title);
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
}

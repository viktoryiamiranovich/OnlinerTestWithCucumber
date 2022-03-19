package onliner.pageobject;

import framework.BasePage;
import framework.elements.Label;
import org.openqa.selenium.By;


public class HomePage extends BasePage {

    private static String formName = HomePage.class.getName();
    private static String logoForCheck = "b-top-logo";
    String sectionNavLabelXpath = "//header/h2/a[contains(text(), '%s')]";

    public HomePage() {
        super(By.className(logoForCheck),formName);
    }

    public void navigateSection(String mainSectionName){
        Label sectionNameForNavigate = new Label(By.xpath(String.format(sectionNavLabelXpath, mainSectionName)));
        sectionNameForNavigate.scrollToElement();
        sectionNameForNavigate.clickAndWait();
    }

}

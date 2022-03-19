package onliner.pageobject;

import framework.BasePage;
import framework.elements.Label;
import org.openqa.selenium.By;


public class HomePage extends BasePage {

    private static String formName = HomePage.class.getName();
    private static String lblUniqueElement = "//header/h2/a[contains(%s, 'Каталог')]";
    String sectionNavLabelXpath = "//header/h2/a[contains(text(), '%s')]";

    public HomePage() {
        super(By.xpath(lblUniqueElement),formName);
    }

    public void navigateSection(String mainSectionName){
        Label sectionNameForNavigate = new Label(By.xpath(String.format(sectionNavLabelXpath, mainSectionName)), mainSectionName);
        sectionNameForNavigate.scrollToElement();
        sectionNameForNavigate.clickAndWait();
    }

}

package onliner.pageobject;

import framework.BaseEntity;
import framework.elements.Label;
import org.openqa.selenium.By;


public class HomePage extends BaseEntity {

    private static By ONLINER_LOGO = By.className("b-top-logo");
    private static String formName = HomePage.class.getName();
    String sectionNavLabelXpath = "//header/h2/a[contains(text(), '%s')]";

    public HomePage() {
        super(ONLINER_LOGO,formName);
    }


    public void navigateSection(String mainSectionName){
        Label sectionNameForNavigate = new Label(By.xpath(String.format(sectionNavLabelXpath, mainSectionName)));
        sectionNameForNavigate.scrollToElement();
        sectionNameForNavigate.clickAndWait();
    }

}

package onliner.pageobject;

import framework.BasePage;
import framework.elements.Dropdown;
import framework.elements.Label;
import org.openqa.selenium.By;

public class CatalogPage extends BasePage {

    private static String txtUniqueElement = "//div[@class = 'catalog-navigation__title'][contains(%s, 'Каталог')]";
    private static String formName = CatalogPage.class.getName();

    private String mainTitleXpath = "//span[@class = 'catalog-navigation-classifier__item-title-wrapper'][contains(text(), '%s')]";
    private String acideTitleXpath = "//div[@class='catalog-navigation-list__aside-title'][normalize-space(translate(., '\u00a0', ' '))='%s']";
    private String dropdownTitleXpath = "//span[@class='catalog-navigation-list__dropdown-title'][contains(text(), '%s')]";

    public CatalogPage(){
        super (By.xpath(txtUniqueElement), formName);
    }

    public void mainMenuNavigation(String mainTitle) {
        Label mainNavLabel = new Label(By.xpath(String.format(mainTitleXpath, mainTitle)), mainTitle);
        mainNavLabel.waitForPageToLoad();
        mainNavLabel.click();
    }

    public void subMenuNavigation(String acideTitle, String dropdownTitle) {
        Dropdown acideDropdown = new Dropdown(By.xpath(String.format(acideTitleXpath, acideTitle)), acideTitle);
        Label subsectionLabel = new Label(By.xpath(String.format(dropdownTitleXpath, dropdownTitle)), dropdownTitle);
        acideDropdown.moveTo();
        subsectionLabel.clickAndWait();
    }
}

package onliner.pageobject;

import framework.BaseEntity;
import framework.elements.Dropdown;
import framework.elements.Label;
import org.openqa.selenium.By;

public class CatalogPage extends BaseEntity {

    private static By CATALOG_PAGE_TITLE = By.className("catalog-navigation__title");
    private static String formName = CatalogPage.class.getName();

    private String mainTitleXpath = "//span[@class = 'catalog-navigation-classifier__item-title-wrapper'][contains(text(), '%s')]";
    private String acideTitleXpath = "//div[@class='catalog-navigation-list__aside-title'][normalize-space(translate(., '\u00a0', ' '))='%s']";
    private String dropdownTitleXpath = "//span[@class='catalog-navigation-list__dropdown-title'][contains(text(), '%s')]";

    public CatalogPage(){
        super (CATALOG_PAGE_TITLE, formName);
    }

    public void mainMenuNavigation(String mainTitle) {
        Label mainNavLabel = new Label(By.xpath(String.format(mainTitleXpath, mainTitle)));
        mainNavLabel.waitForPageToLoad();
        mainNavLabel.click();
    }

    public void subMenuNavigation(String acideTitle, String dropdownTitle) {
        Dropdown acideDropdown = new Dropdown(By.xpath(String.format(acideTitleXpath, acideTitle)));
        Label subsectionLabel = new Label(By.xpath(String.format(dropdownTitleXpath, dropdownTitle)));
        acideDropdown.moveTo();
        subsectionLabel.clickAndWait();
    }
}

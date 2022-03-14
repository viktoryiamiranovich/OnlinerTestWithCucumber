package onliner.pageobject;

import framework.BaseEntity;
import framework.elements.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class TVPage extends BaseEntity {

    private static By TV_PAGE_TITLE = By.xpath("//h1[contains(text(), 'Телевизоры')]");
    private static String formName = TVPage.class.getName();

    private String checkboxFilterLocator = "//div[@class='schema-filter__label']/span[contains(text(),'%s')]/../following-sibling::div//span[contains(text(),'%s')]";
    private String textboxFilterLocator = "//div[@class='schema-filter__label']/following-sibling::div//input[@placeholder='%s']";
    private String dropdownFilterLocator = "//div[@class='schema-filter__label']/span/../following-sibling::div//select[contains(@data-bind, 'value: %s')]";

    private final Label productTitle = new Label(By.className("schema-product__title"));
    private final Label productDescription = new Label(By.className("schema-product__description"));
    private final Label productPrice = new Label(By.xpath("//div[@class='schema-product__price']//span"));

    public TVPage() {
        super(TV_PAGE_TITLE, formName);
    }

    public void setCheckBoxFilter(String filterName, String filterValue) {
        CheckBox checkBoxFilter = new CheckBox(By.xpath(String.format(checkboxFilterLocator, filterName, filterValue)));
        checkBoxFilter.scrollToElement();
        checkBoxFilter.clickAndWait();
    }

    public void setTextBoxFilter(String filterName, String filterValue) {
        TextBox textBoxFilter = new TextBox(By.xpath(String.format(textboxFilterLocator, filterName)));
        textBoxFilter.scrollToElement();
        textBoxFilter.sendKeys(filterValue);
        textBoxFilter.waitForPageToLoad();
    }

    public void setDropdownFilter(String filterName, String filterValue) {
        Dropdown dropdownFilter = new Dropdown(By.xpath(String.format(dropdownFilterLocator, filterName)));
        dropdownFilter.scrollToElement();
        dropdownFilter.select(filterValue);
        dropdownFilter.waitForPageToLoad();
    }

    public void setFilter(Filters filterName, String filterValue) {
        switch(filterName) {
            case BRAND:
            case RESOLUTION:
                setCheckBoxFilter(filterName.toString(),filterValue);
                break;
            case MINDIAGONAL:
            case MAXDIAGONAL:
                setDropdownFilter(filterName.toString(),filterValue);
                break;
            case MAXPRICE:
                setTextBoxFilter(filterName.toString(),filterValue);
                break;
            default:
                break;
        }
    }

    public boolean isEachFilterResultHasTitleWithFilterValue(String filterValue) {
        waitForFiltering();
        List<WebElement> elements = productTitle.getElements();
        for (WebElement element : elements) {
            if (!element.getText().contains(filterValue)){
                return false;
            }
        }
        return true;
    }

    public boolean isEachFilterResultHasPriceByFilterValue(String priceValue) {
        List<WebElement> elements = productPrice.getElementList();
        for (WebElement element : elements){
            String str = element.getText();
            str = str.substring(0,str.length()-3);
            str = str.replace(",", ".");
            double actualPrice = Double.parseDouble(str);
            if (actualPrice > Integer.parseInt(priceValue)){
                return false;
            }
        }
        return true;
    }

    public boolean isEachFilterResultContainsResolutionValue(String filterValue) {
        List<WebElement> elements = productDescription.getElementList();
        for (WebElement element : elements) {
            if (!element.getText().contains(filterValue)){
                return false;
            }
        }
        return true;
    }

    public boolean isEachFilterResultWithinMinAndMaxRange(String minRange, String maxRange) {
        List<WebElement> elements = productDescription.getElementList();
        for (WebElement element : elements) {
            String str = element.getText();
            double actualDiagonal = Double.parseDouble(str.substring(0, str.indexOf("\"")));
            double min = Double.parseDouble(minRange.substring(0, minRange.length()-1));
            double max = Double.parseDouble(maxRange.substring(0, maxRange.length()-1));
            if (!(actualDiagonal>=min && actualDiagonal<=max)) {
                return false;
            }
        }
        return true;
    }
}

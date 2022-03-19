package onliner.stepdefinitions;

import framework.Browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import onliner.pageobject.CatalogPage;
import onliner.pageobject.Filters;
import onliner.pageobject.HomePage;
import onliner.pageobject.TVPage;
import org.testng.asserts.SoftAssert;
import java.util.HashMap;
import java.util.Map;

public class FilterTVSteps{
    Browser browser = new Browser();
    private HomePage homePage;
    private CatalogPage catalogPage;
    private TVPage tvPage;
    private static HashMap<String,String> filtersData = new HashMap<String, String>();

    @Before()
    public void openBrowser() {
        browser.setup();
    }

    @Given("User is on the Onliner Home Page")
    public void homePageIsOpened(){
        homePage = new HomePage();
    }

    @When("User clicks {string} on Onliner Home Page")
    public void userNavigateToSection(String menuSection){
        homePage.navigateSection(menuSection);
    }

    @And("User selects {string} on Catalog Page")
    public void userNavigateToCatalogPageSection(String catalogItem){
        catalogPage = new CatalogPage();
        catalogPage.mainMenuNavigation(catalogItem);
    }

    @And("moves to {string} and open {string} section")
    public void userNavigateToTVPage(String asideItem, String dropDownItem){
        catalogPage.subMenuNavigation(asideItem,dropDownItem);
        tvPage = new TVPage();
    }

    @And("User selects filters on TV page")
    public void userSelectFiltersOnProductsPage(Map<String,String> userFilters){
        userFilters.forEach((title, value) -> {
            tvPage.setFilter(Filters.valueOf(title), value);
            filtersData.put(title,value);
        });
    }

    @Then("correct search results are displayed")
    public void correctSearchResultsAreDisplayed(){
        SoftAssert softAssert = new SoftAssert();
        if (tvPage.isEmptySearchResult() == true){
            System.out.println("No result");
        } else {
            softAssert.assertTrue(tvPage.isEachFilterResultHasTitleWithFilterValue(filtersData.get("BRAND")),
                    String.format("Not all Search Results contain provided Brand %s", filtersData.get("BRAND")));
            softAssert.assertTrue(tvPage.isEachFilterResultWithinMinAndMaxRange(filtersData.get("MINDIAGONAL"), filtersData.get("MAXDIAGONAL")),
                    String.format("Not all Search Results contain Diagonal in a set range between %s and %s", filtersData.get("MINDIAGONAL"), filtersData.get("MAXDIAGONAL")));
            softAssert.assertTrue(tvPage.isEachFilterResultContainsResolutionValue(filtersData.get("RESOLUTION")),
                    String.format("Not all Search Results contain provided  %s ", filtersData.get("RESOLUTION")));
            softAssert.assertTrue(tvPage.isEachFilterResultHasPriceByFilterValue(filtersData.get("MAXPRICE")),
                    String.format("Not all Search Results have provided price %s", filtersData.get("MAXPRICE")));

            softAssert.assertAll();
        }
    }

    @After()
    public void closeBrowser() {
        browser.driverClose();
    }
}

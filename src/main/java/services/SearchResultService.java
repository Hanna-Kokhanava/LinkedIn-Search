package services;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SearchResultPage;
import pages.elements.search.items.SearchResultItem;

import java.util.List;

/**
 * Created on 22.03.2018
 */
public class SearchResultService {
    private SearchResultPage searchResultPage;
    private WebDriverWait waiter;

    private SearchResultPage getPage() {
        return searchResultPage;
    }

    public SearchResultService(WebDriver driver, WebDriverWait waiter) {
        searchResultPage = PageFactory.initElements(driver, SearchResultPage.class);
        this.waiter = waiter;
    }

    public List<SearchResultItem> getPersonBlocksList() {
        return searchResultPage.getSearchResultsBlock().getPersonContainersList();
    }

    /**
     * Click on 'All Filters' button and check 'All people filters' block appears
     */
    public boolean openAllFiltersAndCheckBlock(String blockTitleString) {
        System.out.println("Click 'All filters' button and check that filter block appears");
        WebElement allFiltersButton = getPage().getAllFiltersButton();
        waiter.until(ExpectedConditions.visibilityOf(allFiltersButton));
        allFiltersButton.click();
        WebElement blockTitleElement = getPage().getAllFiltersDropDownBlock().getAllFiltersBlockTitle();
        return blockTitleElement.isDisplayed() && blockTitleElement.getText().equals(blockTitleString);
    }

    /**
     * Click on 'Apply' button on the top bar to close dropdown filters block
     */
    public void clickApplyFiltersButton() {
        System.out.println("Click 'Apply' filter button");
        getPage().getAllFiltersDropDownBlock().getApplyFilterButton().click();
    }

    /**
     * Set 'Location' filter value
     *
     * @param locationFilterValue filter string value
     */
    public void applyLocationFilter(String locationFilterValue) {
        System.out.println("Set 'Location' filter value as " + locationFilterValue);
        WebElement filterInput = getPage().getAllFiltersDropDownBlock().getLocationsFilterContainer().getFilterInput();
        setFilterValue(filterInput, locationFilterValue);
    }

    /**
     * Set 'Industry' filter value
     *
     * @param industryFilterValue filter string value
     */
    public void applyIndustryFilter(String industryFilterValue) {
        System.out.println("Set 'Industry' filter value as " + industryFilterValue);
        WebElement filterInput = getPage().getAllFiltersDropDownBlock().getIndustryFilterContainer().getFilterInput();
        setFilterValue(filterInput, industryFilterValue);
    }

    /**
     * Type filter value, wait for DDL appears and next click 'enter'
     *
     * @param filterInput input element for filter
     * @param filterValue filter string value
     */
    private void setFilterValue(WebElement filterInput, String filterValue) {
        filterInput.click();
        filterInput.sendKeys(filterValue);
        waiter.until(ExpectedConditions.attributeContains(filterInput, "aria-expanded", "true"));
        //Is needed, because script runs too fast and close entire dropdown block
        Sleeper.sleepTightInSeconds(1);
        filterInput.sendKeys(Keys.ENTER);
    }
}

package services;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SearchResultPage;
import pages.elements.search.items.PersonSearchResultItem;
import pages.elements.common.impl.TextInput;

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
        searchResultPage = new SearchResultPage();
        searchResultPage.init(driver);
        this.waiter = waiter;
    }

    /**
     * Click on 'All Filters' button and check 'All people filter' block appears
     */
    public boolean openAllFiltersAndCheckBlock(String blockTitleString) {
        System.out.println("Click 'All filter' button and check that filter block appears");
        WebElement allFiltersButton = getPage().getAllFiltersButton();
        waiter.until(ExpectedConditions.visibilityOf(allFiltersButton));
        allFiltersButton.click();
        WebElement blockTitleElement = getPage().getAllFiltersDropDownBlock().getAllFiltersBlockTitle();
        System.out.println("Actual block element title : " + blockTitleElement.getText());
        return blockTitleElement.isDisplayed() && blockTitleElement.getText().equals(blockTitleString);
    }

    /**
     * Click on 'Apply' button on the top bar to close dropdown filter block
     */
    public void clickApplyFiltersButton() {
        System.out.println("Click 'Apply' filter button");
        getPage().getAllFiltersDropDownBlock().getApplyFilterButton().click();
    }

    public List<PersonSearchResultItem> getPersonBlocksList() {
        return getPage().getPersonContainersList();
    }

    /**
     * Set 'Location' filter value
     *
     * @param locationFilterValue filter string value
     */
    public void applyLocationFilter(String locationFilterValue) {
        System.out.println("Set 'Location' filter value as " + locationFilterValue);
        TextInput filterInput = getPage().getAllFiltersDropDownBlock().getLocationsFilterContainer().getFilterInput();
        waiter.until(ExpectedConditions.visibilityOf(filterInput));
        setFilterValue(filterInput, locationFilterValue);
    }

    /**
     * Set 'Industry' filter value
     *
     * @param industryFilterValue filter string value
     */
    public void applyIndustryFilter(String industryFilterValue) {
        System.out.println("Set 'Industry' filter value as " + industryFilterValue);
        TextInput filterInput = getPage().getAllFiltersDropDownBlock().getIndustryFilterContainer().getFilterInput();
        waiter.until(ExpectedConditions.visibilityOf(filterInput));
        setFilterValue(filterInput, industryFilterValue);
    }

    /**
     * Type filter value, wait for DDL appears and next click 'enter'
     *
     * @param filterInput input element for filter
     * @param filterValue filter string value
     */
    private void setFilterValue(TextInput filterInput, String filterValue) {
        filterInput.click();
        filterInput.sendKeys(filterValue);
        waiter.until(ExpectedConditions.attributeContains(filterInput, "aria-expanded", "true"));
        //Is needed, because script runs too fast and close entire dropdown block
        Sleeper.sleepTightInSeconds(2);
        filterInput.sendKeys(Keys.ENTER);
    }
}

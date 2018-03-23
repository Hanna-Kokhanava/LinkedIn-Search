package services;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SearchResultPage;

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

    public List<WebElement> getPersonBlocksList() {
        return searchResultPage.getPeopleBlocksList();
    }

    public void applyIndustryFilter() {
        getPage().getAllFiltersButton().click();

    }

    /**
     * Click on 'Location' filter to open DDL list, print location string and press 'Enter' button
     *
     * @param locationFilterValue - needed location value
     */
    public void applyLocationSingleFilter(String locationFilterValue) {
        getPage().getLocationsFilterButton().click();
        setFilterValue(locationFilterValue);
        getPage().getSingleFilterBlock().getApplyFilterButton().click();
    }

    /**
     * Set filter string value into input element0
     *
     * @param locationFilterValue - needed location value
     */
    private void setFilterValue(String locationFilterValue) {
        WebElement filterInput = getPage().getSingleFilterBlock().getFilterInput();
        WebElement hintsBlock = getPage().getSingleFilterBlock().getFilterHintsBlock();
        filterInput.click();
        filterInput.sendKeys(locationFilterValue);
        waiter.until(ExpectedConditions.visibilityOf(hintsBlock));
        filterInput.sendKeys(Keys.ENTER);
    }

}

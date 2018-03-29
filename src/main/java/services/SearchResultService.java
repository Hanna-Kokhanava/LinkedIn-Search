package services;

import models.PersonSearchResultItem;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SearchResultPage;
import pages.elements.common.impl.Button;
import pages.elements.common.impl.TextInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created on 22.03.2018
 */
public class SearchResultService {
    private SearchResultPage searchResultPage;
    private WebDriverWait waiter;
    private WebDriver driver;

    private SearchResultPage getPage() {
        return searchResultPage;
    }

    public SearchResultService(WebDriver driver, WebDriverWait waiter) {
        searchResultPage = new SearchResultPage();
        searchResultPage.init(driver);
        this.driver = driver;
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

    /**
     * Waits for Page completely loaded
     */
    private void waitForPageLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = driver -> Objects.requireNonNull((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(pageLoadCondition);
    }

    /**
     * Returns list of {@link PersonSearchResultItem} items from all search result pages
     */
    public List<PersonSearchResultItem> getPersonInfoList() {
        List<PersonSearchResultItem> searchResultItems = new ArrayList<>();
        String contactLink, name, profession, location, additionalInfo;

        do {
            waitForPageLoad();
            List<WebElement> elements = driver.findElements(By.xpath("//li[contains(@class, 'search-result__occluded-item')]"));
            for (WebElement element : elements) {
                try {
                    Actions action = new Actions(driver);
                    action.moveToElement(element);
                    action.perform();
                    additionalInfo = "";

                    contactLink = element.findElement(By.className("search-result__result-link")).getAttribute("href");
                    name = element.findElement(By.className("actor-name")).getText();
                    profession = element.findElement(By.className("subline-level-1")).getText();
                    location = element.findElement(By.className("subline-level-2")).getText();
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    continue;
                }

                if (!profession.contains("at")) {
                    try {
                        additionalInfo = element.findElement(By.className("search-result__snippets")).getText();
                    } catch (NoSuchElementException | StaleElementReferenceException e) {
                    }
                }
                searchResultItems.add(new PersonSearchResultItem(contactLink, name, profession, location, additionalInfo));
            }

        } while (clickOnNextPageButton());

        return searchResultItems;
    }

    /**
     * Check if there is 'Next' button in Pagination bottom bar - click on it
     */
    private boolean clickOnNextPageButton() {
        WebElement nextButton;
        try {
            nextButton = getPage().getNextPageButton().getWebElement();

            Actions action = new Actions(driver);
            action.moveToElement(nextButton).perform();
        } catch (NoSuchElementException e) {
            return false;
        }
        nextButton.click();
        return true;
    }
}

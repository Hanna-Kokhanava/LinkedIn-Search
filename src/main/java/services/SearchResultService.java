package services;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SearchResultPage;
import pages.elements.common.impl.TextInput;

import java.util.List;

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

    public void getPersonInfo() {
        String contactLink;
        String name;
        String profession;
        String location;
        String additionalInfo;

        do {
            List<WebElement> elements = driver.findElements(By.xpath("//li[contains(@class, 'search-result__occluded-item')]"));
            for (WebElement element : elements) {
                try {
                    Actions action = new Actions(driver);
                    action.moveToElement(element);
                    action.perform();

                    contactLink = element.findElement(By.className("search-result__result-link")).getAttribute("href");
                    System.out.println(contactLink);
                    name = element.findElement(By.className("actor-name")).getText();
                    System.out.println(name);
                    profession = element.findElement(By.className("subline-level-1")).getText();
                    System.out.println(profession);
                    location = element.findElement(By.className("subline-level-2")).getText();
                    System.out.println(location);
                    additionalInfo = element.findElement(By.className("search-result__snippets")).getText();
                    System.out.println(additionalInfo);
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    continue;
                }

                //TODO write to list of objects
            }

            Actions action = new Actions(driver);
            action.moveToElement(getPage().getNextPageButton().getWebElement());
            action.perform();

            if (getPage().getNextPageButton().isDisplayed()) {
                getPage().getNextPageButton().click();
            } else {
                return;
            }
        } while (getPage().getNextPageButton().isDisplayed());
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

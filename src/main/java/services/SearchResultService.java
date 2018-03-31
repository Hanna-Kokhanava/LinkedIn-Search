package services;

import models.PersonSearchResultItem;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SearchResultPage;
import pages.elements.common.impl.TextInput;
import utils.reporting.ContactsInfoReport;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created on 22.03.2018
 */
public class SearchResultService {
    private SearchResultPage searchResultPage;
    private WebDriverWait waiter;
    private WebDriver driver;

    private ContactsInfoReport reportManager = new ContactsInfoReport();

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
    public void getPersonInfoList() {
        reportManager.createReportFile();
        String contactLink, name, profession, location, company;
        //With stable internet connection, after filter applying, page need time to refresh result items
        Sleeper.sleepTightInSeconds(2);

        do {
            waitForPageLoad();
            List<WebElement> elements = driver.findElements(By.xpath("//li[contains(@class, 'search-result__occluded-item')]"));
            for (WebElement element : elements) {
                try {
                    Actions action = new Actions(driver);
                    action.moveToElement(element).perform();

                    contactLink = element.findElement(By.className("search-result__result-link")).getAttribute("href");
                    name = element.findElement(By.className("actor-name")).getText();
                    //Skip element without normal member name
                    if (name.equalsIgnoreCase("LinkedIn Member")) {
                        continue;
                    }
                    profession = element.findElement(By.className("subline-level-1")).getText();
                    location = element.findElement(By.className("subline-level-2")).getText();
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    continue;
                }

                //Company name is the string after 'at' word in additional info or in profession string
                company = parseAndGetCompanyName(element, profession);
                reportPersonInfoToFile(new PersonSearchResultItem(name, company, location, contactLink));
            }

        } while (clickOnNextPageButton());
    }

    private String parseAndGetCompanyName(WebElement element, String profession) {
        String companyStrFlag = " at";
        String company;
        String additionalInfo;
        int startIndex;

        if (!profession.contains(companyStrFlag)) {
            try {
                additionalInfo = element.findElement(By.className("search-result__snippets")).getText();
                startIndex = additionalInfo.indexOf(companyStrFlag) + companyStrFlag.length();
                company = additionalInfo.substring(startIndex);
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                company = "";
            }
        } else {
            startIndex = profession.indexOf(companyStrFlag) + companyStrFlag.length();
            company = profession.substring(startIndex);
        }

        return company;
    }

    /**
     * Creates Map contains column name and its row value
     * Pass this Map to {@link ContactsInfoReport}
     *
     * @param item {@link PersonSearchResultItem} item
     */
    private void reportPersonInfoToFile(PersonSearchResultItem item) {
        Map<ContactsInfoReport.Columns, String> reportInfo = new HashMap<>();
        reportInfo.put(ContactsInfoReport.Columns.NAME, item.getName());
        reportInfo.put(ContactsInfoReport.Columns.COMPANY, item.getCompany());
        reportInfo.put(ContactsInfoReport.Columns.LOCATION, item.getLocation());
        reportInfo.put(ContactsInfoReport.Columns.LINK, item.getContactLink());
        try {
            reportManager.savePersonInfo(reportInfo);
        } catch (IOException e) {
            System.out.println("Error occured during writing info to report file");
        }
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
            nextButton.click();
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }
}

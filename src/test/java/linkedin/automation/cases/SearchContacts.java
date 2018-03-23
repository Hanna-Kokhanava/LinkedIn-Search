package linkedin.automation.cases;

import services.HomeService;
import services.SearchResultService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created on 04.03.2018
 */
public class SearchContacts extends BaseCase {
    private SearchResultService searchResultService;
    private HomeService homeService;

    private static final String SEARCH_VALUE = "HR manager";
    private static final String LOCATION_FILTER_VALUE = "Poland";

    @BeforeClass
    public void initializeServices() {
        searchResultService = new SearchResultService(driver, waiter);
        homeService = new HomeService(driver);
    }

    @Test(description = "Go to Search input and type search value")
    public void searchByValue() {
        homeService.goToSearchFormAndTypeValue(SEARCH_VALUE);
    }

    @Test(dependsOnMethods = {"searchByValue"})
    public void setFilters() throws InterruptedException {
        searchResultService.applyLocationSingleFilter(LOCATION_FILTER_VALUE);

        WebElement allFiltersButton = driver.findElement(By.xpath("//button[contains(@class, 'all-filters')]"));
        allFiltersButton.click();

        WebElement industryCheckbox = driver.findElement(By.xpath("//*[text()='Human Resources']//parent::li[contains(@class,'search-facet__value')]"));
        industryCheckbox.click();
        industryCheckbox = driver.findElement(By.xpath("//*[text()='Information Technology and Services']//parent::li[contains(@class,'search-facet__value')]"));
        industryCheckbox.click();

        WebElement applyButton = driver.findElement(By.xpath("//button[contains(@class, 'apply-button')]"));
        applyButton = driver.findElement(By.xpath("//button[contains(@class, 'button--apply')]"));
        applyButton.click();
        Sleeper.SYSTEM_SLEEPER.sleep(new Duration(5, TimeUnit.SECONDS));
    }

    @Test(dependsOnMethods = {"setFilters"})
    public void getPersonInfo() {
        searchResultService.getPersonBlocksList();

    }
}

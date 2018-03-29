package linkedin.automation.cases;

import models.PersonSearchResultItem;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import services.HomeService;
import services.SearchResultService;

import java.util.List;

/**
 * Created on 04.03.2018
 */
public class SearchContactsTest extends BaseTestCase {
    private SearchResultService searchResultService;
    private HomeService homeService;

    //TODO can be placed into property file + PropertyLoader implementation
    private static final String SEARCH_VALUE = "HR manager";
    private static final String FILTERS_BLOCK_TITLE = "All people filters";
    private static final String LOCATION_FILTER_VALUE = "Poland";
    private static final String INDUSTRY_FILTER_VALUE1 = "Information Technology and Services";
    private static final String INDUSTRY_FILTER_VALUE2 = "Human Resources";
    private static final String INDUSTRY_FILTER_VALUE3 = "Staffing and Recruiting";

    @BeforeClass
    public void initializeServices() {
        homeService = new HomeService(driver, waiter);
        searchResultService = new SearchResultService(driver, waiter);
    }

    @Test(description = "Go to Search input and type search value")
    public void goToSearchAndTypeValue() {
        homeService.goToSearchFormAndTypeValue(SEARCH_VALUE);
    }

    @Test(dependsOnMethods = {"goToSearchAndTypeValue"})
    public void setFiltersValue() {
        Assert.assertTrue(searchResultService.openAllFiltersAndCheckBlock(FILTERS_BLOCK_TITLE),
                "'All people filter block' is displayed");
        searchResultService.applyLocationFilter(LOCATION_FILTER_VALUE);
        searchResultService.applyIndustryFilter(INDUSTRY_FILTER_VALUE1);
        searchResultService.applyIndustryFilter(INDUSTRY_FILTER_VALUE2);
        searchResultService.applyIndustryFilter(INDUSTRY_FILTER_VALUE3);
        searchResultService.clickApplyFiltersButton();
    }

    @Test(dependsOnMethods = {"setFiltersValue"})
    public void getPersonInfo() {
        List<PersonSearchResultItem> personInfoList = searchResultService.getPersonInfoList();
        //TODO write fo Excel file
    }
}

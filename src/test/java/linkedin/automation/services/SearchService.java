package linkedin.automation.services;

import linkedin.automation.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created on 22.03.2018
 */
public class SearchService {
    private SearchPage searchPage;

    public SearchService(WebDriver driver) {
        searchPage = new SearchPage(driver);
    }

    public List<WebElement> getPersonBlocksList() {
        return searchPage.getPersonBlocks();
    }

}

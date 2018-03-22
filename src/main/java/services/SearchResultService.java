package services;

import pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created on 22.03.2018
 */
public class SearchResultService {
    private SearchPage searchPage;

    public SearchResultService(WebDriver driver) {
        searchPage = new SearchPage(driver);
    }

    public List<WebElement> getPersonBlocksList() {
        return searchPage.getPersonBlocks();
    }

}

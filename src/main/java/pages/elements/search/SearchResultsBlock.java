package pages.elements.search;

import org.openqa.selenium.support.FindBy;
import pages.elements.search.items.SearchResultItem;

import java.util.List;

/**
 * Created on 24.03.2018
 */
public class SearchResultsBlock {

    @FindBy(xpath = "//li[contains(@class, 'search-result')]")
    private List<SearchResultItem> personContainersList;

    public List<SearchResultItem> getPersonContainersList() {
        return personContainersList;
    }
}

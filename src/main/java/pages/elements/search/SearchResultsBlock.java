package pages.elements.search;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.search.items.PersonSearchResultItem;
import pages.elements.filter.containers.impl.AbstractContainer;

import java.util.List;

/**
 * Created on 24.03.2018
 */
public class SearchResultsBlock extends AbstractContainer {

    @FindBy(xpath = "//div[contains(@class, 'search-result--person')]")
    private List<PersonSearchResultItem> personContainersList;

    public SearchResultsBlock(WebElement webElement) {
        super(webElement);
    }

    public List<PersonSearchResultItem> getPersonContainersList() {
        return personContainersList;
    }
}

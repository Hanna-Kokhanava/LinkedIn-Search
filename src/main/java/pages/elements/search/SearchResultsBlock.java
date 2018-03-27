package pages.elements.search;

import org.openqa.selenium.support.FindBy;
import pages.elements.search.items.PersonSearchResultItem;
import utils.page_elements.containers.impl.AbstractContainer;

import java.util.List;

/**
 * Created on 24.03.2018
 */
public class SearchResultsBlock extends AbstractContainer {

    @FindBy(xpath = "//div[contains(@class, 'search-result--person')]")
    private List<PersonSearchResultItem> personContainersList;

    public List<PersonSearchResultItem> getPersonContainersList() {
        return personContainersList;
    }
}

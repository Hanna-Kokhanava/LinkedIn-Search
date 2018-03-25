package pages.elements.blocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created on 24.03.2018
 */
public class SearchResultsBlock {

    @FindBy(xpath = "//li[contains(@class, 'search-result')]")
    //TODO should be a separate element for container
    private List<WebElement> peopleContainersList;

    public List<WebElement> getPeopleContainersList() {
        return peopleContainersList;
    }
}

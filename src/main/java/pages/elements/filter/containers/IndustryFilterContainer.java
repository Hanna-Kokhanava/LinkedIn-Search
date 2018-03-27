package pages.elements.filter.containers;

import org.openqa.selenium.support.FindBy;
import utils.page_elements.containers.impl.AbstractContainer;
import utils.page_elements.elements.impl.TextInput;

/**
 * Created on 23.03.2018
 */
public class IndustryFilterContainer extends AbstractContainer {

    @FindBy(xpath = "//*[contains(@class, 'search-s-facet--facetIndustry')]//div[contains(@class,'type-ahead-input-container')]//input")
    private TextInput filterInput;

    public TextInput getFilterInput() {
        return filterInput;
    }
}

package pages.elements.filter.containers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.filter.containers.impl.AbstractContainer;
import pages.elements.common.impl.TextInput;

/**
 * Created on 23.03.2018
 */
public class IndustryFilterContainer extends AbstractContainer {

    @FindBy(xpath = "//*[contains(@class, 'search-s-facet--facetIndustry')]//div[contains(@class,'type-ahead-input-container')]//input")
    private TextInput filterInput;

    public IndustryFilterContainer(WebElement webElement) {
        super(webElement);
    }

    public TextInput getFilterInput() {
        return filterInput;
    }
}

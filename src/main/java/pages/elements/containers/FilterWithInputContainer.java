package pages.elements.containers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created on 23.03.2018
 */
public class FilterWithInputContainer {

    @FindBy(xpath = "//li[contains(@class,'search-s-add-facet')]//input")
    private WebElement filterInput;

    @FindBy(className = "search-s-facet-value")
    private List<WebElement> filterValues;

    @FindBy(className = "type-ahead-results")
    private WebElement filterHintsBlock;

    public List<WebElement> getFilterValues() {
        return filterValues;
    }

    public WebElement getFilterHintsBlock() {
        return filterHintsBlock;
    }

    public WebElement getFilterInput() {
        return filterInput;
    }
}

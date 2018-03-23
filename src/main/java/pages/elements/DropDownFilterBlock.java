package pages.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DropDownFilterBlock {

    @FindBy(xpath = "//li[contains(@class,'search-s-add-facet')]//input")
    private WebElement filterInput;

    @FindBy(className = "search-s-facet-value")
    private List<WebElement> filterValues;

    @FindBy(className = "type_ahead_results")
    private WebElement filterHintsBlock;

    public WebElement getFilterInput() {
        return filterInput;
    }

    public List<WebElement> getFilterValues() {
        return filterValues;
    }

    public WebElement getFilterHintsBlock() {
        return filterHintsBlock;
    }
}

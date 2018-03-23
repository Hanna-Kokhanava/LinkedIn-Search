package pages.elements.blocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SingleDropDownFilterBlock {

    @FindBy(xpath = "//li[contains(@class,'search-s-add-facet')]//input")
    private WebElement filterInput;

    @FindBy(className = "search-s-facet-value")
    private List<WebElement> filterValues;

    @FindBy(className = "type-ahead-results")
    private WebElement filterHintsBlock;

    @FindBy(xpath = "//button[contains(@class, 'apply-button')]")
    private WebElement applyFilterButton;

    public WebElement getFilterInput() {
        return filterInput;
    }

    public List<WebElement> getFilterValues() {
        return filterValues;
    }

    public WebElement getFilterHintsBlock() {
        return filterHintsBlock;
    }

    public WebElement getApplyFilterButton() {
        return applyFilterButton;
    }
}

package pages.elements.blocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.containers.FilterWithInputContainer;

/**
 * Created on 23.03.2018
 */
public class AllFiltersDropDownBlock {

    @FindBy(className = "search-s-facet--facetIndustry")
    private FilterWithInputContainer industryFilterContainer;

    @FindBy(className = "button--apply")
    private WebElement applyFilterButton;

    public FilterWithInputContainer getIndustryFilterContainer() {
        return industryFilterContainer;
    }

    public WebElement getApplyFilterButton() {
        return applyFilterButton;
    }
}

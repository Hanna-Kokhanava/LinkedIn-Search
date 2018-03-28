package pages.elements.filter;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.common.impl.Button;
import pages.elements.filter.containers.IndustryFilterContainer;
import pages.elements.filter.containers.LocationFilterContainer;
import pages.elements.filter.containers.impl.AbstractContainer;

/**
 * Created on 23.03.2018
 */
public class AllFiltersDropDownBlock extends AbstractContainer {

    @FindBy(className = "search-s-facet--facetIndustry")
    private IndustryFilterContainer industryFilterContainer;

    @FindBy(className = "search-s-facet--facetGeoRegion")
    private LocationFilterContainer locationsFilterContainer;

    //TODO can be joined in TopBar element
    @FindBy(id = "advanced-facets-modal-header")
    private Button allFiltersBlockTitle;

    @FindBy(xpath = "//button[contains(@class, 'button--apply')]")
    private Button applyFilterButton;

    public AllFiltersDropDownBlock(WebElement webElement) {
        super(webElement);
    }

    public Button getApplyFilterButton() {
        return applyFilterButton;
    }

    public Button getAllFiltersBlockTitle() {
        return allFiltersBlockTitle;
    }

    public LocationFilterContainer getLocationsFilterContainer() {
        return locationsFilterContainer;
    }

    public IndustryFilterContainer getIndustryFilterContainer() {
        return industryFilterContainer;
    }
}

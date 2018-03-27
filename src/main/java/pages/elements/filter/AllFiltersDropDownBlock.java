package pages.elements.filter;

import org.openqa.selenium.support.FindBy;
import pages.elements.filter.containers.IndustryFilterContainer;
import pages.elements.filter.containers.LocationFilterContainer;
import utils.page_elements.containers.impl.AbstractContainer;
import utils.page_elements.elements.impl.Button;

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

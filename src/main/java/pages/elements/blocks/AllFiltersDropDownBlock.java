package pages.elements.blocks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.elements.containers.IndustryFilterContainer;
import pages.elements.containers.LocationFilterContainer;

/**
 * Created on 23.03.2018
 */
public class AllFiltersDropDownBlock {

    @FindBy(className = "search-s-facet--facetIndustry")
    private IndustryFilterContainer industryFilterContainer;

    @FindBy(className = "search-s-facet--facetGeoRegion")
    private LocationFilterContainer locationsFilterContainer;

    //TODO can be joined in TopBar element
    @FindBy(id = "advanced-facets-modal-header")
    private WebElement allFiltersBlockTitle;

    @FindBy(xpath = "//button[contains(@class, 'button--apply')]")
    private WebElement applyFilterButton;

    public AllFiltersDropDownBlock(WebDriver driver) {
        industryFilterContainer = PageFactory.initElements(driver, IndustryFilterContainer.class);
        locationsFilterContainer = PageFactory.initElements(driver, LocationFilterContainer.class);
    }

    public WebElement getApplyFilterButton() {
        return applyFilterButton;
    }

    public WebElement getAllFiltersBlockTitle() {
        return allFiltersBlockTitle;
    }

    public LocationFilterContainer getLocationsFilterContainer() {
        return locationsFilterContainer;
    }

    public IndustryFilterContainer getIndustryFilterContainer() {
        return industryFilterContainer;
    }
}

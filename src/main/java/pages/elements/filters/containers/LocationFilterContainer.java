package pages.elements.filters.containers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created on 23.03.2018
 */
public class LocationFilterContainer {

    @FindBy(xpath = "//*[contains(@class, 'search-s-facet--facetGeoRegion')]//div[contains(@class,'type-ahead-input-container')]//input")
    private WebElement filterInput;

    public WebElement getFilterInput() {
        return filterInput;
    }
}

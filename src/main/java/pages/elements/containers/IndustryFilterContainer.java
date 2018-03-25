package pages.elements.containers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created on 23.03.2018
 */
public class IndustryFilterContainer {

    @FindBy(xpath = "//*[contains(@class, 'search-s-facet--facetIndustry')]//div[contains(@class,'type-ahead-input-container')]//input")
    private WebElement filterInput;

    public WebElement getFilterInput() {
        return filterInput;
    }
}

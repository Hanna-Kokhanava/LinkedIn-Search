package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.elements.DropDownFilterBlock;

import java.util.List;

/**
 * Created on 22.03.2018
 */
public class SearchResultPage {

    @FindBy(xpath = "//*[contains(@class,'search-s-facet__values--facetGeoRegion')]")
    private DropDownFilterBlock ddFilterBlock;

    @FindBy(xpath = "//li[contains(@class, 'search-result')]")
    private List<WebElement> peopleBlocksList;

    @FindBy(xpath = "//li[contains(@class,'GeoRegion')]")
    private WebElement locationsFilter;

    public SearchResultPage(WebDriver driver) {
        ddFilterBlock = PageFactory.initElements(driver, DropDownFilterBlock.class);
    }

    public List<WebElement> getPeopleBlocksList() {
        return peopleBlocksList;
    }

    public WebElement getLocationsFilter() {
        return locationsFilter;
    }

    public DropDownFilterBlock getDropDownFilterBlock() {
        return ddFilterBlock;
    }
}

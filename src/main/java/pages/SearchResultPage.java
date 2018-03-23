package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.elements.blocks.AllFiltersDropDownBlock;
import pages.elements.blocks.SingleDropDownFilterBlock;

import java.util.List;

/**
 * Created on 22.03.2018
 */
public class SearchResultPage {

    @FindBy(xpath = "//*[contains(@class,'search-s-facet__values--facetGeoRegion')]")
    private SingleDropDownFilterBlock singleFilterBlock;

    @FindBy(id = "artdeco-modal-outlet")
    private AllFiltersDropDownBlock allFiltersDropDownBlock;

    @FindBy(xpath = "//li[contains(@class, 'search-result')]")
    private List<WebElement> peopleBlocksList;

    @FindBy(xpath = "//li[contains(@class,'GeoRegion')]")
    private WebElement locationsFilterButton;

    @FindBy(xpath = "//button[contains(@class, 'all-filters')]")
    private WebElement allFiltersButton;

    public SearchResultPage(WebDriver driver) {
        singleFilterBlock = PageFactory.initElements(driver, SingleDropDownFilterBlock.class);
        allFiltersDropDownBlock = PageFactory.initElements(driver, AllFiltersDropDownBlock.class);
    }

    public List<WebElement> getPeopleBlocksList() {
        return peopleBlocksList;
    }

    public WebElement getLocationsFilterButton() {
        return locationsFilterButton;
    }

    public SingleDropDownFilterBlock getSingleFilterBlock() {
        return singleFilterBlock;
    }

    public AllFiltersDropDownBlock getAllFiltersDropDownBlock() {
        return allFiltersDropDownBlock;
    }

    public WebElement getAllFiltersButton() {
        return allFiltersButton;
    }
}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.elements.blocks.AllFiltersDropDownBlock;
import pages.elements.blocks.SearchResultsBlock;

/**
 * Created on 22.03.2018
 */
public class SearchResultPage {

    @FindBy(id = "artdeco-modal-outlet")
    private AllFiltersDropDownBlock allFiltersDropDownBlock;

    @FindBy(className = "search-results-page")
    private SearchResultsBlock searchResultsBlock;

    @FindBy(xpath = "//button[contains(@class, 'all-filters')]")
    private WebElement allFiltersButton;

    public SearchResultPage(WebDriver driver) {
        allFiltersDropDownBlock = PageFactory.initElements(driver, AllFiltersDropDownBlock.class);
        searchResultsBlock = PageFactory.initElements(driver, SearchResultsBlock.class);
    }

    public AllFiltersDropDownBlock getAllFiltersDropDownBlock() {
        return allFiltersDropDownBlock;
    }

    public WebElement getAllFiltersButton() {
        return allFiltersButton;
    }

    public SearchResultsBlock getSearchResultsBlock() {
        return searchResultsBlock;
    }
}

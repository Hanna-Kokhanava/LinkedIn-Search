package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.elements.filter.AllFiltersDropDownBlock;
import pages.elements.search.SearchResultsBlock;
import pages.elements.search.items.PersonSearchResultItem;
import utils.page_factory.decorator.ExtendedFieldDecorator;

import java.util.List;

/**
 * Created on 22.03.2018
 */
public class SearchResultPage implements Page {

    @FindBy(id = "artdeco-modal-outlet")
    private AllFiltersDropDownBlock allFiltersDropDownBlock;

    @FindBy(xpath = "//div[contains(@class, 'search-result--person')]")
    private List<PersonSearchResultItem> personContainersList;

    public List<PersonSearchResultItem> getPersonContainersList() {
        return personContainersList;
    }

    //TODO Can be placed on TopBar
    @FindBy(xpath = "//button[contains(@class, 'all-filter')]")
    private WebElement allFiltersButton;

    @FindBy(xpath = "//span[contains(@class, 'search-vertical-filter__dropdown-trigger-text')]")
    private WebElement searchCriteriaButton;

    @FindBy(xpath = "//button[contains(@class, 'list-item-button--PEOPLE')]")
    private WebElement peopleCriteriaButton;

    @Override
    public void init(WebDriver driver) {
        PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
    }

    public AllFiltersDropDownBlock getAllFiltersDropDownBlock() {
        return allFiltersDropDownBlock;
    }

    public WebElement getAllFiltersButton() {
        return allFiltersButton;
    }

    public WebElement getSearchCriteriaButton() {
        return searchCriteriaButton;
    }

    public WebElement getPeopleCriteriaButton() {
        return peopleCriteriaButton;
    }


}

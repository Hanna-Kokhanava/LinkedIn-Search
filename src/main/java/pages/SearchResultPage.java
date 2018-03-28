package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.elements.common.impl.Button;
import pages.elements.filter.AllFiltersDropDownBlock;
import pages.elements.search.items.PersonSearchResultItem;
import utils.decorator.ExtendedFieldDecorator;

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
    private Button allFiltersButton;

    @FindBy(xpath = "//span[contains(@class, 'search-vertical-filter__dropdown-trigger-text')]")
    private Button searchCriteriaButton;

    @FindBy(xpath = "//button[contains(@class, 'list-item-button--PEOPLE')]")
    private Button peopleCriteriaButton;

    @Override
    public void init(WebDriver driver) {
        PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
    }

    public AllFiltersDropDownBlock getAllFiltersDropDownBlock() {
        return allFiltersDropDownBlock;
    }

    public Button getAllFiltersButton() {
        return allFiltersButton;
    }

    public Button getSearchCriteriaButton() {
        return searchCriteriaButton;
    }

    public Button getPeopleCriteriaButton() {
        return peopleCriteriaButton;
    }


}

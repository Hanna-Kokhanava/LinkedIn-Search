package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.elements.common.impl.Button;
import pages.elements.filter.AllFiltersDropDownBlock;
import utils.decorator.ExtendedFieldDecorator;

/**
 * Created on 22.03.2018
 */
public class SearchResultPage implements Page {

    @FindBy(id = "artdeco-modal-outlet")
    private AllFiltersDropDownBlock allFiltersDropDownBlock;

    //TODO Can be placed on TopBar
    @FindBy(xpath = "//button[contains(@class, 'all-filter')]")
    private Button allFiltersButton;

    @FindBy(className = "next-text")
    private Button nextPageButton;

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


    public Button getNextPageButton() {
        return nextPageButton;
    }
}

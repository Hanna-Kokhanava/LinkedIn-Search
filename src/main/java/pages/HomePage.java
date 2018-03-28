package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.elements.common.impl.TextInput;
import utils.decorator.ExtendedFieldDecorator;

/**
 * Created on 22.03.2018
 */
public class HomePage implements Page {
    @FindBy(className = "nav-search-typeahead")
    private WebElement searchField;

    @FindBy(xpath = "//*[@class='nav-search-typeahead']//input")
    private TextInput searchInput;

    @Override
    public void init(WebDriver driver) {
        PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
    }

    public TextInput getSearchInput() {
        return searchInput;
    }

    public WebElement getSearchField() {
        return searchField;
    }

}

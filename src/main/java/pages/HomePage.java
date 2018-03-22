package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created on 22.03.2018
 */
public class HomePage {
    @FindBy(className = "nav-search-typeahead")
    private WebElement searchField;

    @FindBy(xpath = "//*[@class='nav-search-typeahead']//input")
    private WebElement searchInput;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getSearchInput() {
        return searchInput;
    }

    public WebElement getSearchField() {
        return searchField;
    }
}

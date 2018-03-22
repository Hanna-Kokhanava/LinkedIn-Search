package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created on 22.03.2018
 */
public class SearchPage {
    private WebDriver driver;

    @FindBy(xpath = "//li[contains(@class, 'search-result')]")
    private List<WebElement> personBlocks;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getPersonBlocks() {
        return personBlocks;
    }
}

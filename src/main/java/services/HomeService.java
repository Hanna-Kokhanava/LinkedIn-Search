package services;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;

/**
 * Created on 22.03.2018
 */
public class HomeService {
    private HomePage homePage;

    public HomeService(WebDriver driver) {
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    /**
     * Click on search field and type search criteria to the input
     * @param searchValue - search value
     */
    public void goToSearchFormAndTypeValue(String searchValue) {
        homePage.getSearchField().click();
        homePage.getSearchInput().sendKeys(searchValue);
        homePage.getSearchInput().sendKeys(Keys.RETURN);
    }
}

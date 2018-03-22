package services;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

/**
 * Created on 22.03.2018
 */
public class HomeService {
    private HomePage homePage;

    public HomeService(WebDriver driver) {
        homePage = new HomePage(driver);
    }

    /**
     * Click on search field, type search criteria to the input
     * @param searchValue - search value
     */
    public void searchByValue(String searchValue) {
        homePage.getSearchField().click();
        homePage.getSearchInput().sendKeys(searchValue);
        homePage.getSearchInput().sendKeys(Keys.RETURN);
    }
}

package services;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;

/**
 * Created on 22.03.2018
 */
public class HomeService {
    private WebDriverWait waiter;
    private HomePage homePage;

    public HomeService(WebDriver driver, WebDriverWait waiter) {
        homePage = new HomePage();
        homePage.init(driver);
        this.waiter = waiter;
    }

    /**
     * Click on search field and type search criteria to the input
     *
     * @param searchValue - search value
     */
    public void goToSearchFormAndTypeValue(String searchValue) {
        System.out.println("Type search criteria to the input");
        waiter.until(ExpectedConditions.visibilityOf(homePage.getSearchField()));
        homePage.getSearchField().click();
        homePage.getSearchInput().sendKeys(searchValue);
        homePage.getSearchInput().sendKeys(Keys.ENTER);
    }
}

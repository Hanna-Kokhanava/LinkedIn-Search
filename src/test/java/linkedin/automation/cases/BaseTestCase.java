package linkedin.automation.cases;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created on 04.03.2018
 */
public abstract class BaseTestCase {
    private static final String WIN_PATH_TO_CHROMEDRIVER = "./drivers/chromedriver 2.37.exe";
    private static final String MAC_PATH_TO_CHROMEDRIVER = "./drivers/chromedriver";
    private static final String URL = "http://www.linkedin.com";
    protected static WebDriver driver;
    protected WebDriverWait waiter;

    @BeforeSuite
    public void setUp() {
        String currentUserName = System.getProperty("user.name");
        String currentOsType = System.getProperty("os.name");

        ChromeOptions options = new ChromeOptions();
        if (currentOsType.startsWith("Mac")) {
            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, MAC_PATH_TO_CHROMEDRIVER);
            options.addArguments("user-data-dir=/Users/" + currentUserName + "/Library/Application Support/Google/Chrome/Default");
        } else {
            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, WIN_PATH_TO_CHROMEDRIVER);
            options.addArguments("user-data-dir=C:/Users/" + currentUserName + "/AppData/Local/Google/Chrome/User Data/Default");
        }
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        waiter = new WebDriverWait(driver, 15);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(URL);
        waitForPageLoad();
    }

    /**
     * Waiter for page completely loaded
     */
    private void waitForPageLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = driver -> Objects.requireNonNull((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}

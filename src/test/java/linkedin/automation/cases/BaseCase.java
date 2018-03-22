package linkedin.automation.cases;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

/**
 * Created on 04.03.2018
 */
public abstract class BaseCase {
    private static final String PATH_TO_CHROMEDRIVER_EXE = "./drivers/chromedriver.exe";
    protected static WebDriver driver;

    @BeforeSuite
    public void setUp() {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, PATH_TO_CHROMEDRIVER_EXE);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:/Users/Ania/AppData/Local/Google/Chrome/User Data/Default");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.linkedin.com");
    }

    @AfterSuite
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}

package linkedin.automation.cases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

/**
 * Created on 04.03.2018
 */
public abstract class BaseCase {
    private static final String WIN_PATH_TO_CHROMEDRIVER_EXE = "./drivers/chromedriver.exe";
    private static final String MAC_PATH_TO_CHROMEDRIVER_EXE = "./drivers/chromedriver";
    private static final String URL = "https://www.linkedin.com";
    protected static WebDriver driver;

    @BeforeSuite
    public void setUp() {
        String currentUserName = System.getProperty("user.name");
        String currentOsType = System.getProperty("os.name");

        ChromeOptions options = new ChromeOptions();
        if (currentOsType.startsWith("Mac")) {
            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, MAC_PATH_TO_CHROMEDRIVER_EXE);
            options.addArguments("user-data-dir=/Users/" + currentUserName + "/Library/Application Support/Google/Chrome/Default");
        } else {
            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, WIN_PATH_TO_CHROMEDRIVER_EXE);
            options.addArguments("user-data-dir=C:/Users/" + currentUserName + "/Ania/AppData/Local/Google/Chrome/User Data/Default");
        }
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @AfterSuite
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}

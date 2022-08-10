package support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.yaml.snakeyaml.Yaml;
import pages.LogInPage;
import pages.RegistrationPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Map;

public class TestContext {

    private static WebDriver driver;
    public static final String MATCH_REGISTRATION_URL = "https://www.match.com";


    @BeforeClass
    public void profileSetUp() {
        initialize("chrome");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    @BeforeMethod
    public void testSetUp() {
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(45));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.get(MATCH_REGISTRATION_URL);
    }

    public void initialize(String browser) {
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Driver is not implemented for: " + browser);
        }

    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static Map<String, String> getDataByFileName(String fileName) {
        String path = System.getProperty("user.dir") + "/src/main/resources/data/" + fileName + ".yaml";
        try {
            InputStream stream = new FileInputStream(path);
            return new Yaml().load(stream);
        } catch (FileNotFoundException e) {
            throw new Error(e);
        }
    }

    public RegistrationPage getHomePage() {
        return new RegistrationPage(driver);
    }

        public LogInPage getLogInPage() {
        return new LogInPage(driver);
    }

}



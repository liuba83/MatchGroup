package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.TestContext;

import java.time.Duration;
import java.util.Map;


public class LogInPage extends BasePage{

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@id='logRedirect']")
   private WebElement signInButton;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailEntry;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordEntry;

    @FindBy(xpath = "//span[text()='Sign in']")
    private WebElement logInButton;

    @FindBy(xpath = "//main[@id='mainContent']//div[contains(text(),'There is a problem')]")
    private WebElement errorMes;

    public void clickSignInButton() {
       waitVisibilityOfElement(Duration.ofSeconds(10), signInButton);
        signInButton.click();
    }

    public void fillOutLogInForm(String role) {
        Map<String, String> user = TestContext.getDataByFileName(role);
        waitVisibilityOfElement(Duration.ofSeconds(10), emailEntry);
        emailEntry.sendKeys(user.get("email"));
        passwordEntry.sendKeys(user.get("password"));
    }

    public void clickLogInButton() {
        logInButton.click();
    }

    public boolean isDisplayedErrorMes() {
        waitVisibilityOfElement(Duration.ofSeconds(7), errorMes);
        return errorMes.isDisplayed();
    }

}

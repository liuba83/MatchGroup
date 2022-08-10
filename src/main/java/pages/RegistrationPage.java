package pages;

import support.TestContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;

public class RegistrationPage extends BasePage {

    private static final String I_am_a_Man_seeking_a_Woman = "2";

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    private WebElement acceptCookies;

    @FindBy(xpath = "//select[@name='ggs']")
    private WebElement interestDropdown;

    @FindBy(xpath = "//input[@name='postalCode']")
    private WebElement zipCodeEntry;

    @FindBy(xpath = "//button[@id='viewSingles']")
    private WebElement viewSinglesButton;

    @FindBy(xpath = "//input[@id='birthDate']")
    private WebElement birthdayEntry;

    @FindBy(xpath = "//section[@id='dobStep']//button[contains(@class,'button-submit')]")
    private WebElement thatsItButton;

    @FindBy(xpath = "//input[@name='firstname']")
    private WebElement firstNameEntry;

    @FindBy(xpath = "//section[@id='firstnameStep']//button[contains(@class,'button-submit')]")
    private WebElement thatsMeButton;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailEntry;

    @FindBy(xpath = "//button[@id='FbValidMail']")
    private WebElement thatsTheOneButton;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordEntry;

    @FindBy(xpath = "//section[@id='passwordStep']//button[contains(@class,'button-submit')]")
    private WebElement passwordButton;

    @FindBy(xpath = "//div[@id='profile-capture-wrap']//button[@role='button']")
    private WebElement welcomeButton;

    @FindBy(xpath = "//a[@role='button']")
    private WebElement skipButton;

    @FindBy(xpath = "//div[contains(@id,'seek-target')]//button[@role='button']")
    private WebElement continueButton;

    @FindBy(xpath = "//span[contains(text(), 'Continue with Free')]")
    private WebElement continueFreeButton;

    @FindBy(xpath = "//button[text()='Skip']")
    private WebElement skipSurveyButton;

    @FindBy(css = ".css-h6pzbp")
    private WebElement verificationScreen;

    public void acceptCookie() {
        waitVisibilityOfElement(Duration.ofSeconds(7), acceptCookies);
        acceptCookies.click();
    }

    public void userInterest() {
        new Select(interestDropdown).selectByValue(I_am_a_Man_seeking_a_Woman);
    }

    public void setZipCode(String role) {
        Map<String, String> user = TestContext.getDataByFileName(role);
        zipCodeEntry.sendKeys(user.get("zipcode"));
    }

    public void clickViewSinglesButton() {
        viewSinglesButton.click();
    }

    public void fillOutQuestionary(String role) {
        Map<String, String> user = TestContext.getDataByFileName(role);
        waitVisibilityOfElement(Duration.ofSeconds(7), birthdayEntry);
        birthdayEntry.sendKeys(user.get("birthday"));

        thatsItButton.click();

        waitVisibilityOfElement(Duration.ofSeconds(7), firstNameEntry);
        firstNameEntry.sendKeys(user.get("firstName"));

        thatsMeButton.click();

        waitVisibilityOfElement(Duration.ofSeconds(7), emailEntry);
        String randomEmail = randomEmail();
        emailEntry.sendKeys(randomEmail);

        thatsTheOneButton.click();

        waitVisibilityOfElement(Duration.ofSeconds(7), passwordEntry);
        passwordEntry.sendKeys(user.get("password"));

        passwordButton.click();

    }

    private static String randomEmail() {
        return "random-" + UUID.randomUUID().toString() + "@gmail.com";
    }

    public void clickSkipSurveyButton() {
        if (isDisplayed(skipSurveyButton)) {
            skipSurveyButton.click();
        }
    }

    public void clickWelcomeButton() {
        waitVisibilityOfElement(Duration.ofSeconds(7), welcomeButton);
        welcomeButton.click();
    }

    public void clickSkipButton() {
        while (isDisplayed(skipButton)) {
            skipButton.click();
        }
    }

    public static boolean isDisplayed(WebElement elem) {
        try {
            WebDriverWait wait = new WebDriverWait(TestContext.getDriver(), Duration.ofSeconds(5));
            return wait.until((ExpectedCondition<Boolean>) driver -> {
                if (elem.isDisplayed()) {
                    return true;
                }
                return false;
            });
        } catch (Exception e) {
            return false;
        }
    }

    public void clickContinueButton() {
        waitElementToBeClickable(Duration.ofSeconds(5), continueButton);
        continueButton.click();
    }

    public void clickContinueWithFreeButton() {
        waitElementToBeClickable(Duration.ofSeconds(5), continueFreeButton);
        continueFreeButton.click();
    }

    public boolean isVerificationDisplayed() {
        return verificationScreen.isDisplayed();
    }

}

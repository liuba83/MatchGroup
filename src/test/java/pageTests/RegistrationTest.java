package pageTests;

import support.TestContext;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends TestContext {

    @Test
    public void verifyRegistration() {
        getHomePage().acceptCookie();
        getHomePage().userInterest();
        getHomePage().setZipCode("newUser");
        getHomePage().clickViewSinglesButton();
        getHomePage().fillOutQuestionary("newUser");
        getHomePage().clickSkipSurveyButton();
        getHomePage().clickWelcomeButton();

        getHomePage().clickSkipButton();

        getHomePage().clickContinueButton();

        getHomePage().clickSkipButton();

        getHomePage().clickContinueWithFreeButton();
        Assert.assertTrue(getHomePage().isVerificationDisplayed());
    }
}

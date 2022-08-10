package pageTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import support.TestContext;

public class LogInTest extends TestContext {

    @Test
    public void verifySignIn() {
        getHomePage().acceptCookie();
        getLogInPage().clickSignInButton();
        getLogInPage().fillOutLogInForm("registeredUser");
        getLogInPage().clickLogInButton();
        Assert.assertTrue(getLogInPage().isDisplayedErrorMes());
    }
}

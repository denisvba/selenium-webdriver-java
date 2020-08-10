package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import support.Web;

import static org.junit.Assert.*;
@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "UserInformationPageObjectsTestData.csv")
public class UserInformationPageObjectsTest {
    private WebDriver browser;

    @Before
    public void setUp(){
        browser = Web.createChrome();
    }

    @Test
    public void testAddOneAdditionalUserInfo(
            @Param (name="login")String login,
            @Param (name="password")String password,
            @Param (name="contactType")String contactType,
            @Param (name="contact")String contact,
            @Param (name="expectedMessage")String expectedMessage
    ) {
        String toastText = new LoginPage(browser)
                .clickHomeSignIn()
                .doLogin(login, password)
                .clickMe()
                .clickMoreDataAboutYouTab()
                .clickMoreAddDataAboutYouButton()
                .addContact(contactType, contact)
                .captureToastText();

        assertEquals(expectedMessage, toastText);
    }

    @After
    public void tearDown(){
        browser.quit();
    }
}

package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.Generator;
import support.Screenshot;

import java.util.concurrent.TimeUnit;

public class UserInformationTest {

    private WebDriver browser;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp(){
        // Open Browser
        //System.setProperty("webdriver.chrome.driver","~/drivers/chromedriver83/chromedriver");
        browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Navigate to Takist page
        browser.get("http://www.juliodelima.com.br/taskit");

        // Click on element with link text = "Sign in"
        browser.findElement(By.linkText("Sign in")).click();

        // Id form id="signinbox"
        WebElement formSignInBox = browser.findElement(By.id("signinbox"));

        // Type on field name="login" inside form id="signinbox" the text="julio0001"
        formSignInBox.findElement(By.name("login")).sendKeys("julio0001");

        // Type on field name="password" inside form id="signinbox" the text="123456"
        formSignInBox.findElement(By.name("password")).sendKeys("123456");

        // Click on link with text="SIGN IN"
        browser.findElement(By.linkText("SIGN IN")).click();

        // Click on link class="me"
        browser.findElement(By.className("me")).click();

        // Click on link text= "MORE DATA ABOUT YOU"
        browser.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

    }


    @Test
    public void testAddOneAdditionalUserInfo(){

        // Click on XPath = //button[@data-target='addmoredata']
        browser.findElement(By.xpath("//button[@data-target='addmoredata']")).click();

        // ID the pop up with form id="addmoredata"
        WebElement popUpAddMoreData = browser.findElement(By.id("addmoredata"));

        // Select "Phone" on combobox name="type"
        WebElement typeField = popUpAddMoreData.findElement(By.name("type"));
        new Select(typeField).selectByVisibleText("Phone");

        // Type "+5519987654321" on combobox name="contact"
        popUpAddMoreData.findElement(By.name("contact")).sendKeys("+5519987654321");

        // Click on link text="SAVE" in the popup id="addmoredata"
        popUpAddMoreData.findElement(By.linkText("SAVE")).click();

        // Validate text "Your contact has been added!" on toast message id="toast-container"
        WebElement popUpMessage = browser.findElement(By.id("toast-container"));
        String message = popUpMessage.getText();
        assertEquals("Your contact has been added!", message);
    }

    @Test
    public void testRemoveAnUserContact(){
        // Click on element with  xpath //span[text() ='+5519987654321']/following-sibling::a
        browser.findElement(By.xpath("//span[text() ='+5519987654321']/following-sibling::a")).click();

        // Validate  Javascript window
        browser.switchTo().alert().accept();

        // Validate toast message was "Rest in peace, dear phone!"
        WebElement popUpMessage = browser.findElement(By.id("toast-container"));
        String message = popUpMessage.getText();
        assertEquals("Rest in peace, dear phone!", message);

        String screenshotFilename = "./test-report/"+ Generator.dateTimeToFile() + test.getMethodName() + ".png";
        Screenshot.take(browser, screenshotFilename);

        // Wait up to 10 seconds to the window to be dismissed
        WebDriverWait wait = new WebDriverWait(browser, 10);
        wait.until(ExpectedConditions.stalenessOf(popUpMessage));

        // Click on Logout linkname= "logout"
        browser.findElement(By.linkText("Logout")).click();
    }

    @After
    public void TearDown(){
        // Close the browser
        /browser.quit(); // close all tabs
        // browser.close(); // close only current tab
    }
}

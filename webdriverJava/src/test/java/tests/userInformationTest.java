package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class userInformationTest {

    private WebDriver browser;

    @Before
    public void setUp(){
        // Open Browser
        //System.setProperty("webdriver.chrome.driver","~/drivers/chromedriver83/chromedriver");
        browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Navigate to Takist page
        browser.get("http://www.juliodelima.com.br/taskit");
    }


    @Test
    public void testAddOneAdditionalUserInfo(){

        // Click on link with text="Sign in"
        browser.findElement(By.linkText("Sign in")).click();

        // Instantiate a WebElement object
        // WebElement linkSignIn = browser.findElement(By.linkText("Sign in"));
        // linkSignIn.click();

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

    @After
    public void TearDown(){
        // Close the browser
        // browser.quit(); // close all tabs
        // browser.close(); // close only current tab
    }
}

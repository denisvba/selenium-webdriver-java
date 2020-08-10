package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Web {
    public static WebDriver createChrome() {
        // Open Browser
        //System.setProperty("webdriver.chrome.driver","~/drivers/chromedriver83/chromedriver");
        WebDriver browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Navigate to Takist page
        browser.get("http://www.juliodelima.com.br/taskit");

        return browser;
    }
}

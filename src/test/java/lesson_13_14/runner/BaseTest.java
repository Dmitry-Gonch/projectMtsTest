package lesson_13_14.runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    protected void beforeMethod() {
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
    }

    @AfterMethod
    protected void afterMethod() {
        driver.quit();
    }
}

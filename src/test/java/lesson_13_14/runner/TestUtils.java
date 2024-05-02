package lesson_13_14.runner;

import lesson_13_14.model.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getDriver;

public class TestUtils {
    public static void enterDataClickContinue(BaseTest baseTest, WebDriver driver) {
        new HomePage(driver)
                .clickAcceptCookies()
                .enterPhoneNumber()
                .enterAmount()
                .enterEmail()
                .clickContinueButton();
    }

    public static void switchToFrame(WebDriver driver, WebDriverWait wait) {
        WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("iframe.bepaid-iframe")));
        driver.switchTo().frame(frame);
    }
}

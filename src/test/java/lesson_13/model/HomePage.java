package lesson_13.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @FindBy(xpath = "//button[@id = 'cookie-agree']")
    private WebElement acceptButton;

    @FindBy(xpath = "//form[@id = 'pay-connection']/descendant::button")
    private WebElement continueButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickAcceptCookies() {
        acceptButton.click();

        return new HomePage(getDriver());
    }

    public String getActualBlockName() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class = 'pay__wrapper']/h2"))).getText();
    }

    public Boolean isPresenceOfLogos() {
        return getWait5().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//*[@class = 'pay__partners']/ul"))).isEmpty();
    }

    public AboutTheServicePage clickLink() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href = '/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']"))).click();

        return new AboutTheServicePage(getDriver());
    }

    public HomePage enterPhoneNumber() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("connection-phone"))).sendKeys("297777777");

        return this;
    }

    public HomePage enterAmount() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("connection-sum"))).sendKeys("10");

        return this;
    }

    public HomePage enterEmail() {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("connection-email"))).sendKeys("test@test.com");

        return this;
    }

    public HomePage clickContinueButton() {
        continueButton.click();

        return this;
    }

    public String getTitleOpenFrame() {
        WebElement frame = getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("iframe.bepaid-iframe")));
        getDriver().switchTo().frame(frame);

        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("span.pay-description__text"))).getText();
    }
}

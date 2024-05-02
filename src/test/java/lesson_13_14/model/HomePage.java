package lesson_13_14.model;

import lesson_13_14.runner.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

    private final static String SUM = "10.00";
    private final static String NUMBER_PHONE = "297777777";

    @FindBy(xpath = "//button[@id = 'cookie-agree']")
    private WebElement acceptButton;

    @FindBy(xpath = "//form[@id = 'pay-connection']/descendant::button")
    private WebElement continueButton;

    @FindBy(className = "select__header")
    private WebElement dropDownMenu;

    @FindBy(xpath = "//p[text() = 'Услуги связи']")
    private WebElement communicationServices;

    @FindBy(xpath = "//p[text() = 'Домашний интернет']")
    private WebElement homeInternet;

    @FindBy(xpath = "//p[text() = 'Рассрочка']")
    private WebElement installmentPlan;

    @FindBy(xpath = "//p[text() = 'Задолженность']")
    private WebElement debt;

    @FindBy(xpath = "//*[@id = 'pay-connection']//input")
    private List<WebElement> placeholderCommunicationServices;

    @FindBy(xpath = "//*[@id = 'pay-internet']//input")
    private List<WebElement> placeholderHomeInternet;

    @FindBy(xpath = "//*[@id = 'pay-instalment']//input")
    private List<WebElement> placeholderInstallmentPlan;

    @FindBy(xpath = "//*[@id = 'pay-arrears']//input")
    private List<WebElement> placeholderDebt;

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
                By.xpath("//div[@class = 'pay__partners']/ul/li"))).isEmpty();
    }

    public AboutTheServicePage clickLink() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href = '/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']"))).click();

        return new AboutTheServicePage(getDriver());
    }

    public HomePage enterPhoneNumber() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("connection-phone"))).sendKeys(NUMBER_PHONE);

        return this;
    }

    public HomePage enterAmount() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("connection-sum"))).sendKeys(SUM);

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
        TestUtils.switchToFrame(getDriver(), getWait2());

        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("span.pay-description__text"))).getText();
    }

    public HomePage selectCommunicationServices() {
        dropDownMenu.click();
        communicationServices.click();

        return this;
    }

    public HomePage selectHomeInternet() {
        dropDownMenu.click();
        homeInternet.click();

        return this;
    }

    public HomePage selectInstallmentPlan() {
        dropDownMenu.click();
        installmentPlan.click();

        return this;
    }

    public HomePage selectDept() {
        dropDownMenu.click();
        debt.click();

        return this;
    }

    public List<String> getPlaceholdersCommunicationServices() {

        return placeholderCommunicationServices
                .stream()
                .map(element -> element.getAttribute("placeholder"))
                .collect(Collectors.toList());
    }

    public List<String> getPlaceholdersHomeInternet() {

        return placeholderHomeInternet
                .stream()
                .map(element -> element.getAttribute("placeholder"))
                .collect(Collectors.toList());
    }

    public List<String> getPlaceholdersInstallmentPlan() {

        return placeholderInstallmentPlan
                .stream()
                .map(element -> element.getAttribute("placeholder"))
                .collect(Collectors.toList());
    }

    public List<String> getPlaceholdersDebt() {

        return placeholderDebt
                .stream()
                .map(element -> element.getAttribute("placeholder"))
                .collect(Collectors.toList());
    }

    public String getAmountTitleOpenFrame() {
        TestUtils.switchToFrame(getDriver(), getWait2());

        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.pay-description__cost"))).getText();
    }

    public Boolean isPresenceLogosOpenFrame() {
        TestUtils.switchToFrame(getDriver(), getWait2());

        return getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//img[@class = 'ng-tns-c60-0 ng-star-inserted']"))).isEmpty();
    }

    public String getNumberPhoneOpenFrame() {
        TestUtils.switchToFrame(getDriver(), getWait2());

        String text = getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("span.pay-description__text"))).getText();

        String[] words = text.split(" ");
        String actualNumberPhone = "";
        for (String word : words) {
            if (word.contains("Номер:375" + NUMBER_PHONE)) {
                actualNumberPhone = word;
            }
        }
        return actualNumberPhone;
    }

    public String getAmountOnTheButton() {
        TestUtils.switchToFrame(getDriver(), getWait2());

        String sum = getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='card-page__card']/button"))).getText();

        String[] words = sum.split(" ");
        String actualSum = "";
        for (String word : words) {
            if (word.contains(SUM)) {
                actualSum = word;
            }
        }
        return actualSum;
    }

    public List<String> getPlaceholdersInTheFrame() {
        TestUtils.switchToFrame(getDriver(), getWait2());

        List<WebElement> elements = getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@class = 'row ng-tns-c60-0 ng-star-inserted']")));

        List<String> actualList = new ArrayList<>();
        for (WebElement element : elements) {

            new Actions(getDriver())
                    .pause(500)
                    .moveToElement(element)
                    .pause(500)
                    .perform();

            actualList.add(element.getText());
        }

        return actualList;
    }
}


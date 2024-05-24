package lesson_18.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

    @FindBy(xpath = "//button[@id = 'cookie-agree']")
    private WebElement acceptButton;

    @FindBy(className = "select__header")
    private WebElement dropDownMenu;

    @FindBy(xpath = "//p[text() = 'Услуги связи']")
    private WebElement communicationServices;

    @FindBy(xpath = "//*[@id = 'pay-connection']//input")
    private List<WebElement> placeholderCommunicationServices;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickAcceptCookies() {
        acceptButton.click();

        return new HomePage(getDriver());
    }

    public HomePage selectCommunicationServices() {
        dropDownMenu.click();
        communicationServices.click();

        return this;
    }

    public List<String> getPlaceholdersCommunicationServices() {

        return placeholderCommunicationServices
                .stream()
                .map(element -> element.getAttribute("placeholder"))
                .collect(Collectors.toList());
    }
}

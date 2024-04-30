package lesson_13.model;

import org.openqa.selenium.WebDriver;

public class AboutTheServicePage extends BasePage{
    public AboutTheServicePage(WebDriver driver) {
        super(driver);
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
}

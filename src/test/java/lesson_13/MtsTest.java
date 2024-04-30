package lesson_13;

import lesson_13.model.HomePage;
import lesson_13.runner.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MtsTest extends BaseTest {

    @Test
    public void testCheckBlockName() {
        final String expectedBlockName = "Онлайн пополнение\n" + "без комиссии";

        String getBlockName = new HomePage(getDriver())
                .clickAcceptCookies()
                .getActualBlockName();

        Assert.assertEquals(getBlockName, expectedBlockName);
    }

    @Test
    public void testCheckForLogos() {
        Boolean isLogos = new HomePage(getDriver())
                .clickAcceptCookies()
                .isPresenceOfLogos();

        Assert.assertFalse(isLogos);
    }

    @Test
    public void testCheckLink() {
        final String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";

        String currentUrl = new HomePage(getDriver())
                .clickAcceptCookies()
                .clickLink()
                .getCurrentUrl();

        Assert.assertEquals(currentUrl, expectedUrl);
    }

    @Test
    public void testCheckTheContinueButton() {
        final String expectedTitle = "Оплата: Услуги связи Номер:375297777777";

        String actualTitle = new HomePage(getDriver())
                .clickAcceptCookies()
                .enterPhoneNumber()
                .enterAmount()
                .enterEmail()
                .clickContinueButton()
                .getTitleOpenFrame();

        Assert.assertEquals(actualTitle, expectedTitle);
    }
}

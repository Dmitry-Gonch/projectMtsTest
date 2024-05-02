package lesson_13_14;

import lesson_13_14.model.HomePage;
import lesson_13_14.runner.BaseTest;
import lesson_13_14.runner.TestUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Lesson_14 extends BaseTest {

    private final static String SUMMA = "Сумма";
    private final static String EMAIL_FOR_CHECK = "E-mail для отправки чека";
    private final static String NUMBER = "Номер";

    @Test
    public void testCheckPlaceholdersForCommunicationService() {
        final List<String> expectedPlaceholders = List.of(
                NUMBER + " телефона",
                SUMMA,
                EMAIL_FOR_CHECK
        );

        List<String> actualPlaceholders = new HomePage(getDriver())
                .clickAcceptCookies()
                .selectCommunicationServices()
                .getPlaceholdersCommunicationServices();

        Assert.assertEquals(actualPlaceholders, expectedPlaceholders);
    }

    @Test
    public void testCheckPlaceholdersForHomeInternet() {
        final List<String> expectedPlaceholders = List.of(
                NUMBER + " абонента",
                SUMMA,
                EMAIL_FOR_CHECK
        );

        List<String> actualPlaceholders = new HomePage(getDriver())
                .clickAcceptCookies()
                .selectHomeInternet()
                .getPlaceholdersHomeInternet();

        Assert.assertEquals(actualPlaceholders, expectedPlaceholders);
    }

    @Test
    public void testCheckPlaceholdersForInstallmentPlan() {
        final List<String> expectedPlaceholders = List.of(
                NUMBER + " счета на 44",
                SUMMA,
                EMAIL_FOR_CHECK
        );

        List<String> actualPlaceholders = new HomePage(getDriver())
                .clickAcceptCookies()
                .selectInstallmentPlan()
                .getPlaceholdersInstallmentPlan();

        Assert.assertEquals(actualPlaceholders, expectedPlaceholders);
    }

    @Test
    public void testCheckPlaceholdersForDebt() {
        final List<String> expectedPlaceholders = List.of(
                NUMBER + " счета на 2073",
                SUMMA,
                EMAIL_FOR_CHECK
        );

        List<String> actualPlaceholders = new HomePage(getDriver())
                .clickAcceptCookies()
                .selectDept()
                .getPlaceholdersDebt();

        Assert.assertEquals(actualPlaceholders, expectedPlaceholders);
    }

    @Test
    public void testCheckAmountTitleDisplay() {
        final String expectedAmountTitle = "10.00 BYN";

        TestUtils.enterDataClickContinue(this, getDriver());
        String actualAmountTitle = new HomePage(getDriver())
                .getAmountTitleOpenFrame();

        Assert.assertEquals(actualAmountTitle, expectedAmountTitle);
    }

    @Test
    public void testCheckAmountOnTheButton() {
        final String expectedAmountOnTheButton = "10.00";

        TestUtils.enterDataClickContinue(this, getDriver());
        String actualAmountOnTheButton = new HomePage(getDriver())
                .getAmountOnTheButton();

        Assert.assertEquals(actualAmountOnTheButton, expectedAmountOnTheButton);
    }

    @Test
    public void testCheckNumberTitleDisplay() {
        final String expectedNumberPhone = "Номер:375297777777";

        TestUtils.enterDataClickContinue(this, getDriver());
        String actualNumberPhone = new HomePage(getDriver())
                .getNumberPhoneOpenFrame();

        Assert.assertEquals(actualNumberPhone, expectedNumberPhone);
    }

    @Test
    public void testCheckPlaceholdersInTheFrame() {
        final List<String> expectedPlaceholders = List.of(
                "Номер карты",
                "Срок действия\nCVC",
                "Имя держателя (как на карте)"
        );

        TestUtils.enterDataClickContinue(this, getDriver());
        List<String> actualPlaceholders = new HomePage(getDriver())
                .getPlaceholdersInTheFrame();

        Assert.assertEquals(actualPlaceholders, expectedPlaceholders);
    }

    @Test
    public void testCheckFramedLogos() {
        TestUtils.enterDataClickContinue(this, getDriver());

        Boolean isLogos = new HomePage(getDriver())
                .isPresenceLogosOpenFrame();

        Assert.assertFalse(isLogos);
    }
}

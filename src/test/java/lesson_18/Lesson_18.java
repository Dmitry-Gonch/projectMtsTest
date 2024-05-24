package lesson_18;

import io.qameta.allure.*;
import lesson_13_14.model.HomePage;
import lesson_18.runner.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Lesson_18 extends BaseTest {

    @Test
    @Owner("Иванов Иван")
    @Epic("Автотест для сайта mts.by")
    @Feature("Проверка плейсхолдеров полей для Услуги связи")
    @Story("Пользователь должен иметь возможность видеть плейсхолдеры для правильного заполнения полей")
    @Description("Allure Framework")
    @Severity(SeverityLevel.MINOR)
    public void testCheckPlaceholdersForCommunicationService() {

        final List<String> expectedPlaceholders = List.of(
                "Номер телефона",
                "Сумма",
                "E-mail для отправки чека"
        );

        List<String> actualPlaceholders = new HomePage(getDriver())
                .clickAcceptCookies()
                .selectCommunicationServices()
                .getPlaceholdersCommunicationServices();

        Assert.assertEquals(actualPlaceholders, expectedPlaceholders);
    }
}

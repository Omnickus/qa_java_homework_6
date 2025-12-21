package tests;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import factory.WebDriverSetup;
import pages.MainPage;

// Для логирования
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainPageTest extends WebDriverSetup {

    private static final Logger logger = LogManager.getLogger(MainPageTest.class);

    @Test
    public void testFillingFormAndSend() {
        logger.info("Тест: Заполнение формы и отправка");

        String username = System.getProperty("login", "Test1");
        String password = System.getProperty("password", "test_password");
        String email = "test@test.ru";
        String brithDate = "11.11.2025";
        String text_btith_date_for_check = LocalDate.parse(
            brithDate, DateTimeFormatter.ofPattern("dd.MM.yyyy")
        ).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.sendKeysUsername(username)
            .sendKeysEmail(email)
            .sendKeysPassword(password)
            .sendKeysPasswordConfirm(password)
            .sendKeysBirthDate(brithDate)
            .sendKeysLanguageProficiencyLevel("Продвинутый");

        mainPage.clickRegistration().findOutputDataAfterRegistrationAndCheck(username, email, text_btith_date_for_check);

    }

}

package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import driver.WebDriverSetup;
import pages.MainPage;

// Для логирования
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainPageTest extends WebDriverSetup {

    private Logger logger = LogManager.getLogger(MainPageTest.class);

    MainPage mainPage;

    @Test
    public void testFillingFormAndSend() {
        logger.info("Тест: Заполнение формы и отправка");

        String username = System.getProperty("login", "Test1");
        String password = System.getProperty("password", "test_password");
        String email = "test@test.ru";
        String brithDate = "11.11.2025";
        String[] parts = brithDate.split("\\.");
        String text_btith_date_for_check = String.format("%s-%s-%s", parts[2], parts[1], parts[0]);

        MainPage mainPage = new MainPage(driver);
        mainPage.open()
            .sendKeysUsername(username)
            .sendKeysEmail(email)
            .sendKeysPassword(password)
            .sendKeysPasswordConfirm(password)
            .sendKeysBirthDate(brithDate);
        
        WebElement option = mainPage.sendKeysLanguageProficiencyLevel("Продвинутый");
        WebElement output_data = mainPage.clickRegistration()
            .findOutputDataAfterRegistration();

        String output_data_inner_text = output_data.getText();

        assertTrue(output_data_inner_text.contains(String.format("Имя пользователя: %s", username)),
                "Неверное имя пользвоателя в окне с выводом данных");
        assertTrue(output_data_inner_text.contains(String.format("Электронная почта: %s", email)),
                "Неверное электронная почта пользвоателя в окне с выводом данных");
        assertTrue(output_data_inner_text.contains(String.format("Дата рождения: %s", text_btith_date_for_check)),
                "Неверная дата рождения пользвоателя в окне с выводом данных");
        assertTrue(output_data_inner_text.contains(String.format("Уровень языка: %s", option.getAttribute("value"))),
                "Не верный уровень языка пользвоателя в окне с выводом данных");

    }

}

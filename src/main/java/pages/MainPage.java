package pages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Для логирования
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.AbsBasePage;

public class MainPage extends AbsBasePage {

    private static final Logger logger = LogManager.getLogger(MainPage.class);

    // Известные элементы
    private String xpathLanguageLevelDropdown = "//*[@id='language_level']";

    @FindBy(css = "#username")
    private WebElement elementUsername;
    
    @FindBy(css = "#email")
    private WebElement elementEmail;
    
    @FindBy(css = "#password")
    private WebElement elementPassword;
    
    @FindBy(css = "#confirm_password")
    private WebElement elementConfirmPassword;
    
    @FindBy(css = "#birthdate")
    private WebElement elementBirthDate;
    
    @FindBy(css = "#registrationForm input[type='submit']")
    private WebElement elementSubmitButton;
    
    @FindBy(css = "#output")
    private WebElement elementOutputDiv;

    // Вспомогательные переменные
    private String selected_language_level;

    public MainPage(WebDriver driver) {
        super(driver, "form.html");
        PageFactory.initElements(driver, this);
    }

    //* Заполнить поле имя пользователя */
    public MainPage sendKeysUsername(String username) {
        elementUsername.clear();
        logger.info("Отчистил поле 'Имя пользователя' перед вводом {}", username);
        elementUsername.sendKeys(username);
        assertEquals(username, elementUsername.getAttribute("value"), "Имя пользователя должно совпадать");
        logger.info("Заполнил поле 'Имя пользователя' значением: {}", username);
        return this;
    }

    //* Заполнить поле Электронная почта */
    public MainPage sendKeysEmail(String text_email) {
        elementEmail.clear();
        logger.info("Отчистил поле 'Имя пользователя' перед вводом {}", text_email);
        elementEmail.sendKeys(text_email);
        assertEquals(text_email, elementEmail.getAttribute("value"), "Email должен совпадать");
        logger.info("Заполнил поле 'Электронная почта' значением: {}", text_email);
        return this;
    }

    //* Заполнить поле Пароль */
    public MainPage sendKeysPassword(String password) {
        elementPassword.clear();
        logger.info("Отчистил поле 'Пароль' перед вводом");
        elementPassword.sendKeys(password);
        logger.info("Заполнил поле 'Пароль'");
        return this;
    }

    //* Заполнить поле Подтвердите пароль */
    public MainPage sendKeysPasswordConfirm(String password) {
        elementConfirmPassword.clear();
        logger.info("Отчистил поле 'Подтвердите пароль' перед вводом");
        elementConfirmPassword.sendKeys(password);
        logger.info("Заполнил поле 'Подтвердите пароль'");
        return this;
    }

    //* Заполнить поле Дата рождения */
    public MainPage sendKeysBirthDate(String brith_date) {
        // Разделяем строку по точке
        String[] parts = brith_date.split("\\.");
        String text_btith_date_for_check = String.format("%s-%s-%s", parts[2], parts[1], parts[0]);
        elementBirthDate.clear();
        logger.info("Отчистил поле 'Дата рождения' перед вводом");
        elementBirthDate.sendKeys(brith_date);
        assertEquals(text_btith_date_for_check, elementBirthDate.getAttribute("value"),"Дата рождения должна совпадать");
        logger.info("Заполнил поле 'Дата рождения' значением: {}", brith_date);
        return this;
    }

    //* Заполнить поле Уровень знания языка */
    public MainPage sendKeysLanguageProficiencyLevel(String level) {
        logger.info("Находим и раскрываем выпадающий список с уровнями владения языка");
        WebElement select_language_level = driver.findElement(By.xpath(this.xpathLanguageLevelDropdown));
        select_language_level.click();
        logger.info("Находим уровень '{}' и выбираем его", level);
        WebElement option = driver.findElement(By.xpath(this.xpathLanguageLevelDropdown + String.format("/option[text()='%s']", level)));
        this.selected_language_level = option.getAttribute("value");
        option.click();
        logger.info("Нажимаю на выпадающий список для его закрытия");
        select_language_level.click();
        return this;
    }

    //* Нажатие на кнопку зарегистрироваться */
    public MainPage clickRegistration() {
        logger.info("Нажимаю на кнопку 'Зарегистрироваться'");
        elementSubmitButton.click();
        return this;
    }

    //* Получить элемент OutPut формы и сравнить */
    public MainPage findOutputDataAfterRegistrationAndCheck(String username, String email, String text_btith_date_for_check) {
        String output_data_inner_text = elementOutputDiv.getText();
        logger.info("Данные в поле output:\n{}\n====================", output_data_inner_text);
        assertTrue(output_data_inner_text.contains(String.format("Имя пользователя: %s", username)),
                "Неверное имя пользвоателя в окне с выводом данных");
        assertTrue(output_data_inner_text.contains(String.format("Электронная почта: %s", email)),
                "Неверное электронная почта пользвоателя в окне с выводом данных");
        assertTrue(output_data_inner_text.contains(String.format("Дата рождения: %s", text_btith_date_for_check)),
                "Неверная дата рождения пользвоателя в окне с выводом данных");
        assertTrue(output_data_inner_text.contains(String.format("Уровень языка: %s", this.selected_language_level)),
                "Не верный уровень языка пользвоателя в окне с выводом данных");
        return this;
    }




}


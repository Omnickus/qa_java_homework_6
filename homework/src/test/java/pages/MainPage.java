package pages;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Для логирования
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import driver.WebDriverSetup;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LogManager.getLogger(WebDriverSetup.class);

    private String url = "https://otus.home.kartushin.su/form.html";

    // Известные элементы
    private String xpathUsername = "//*[@id='username']";
    private String xpathEmail = "//*[@id='email']";
    private String xpathPassword = "//*[@id='password']";
    private String xpathPasswordConfirm = "//*[@id='confirm_password']";
    private String xpathBirthDate = "//*[@id='birthdate']";

    private String xpathLanguageLevelDropdown = "//*[@id='language_level']";

    private String xpathButtonSubmitForm = "//*[@id='registrationForm']/input[@type='submit']";

    private String xpathOutputRegistrationData = "//*[@id='output']";

    //* Открыть главную страницу */
    public MainPage open() {
        driver.get(this.url);
        logger.info("Открыта страница {}", this.url);
        return this;
    }

    //* Заполнить поле имя пользователя */
    public MainPage sendKeysUsername(String username) {
        WebElement input_username = driver.findElement(By.xpath(this.xpathUsername));
        input_username.clear();
        logger.info("Отчистил поле 'Имя пользователя' перед вводом {}", username);
        input_username.sendKeys(username);
        assertEquals(username, input_username.getAttribute("value"), "Имя пользователя должно совпадать");
        logger.info("Заполнил поле 'Имя пользователя' значением: {}", username);
        return this;
    }

    //* Заполнить поле Электронная почта */
    public MainPage sendKeysEmail(String text_email) {
        WebElement input_email = driver.findElement(By.xpath(this.xpathEmail));
        input_email.clear();
        logger.info("Отчистил поле 'Имя пользователя' перед вводом {}", text_email);
        input_email.sendKeys(text_email);
        assertEquals(text_email, input_email.getAttribute("value"), "Email должен совпадать");
        logger.info("Заполнил поле 'Электронная почта' значением: {}", text_email);
        return this;
    }

    //* Заполнить поле Пароль */
    public MainPage sendKeysPassword(String password) {
        WebElement input_password = driver.findElement(By.xpath(this.xpathPassword));
        input_password.clear();
        logger.info("Отчистил поле 'Пароль' перед вводом");
        input_password.sendKeys(password);
        logger.info("Заполнил поле 'Пароль'");
        return this;
    }

    //* Заполнить поле Подтвердите пароль */
    public MainPage sendKeysPasswordConfirm(String password) {
        WebElement input_password = driver.findElement(By.xpath(this.xpathPasswordConfirm));
        input_password.clear();
        logger.info("Отчистил поле 'Подтвердите пароль' перед вводом");
        input_password.sendKeys(password);
        logger.info("Заполнил поле 'Подтвердите пароль'");
        return this;
    }

    //* Заполнить поле Дата рождения */
    public MainPage sendKeysBirthDate(String brith_date) {
        // Разделяем строку по точке
        String[] parts = brith_date.split("\\.");
        String text_btith_date_for_check = String.format("%s-%s-%s", parts[2], parts[1], parts[0]);
        WebElement input_birth_date = driver.findElement(By.xpath(this.xpathBirthDate));
        input_birth_date.clear();
        logger.info("Отчистил поле 'Дата рождения' перед вводом");
        input_birth_date.sendKeys(brith_date);
        assertEquals(text_btith_date_for_check, input_birth_date.getAttribute("value"),"Дата рождения должна совпадать");
        logger.info("Заполнил поле 'Дата рождения' значением: {}", brith_date);
        return this;
    }

    //* Заполнить поле Уровень знания языка */
    public WebElement sendKeysLanguageProficiencyLevel(String level) {
        logger.info("Находим и раскрываем выпадающий список с уровнями владения языка");
        WebElement select_language_level = driver.findElement(By.xpath(this.xpathLanguageLevelDropdown));
        select_language_level.click();
        logger.info("Находим уровень '{}' и выбираем его", level);
        WebElement option = driver.findElement(By.xpath(this.xpathLanguageLevelDropdown + String.format("/option[text()='%s']", level)));
        option.click();
        logger.info("Нажимаю на выпадающий список для его закрытия");
        select_language_level.click();
        return option;
    }

    //* Нажатие на кнопку зарегистрироваться */
    public MainPage clickRegistration() {
        logger.info("Нажимаю на кнопку 'Зарегистрироваться'");
        WebElement input_submit = driver.findElement(By.xpath(this.xpathButtonSubmitForm));
        input_submit.click();
        return this;
    }

    //* Получить элемент OutPut формы */
    public WebElement findOutputDataAfterRegistration() {
        WebElement output_data = driver.findElement(By.xpath(this.xpathOutputRegistrationData));
        String output_data_inner_text = output_data.getText();
        logger.info("Данные в поле output:\n{}\n====================", output_data_inner_text);
        return output_data;
    }




}

package driver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
// Для тестирования
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
// Для логирования
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebDriverSetup {

    private static final Logger logger = LogManager.getLogger(WebDriverSetup.class);

    public static WebDriver driver;
    private String BASE_PAGE = "https://otus.home.kartushin.su/form.html";

    @BeforeAll
    public static void driverStart() {
        logger.info("Начало инициализации драйвера");
        String browser = System.getProperty("browser");
        String cmdOptions = System.getProperty("options");
        if (browser == null) {
            logger.error("Укажите браузер для запуска в -Dbrowser");
            System.exit(1);
        } else {
            try {
                driver = WebDriverFactory.createWebDriver(browser, cmdOptions);
            } catch (Exception e) {
                logger.error("Возникла ошибка: {}", e.getMessage());
                System.out.println(e);
                System.exit(1);
            }
        }
    }

    @BeforeEach
    public void testStart() {
        logger.info("Наинаем проходить тест");
        driver.get(this.BASE_PAGE);
    }
    
    @AfterEach
    public void testCompleted() {
        logger.info("Тест завершён");
    }
    
    @AfterAll
    public static void allTestsCompleted() {
        logger.info("=== Завершение всех тестов ===");
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("Драйвер закрыт");
        }
    }

}

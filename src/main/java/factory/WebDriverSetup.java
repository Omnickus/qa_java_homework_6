package factory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
// Для тестирования
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import data.EnumWebDriverType;

// Для логирования
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebDriverSetup {

    private static final Logger logger = LogManager.getLogger(WebDriverSetup.class);

    public WebDriver driver;

    @BeforeAll
    public static void driverSetup() {
        // Теперь стал пустым (Перенёс инициализапцию драйвера в @BeforeEach)
    }

    @BeforeEach
    public void driverStart() {
        logger.info("Начало инициализации драйвера");
        String browser = System.getProperty("browser");
        String cmdOptions = System.getProperty("options");
        if (browser == null) {
            logger.error("Укажите браузер для запуска в -Dbrowser");
            System.exit(1);
        } else {
            try {
                // Проверяем, что драйвер поддерживиется
                try {
                    EnumWebDriverType.valueOf(browser.toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Не поддерживаемый тип браузера: " + browser);
                }
                
                driver = WebDriverFactory.createWebDriver(browser, cmdOptions);
            } catch (Exception e) {
                logger.error("Возникла ошибка: {}", e.getMessage());
                System.out.println(e);
                System.exit(1);
            }
        }
    }

    @AfterEach
    public void testCompleted() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("Драйвер закрыт");
        }
        logger.info("=== Завершение теста ===");
    }

    @AfterAll
    public static void testingCompleted() {
        logger.info("=== Завершение прогона тестов ===");
    }

}
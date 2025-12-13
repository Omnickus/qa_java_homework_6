package main.java.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// Для логирования
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.data.EnumWebDriverType;

public class WebDriverFactory {

    private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);

    public static WebDriver createWebDriver(String browser, String options) {

        // Проверяем, что драйвер поддерживиется
        try {
            EnumWebDriverType.valueOf(browser.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Не поддерживаемый тип браузера: " + browser);
        }

        WebDriver driver;

        logger.info("Создаём объект драйвера");
        logger.info("Выбран браузер: " + browser);

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chrome_options = new ChromeOptions();
                chrome_options.addArguments("--start-maximized");
                // Получаем опции из командной строки
                if (options != null && !options.isEmpty()) {
                    logger.info("Опции из командной строки: {}", options);
                    String[] optionsArray = options.split(",");
                    for (String option : optionsArray) {
                        chrome_options.addArguments(option.trim().toLowerCase());
                    }
                }
                driver = new ChromeDriver(chrome_options);
                break;
            case "edge":
                EdgeOptions edge_options = new EdgeOptions();
                edge_options.addArguments("--start-maximized");
                // Получаем опции из командной строки
                if (options != null && !options.isEmpty()) {
                    logger.info("Опции из командной строки: {}", options);
                    String[] optionsArray = options.split(",");
                    for (String option : optionsArray) {
                        edge_options.addArguments(option.trim().toLowerCase());
                    }
                }
                driver = new EdgeDriver(edge_options);
                break;
            default:
                throw new IllegalArgumentException("Не поддерживаемый тип браузера");
        }

        logger.info("Объект драйвера создан");
        return driver;

    }

}

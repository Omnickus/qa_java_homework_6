package main.java.pages;


import java.util.logging.LogManager;

import org.openqa.selenium.WebDriver;

public abstract class AbsBasePage {

    protected WebDriver driver;
    protected String path;

    private String basePage = System.getProperty("base.url", "https://otus.home.kartushin.su/");

    public AbsBasePage( WebDriver driver, String path) {
        this.driver = driver;
        this.path = path;
    }

    //* Открыть главную страницу */
    public void open() {
        driver.get(basePage + this.path);
    }

}

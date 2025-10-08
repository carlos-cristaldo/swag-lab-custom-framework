package com.demo.swaglabs.tests;

import com.demo.swaglabs.pages.BasePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.demo.swaglabs.utilities.GetProperty.getProperties;

public class BaseTest {

    protected WebDriver driver;
    protected BasePage basePage;

    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver();
        driver.get(getProperties("URL"));
        basePage = new BasePage(driver);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}

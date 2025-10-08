package com.demo.swaglabs.tests;

import com.demo.swaglabs.pages.LoginPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

import static com.demo.swaglabs.utilities.GetProperty.getProperties;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected SoftAssertions softAssertions = new SoftAssertions();

    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver(setChromeOptions());
        driver.manage().window().maximize();
        driver.get(getProperties("URL"));
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    //remove "Change password" pop-up
    public ChromeOptions setChromeOptions(){
        final ChromeOptions chromeOptions = new ChromeOptions();
        final Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("profile.password_manager_leak_detection", false);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        return chromeOptions;
    }
}

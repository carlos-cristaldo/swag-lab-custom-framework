package com.demo.swaglabs.tests;

import com.demo.swaglabs.pages.LoginPage;
import org.assertj.core.api.SoftAssertions;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static com.demo.swaglabs.utilities.GetProperty.getProperties;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected SoftAssertions softAssertions = new SoftAssertions();
    protected static ThreadLocal<Map<Object, Object>> threadLocal = ThreadLocal.withInitial(HashMap::new);
    protected Random random = new Random();
    Logger logger = LoggerFactory.getLogger(BaseTest.class);


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

    public ChromeOptions setChromeOptions(){
        final ChromeOptions chromeOptions = new ChromeOptions();
        final Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("profile.password_manager_leak_detection", false);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.addArguments("--headless");
        return chromeOptions;
    }

    public void errorLogger(@Nullable List<String> args){
        String msg = null;
        if (args != null) {
            msg = args.stream()
                    .collect(Collectors.joining(" | ", "", " --"));
        }
        logger.error(msg);
    }

    public void infoLogger(@Nullable Object o){
        if (o != null) {
            logger.info(o.toString());

        }
    }

    protected BigDecimal convertStringIntoBigDecimal(String stringPrice){
        BigDecimal convertedString = null;
        try {
            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
            Number number = format.parse(stringPrice);
            convertedString = new BigDecimal(number.doubleValue()).setScale(2, RoundingMode.HALF_UP);
        } catch (ParseException e) {
            errorLogger(List.of("Error parsing price string: ", e.getMessage()));
        }
        return convertedString;
    }

    public static void put(Object key, Object value) {
        threadLocal.get().put(key, value);
    }

    public static Object get(String key) {
        return threadLocal.get().get(key);
    }

    public static void hardWait(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.demo.swaglabs.tests;

import org.junit.jupiter.api.Test;

public class TestSuite extends BaseTest{

    @Test
    public void loginTest(){
        driver.get("https://www.saucedemo.com/");
    }
}

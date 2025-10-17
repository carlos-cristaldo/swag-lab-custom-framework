package com.demo.swaglabs.utilities;


import com.demo.swaglabs.tests.BaseTest;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;

import static com.demo.swaglabs.utilities.GetProperty.getProperties;

public class Scratch  {

    static BaseTest baseTest = new BaseTest();

    public static void main(String[] args) {

        baseTest.errorLogger(List.of("hola"));
    }


}

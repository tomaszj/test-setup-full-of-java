package org.tomaszjaneczko.testpoc.selenium_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by tomaszj on 12.10.2014.
 */
public class BaseTest {
    protected static String baseUrl;
    protected static WebDriver webDriver;

    @BeforeClass
    public static void setup() {
        baseUrl = "http://localhost:8000";
        webDriver = new FirefoxDriver();
    }

    @AfterClass
    public static void tearDown() {
        webDriver.quit();
    }
}

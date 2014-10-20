package org.tomaszjaneczko.testpoc.selenium_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

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

    public WebElement findElementByLocator(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(webDriver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        return wait.until(d -> d.findElement(locator));
    }
}

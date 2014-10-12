package org.tomaszjaneczko.testpoc.selenium_tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BusinessesTests extends BaseTest {

    @Test
    public void itShouldReturnBusinesses() {
        webDriver.get(baseUrl + "/businesses");

        WebElement element = webDriver.findElement(By.className("business-name"));
        String businessNameInTable = element.getText();

        assertThat("Business Name contains Business 1",
                businessNameInTable.contentEquals("Business 1"), is(true));
    }

    @Test
    public void itShouldOpenBusiness() {
        webDriver.get(baseUrl + "/businesses");

        WebElement element = webDriver.findElement(By.className("business-show-link"));
        element.click();

        final WebElement businessId = webDriver.findElement(By.className("business-id"));
        String fullBusinessId = businessId.getText();
        assertThat("Business Id contains correct ID", fullBusinessId.contains("#1"), is(true));
    }


}

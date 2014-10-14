package org.tomaszjaneczko.testpoc.selenium_tests;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BusinessesTests extends BaseTest {

    @Test
    public void itShouldReturnBusinesses() {
        webDriver.get(baseUrl + "/businesses");

        String businessNameInTable = findElementByClassName("business-name").getText();

        assertThat("Business Name contains Business 1",
                businessNameInTable.contentEquals("Business 1"), is(true));
    }

    @Test
    public void itShouldOpenBusiness() {
        webDriver.get(baseUrl + "/businesses");

        findElementByClassName("business-show-link").click();

        String fullBusinessId = findElementByClassName("business-id").getText();
        assertThat("Business Id contains correct ID", fullBusinessId.contains("#1"), is(true));
    }

    @Test
    public void itShouldAddBusiness() {
        webDriver.get(baseUrl + "/businesses");

        findElementByClassName("business-name-field").sendKeys("New name");
        findElementByClassName("business-add-button").click();

        WebElement alertElement = findElementByClassName("alert");
        assertThat("Business Id contains correct ID",
                alertElement.getText().contains("Business added successfully"), is(true));

    }

    @Test
    public void itShouldRemoveBusiness() {
        webDriver.get(baseUrl + "/businesses/3");

        findElementByClassName("business-delete-button").click();

        WebElement alertElement = findElementByClassName("alert");
        assertThat("Business removed message present",
                alertElement.getText().contains("removed"), is(true));
    }
}

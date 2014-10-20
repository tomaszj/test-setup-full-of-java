package org.tomaszjaneczko.testpoc.selenium_tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class BusinessesTests extends BaseTest {


    private static final String EXISTING_BUSINESS_NAME = "Business 1";
    private static final By BUSINESS_ROWS_LOCATOR = By.className("business-row");
    public static final By BUSINESS_ROW_NAME_LOCATOR = By.className("business-name");

    @Test
    public void itShouldReturnBusinesses() {
        webDriver.get(baseUrl + "/businesses");

        final Optional<WebElement> correctRow = findRowInTableRowsWithField(BUSINESS_ROWS_LOCATOR, BUSINESS_ROW_NAME_LOCATOR, EXISTING_BUSINESS_NAME);

        assertThat("There is a business named Business 1",
                correctRow.isPresent(), is(true));

        final List<String> businessNames = allFieldValuesInTable(BUSINESS_ROWS_LOCATOR, BUSINESS_ROW_NAME_LOCATOR);
        checkIfNamesAreSorted(businessNames);
    }

    @Test
    public void itShouldOpenBusiness() {
        webDriver.get(baseUrl + "/businesses");

        final Optional<WebElement> correctRow = findRowInTableRowsWithField(BUSINESS_ROWS_LOCATOR, BUSINESS_ROW_NAME_LOCATOR, EXISTING_BUSINESS_NAME);

        if (correctRow.isPresent()) {
            correctRow.get().findElement(By.className("business-show-link")).click();

            String fullBusinessId = findElementByLocator(By.className("business-id")).getText();
            assertThat("Business Id contains correct ID", fullBusinessId.contains("#1"), is(true));
        } else {
            fail("Business named Business 1 wasn't found");
        }
    }

    @Test
    public void itShouldAddBusiness() {
        webDriver.get(baseUrl + "/businesses");

        findElementByLocator(By.className("business-name-field")).sendKeys("New name");
        findElementByLocator(By.className("business-add-button")).click();

        WebElement alertElement = findElementByLocator(By.className("alert"));
        assertThat("Business Id contains correct ID",
                alertElement.getText().contains("Business added successfully"), is(true));

    }

    @Test
    public void itShouldNotAddExistingBusiness() {
        webDriver.get(baseUrl + "/businesses");

        findElementByLocator(By.className("business-name-field")).sendKeys("Exists");
        findElementByLocator(By.className("business-add-button")).click();

        WebElement alertElement = findElementByLocator(By.className("alert"));
        assertThat("Validation problem alert is displayed",
                alertElement.getText().contains("name already exists"), is(true));

    }

    @Test
    public void itShouldRemoveBusiness() {
        webDriver.get(baseUrl + "/businesses/2");

        findElementByLocator(By.className("business-delete-button")).click();

        WebElement alertElement = findElementByLocator(By.className("alert"));
        assertThat("Business removed message present",
                alertElement.getText().contains("removed"), is(true));
    }

    private Optional<WebElement> findRowInTableRowsWithField(By rowsLocator, By fieldLocator, String fieldValue) {
        final List<WebElement> rows = webDriver.findElements(rowsLocator);
        return rows.stream().filter(
                row -> fieldValue.equalsIgnoreCase(row.findElement(fieldLocator).getText())
        ).findFirst();
    }

    private List<String> allFieldValuesInTable(By rowsLocator, By fieldNameLocator) {
        return webDriver.findElements(rowsLocator).stream()
                .map(row -> row.findElement(fieldNameLocator).getText())
                .collect(Collectors.toList());
    }

    private void checkIfNamesAreSorted(List<String> businessNames) {
        for (int i = 0; i < businessNames.size() - 1; i++) {
            assertThat("Next row is greater than previous row", businessNames.get(i).compareTo(businessNames.get(i + 1)) <= 0, is(true));
        }
    }
}

package locations;

import extension.SeleniumTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SeleniumTest
@Tag("locations")
class LocationsTest {

//    @Test
//    void testCreate(WebDriver driver) {
//        driver.get("http://localhost:8080");
//        driver.findElement(By.linkText("Create location")).click();
//        driver.findElement(By.id("location-name")).sendKeys("Home");
//        driver.findElement(By.id("location-coords")).sendKeys("1,1");
//        driver.findElement(By.cssSelector("#location-form input.btn.btn-primary")).click();
//
//        var message = driver.findElement(By.id("message-div")).getText();
//        assertEquals("Location has been created", message);
//    }

    @Test
    void testCreate(WebDriver driver) {
        new LocationPage(driver)
            .go()
            .clickOnCreateLocation()
            .enterLocationData()
            .clickOnSubmitButton()
            .messageIs("Location has been created");
    }

    @Test
    // @RepeatedTest(10)
    void testEdit(WebDriver driver) {
        new LocationPage(driver)
                .go()
                .clickOnFirstEditButton()
                .enterLocationDataToModify()
                .clickOnModifySubmitButton()
                .messageIs("Location has been modified");

    }

    @ParameterizedTest
    @ValueSource(strings = {"Home", "Work", "Hobby", "School"})
    void testCreateWithData(String name, WebDriver driver) {
        new LocationPage(driver)
                .go()
                .clickOnCreateLocation()
                .enterLocationData(name, "1,1")
                .clickOnSubmitButton()
                .messageIs("Location has been created");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/locations.csv", numLinesToSkip = 1)
    void testCreateWithData(String name, String lat, String lon, WebDriver driver) {
        new LocationPage(driver)
                .go()
                .clickOnCreateLocation()
                .enterLocationData(name, lat + "," + lon)
                .clickOnSubmitButton()
                .messageIs("Location has been created");
    }


}

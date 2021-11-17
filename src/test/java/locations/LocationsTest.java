package locations;

import extension.SeleniumTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
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


}

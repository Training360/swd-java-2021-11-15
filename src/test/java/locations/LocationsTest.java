package locations;

import extension.SeleniumTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mariadb.jdbc.MariaDbDataSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.net.URL;
import java.sql.SQLException;

import static io.restassured.RestAssured.*;
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
    void testCreate(WebDriver driver) throws SQLException {
//        var dataSource = new MariaDbDataSource("localhost", 3306, "locations");
//        dataSource.setUser("locations");
//        dataSource.setPassword("locations");
//
//        var jdbcTemplate = new JdbcTemplate(dataSource);
//        jdbcTemplate.execute("delete from tag");
//        jdbcTemplate.execute("delete from location");

        delete("/api/locations")
                .then()
                .statusCode(204);

        String json = "{\n" +
                "                             \"name\": \"Budapest\",\n" +
                "                             \"coords\": \"47.497912,19.040235\",\n" +
                "                           }";

        given()
                .body("""
                        {
                             "name": "Budapest",
                             "coords": "47.497912,19.040235",
                           }
                        """)
                        .when()
                                .post("/api/locations")
                                        .then()
                                                .statusCode(201);


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

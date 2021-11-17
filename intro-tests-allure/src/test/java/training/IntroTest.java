package training;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntroTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
    }

//    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Story("Welcome message.")
    void testWelcomeMessage() {
        driver.get("http://localhost:80");
        driver.findElement(By.name("name")).click();
        driver.findElement(By.name("name")).sendKeys("John Doe");
        driver.findElement(By.id("welcome-button")).click();
        String message = driver.findElement(By.id("welcome-div")).getText();
        assertEquals("Hello John Doe!", message);
    }
}

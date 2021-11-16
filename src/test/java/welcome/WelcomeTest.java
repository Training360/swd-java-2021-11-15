package welcome;

import extension.SeleniumTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SeleniumTest
public class WelcomeTest {

    @Test
    void testSayHello(WebDriver driver, URL url) {
        driver.get(url + "/welcome");

        driver.findElement(By.id("name-input")).sendKeys("John Doe");
        driver.findElement(By.id("welcome-button")).click();

        var message = driver.findElement(By.id("welcome-div"))
                .getText();
        assertEquals("Hello John Doe!", message);
    }
}

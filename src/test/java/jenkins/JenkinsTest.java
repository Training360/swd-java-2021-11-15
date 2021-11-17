package jenkins;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JenkinsTest {

    @Test
    void testSayHello() throws Exception {
        var options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        // var options = new FirefoxOptions();
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);

        driver.get("https://training360.github.io/swd-java-2021-11-15/welcome");

        driver.findElement(By.id("name-input")).sendKeys("John Doe");
        driver.findElement(By.id("welcome-button")).click();

        var message = driver.findElement(By.id("welcome-div"))
                .getText();
        assertEquals("Hello John Doe!", message);

        //driver.quit();
    }
}

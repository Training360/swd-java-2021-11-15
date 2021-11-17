package cross;

import extension.SeleniumTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CrossTest {

    @Test
    @Tag("dockerdemo")
    void testSayHello() throws Exception {
        var options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        // var options = new FirefoxOptions();
        WebDriver driver = new RemoteWebDriver(new URL("http://selenium-hub:4444"), options);

        driver.get("https://training360.github.io/swd-java-2021-11-15/welcome");

        driver.findElement(By.id("name-input")).sendKeys("John Doe");
        driver.findElement(By.id("welcome-button")).click();

        var message = driver.findElement(By.id("welcome-div"))
                .getText();
        assertEquals("Hello John Doe!", message);

        //driver.quit();
    }


}

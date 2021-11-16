package extension;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
//@ExtendWith(SeleniumExtension.class)
@SeleniumTest
@LoggingTest
class SimpleTest {

    @Test
    @DisplayName("header test 1")
    void testHeader(WebDriver driver, URL url) {
        driver.get(url.toString());
        var header = driver.findElement(By.tagName("h1")).getText();
        assertEquals("Welcome in Selenium Training!", header);
    }

    @Test
    @DisplayName("header test 2")
    void testHeader2(WebDriver driver, URL url) {
        driver.get(url.toString());
        var header = driver.findElement(By.tagName("h1")).getText();
        assertEquals("Welcome in Selenium Training!", header);
    }
}

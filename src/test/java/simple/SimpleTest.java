package simple;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;


// Teszt osztály
class SimpleTest {

    // Teszt metódus = teszt eset

    @Test
    @DisplayName("Add")
    void testAdd() {
        // Given
        int a = 5;
        int b = 6;
        // When
        int c = a + b;
        // Then
        assertEquals(11, c);
    }

    @Test
    @DisplayName("Header")
    void testHeader() {
        // WebDriverManager.chromedriver().setup();

        WebDriverManager manager = WebDriverManager.chromedriver();
        manager.cachePath("C:\\utils");
        manager.setup();

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://training360.github.io/swd-java-2021-11-15/");

        System.out.println(manager.getDownloadedDriverPath());

        webDriver.quit();
    }

//    void test_header_is_correct() {
//
//    }
}

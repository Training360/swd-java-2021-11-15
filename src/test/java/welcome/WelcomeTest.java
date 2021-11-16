package welcome;

import extension.SeleniumTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SeleniumTest
class WelcomeTest {

    @Test
    void testSayHello(WebDriver driver, URL url) {
        driver.get(url + "/welcome");

        driver.findElement(By.id("name-input")).sendKeys("John Doe");
        driver.findElement(By.id("welcome-button")).click();

        var message = driver.findElement(By.id("welcome-div"))
                .getText();
        assertEquals("Hello John Doe!", message);
    }

    @Test
    void testContext(WebDriver driver, URL url) {
        driver.get(url + "/welcome");

        var outerDiv = driver.findElement(By.id("outer-div"));

        var p = outerDiv.findElement(By.tagName("p"));
        // find element meghívható bármilyen WebElement kontextusában

        assertEquals("This is a paragraph in a div.", p.getText());
    }
}

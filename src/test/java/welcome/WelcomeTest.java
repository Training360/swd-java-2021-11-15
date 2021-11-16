package welcome;

import extension.SeleniumTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void testListItems(WebDriver driver, URL url) {
        driver.get(url + "/welcome");

        var items = driver.findElements(By.tagName("li"));

//        var cookies = List.of("Pogácsa", "Kakaóscsiga", "Muffin");
//        for (int i = 0; i < items.size(); i++) {
//            assertEquals(cookies.get(i), items.get(i).getText());
//        }

//        assertEquals(List.of("Pogácsa", "Kakaóscsiga", "Muffin"),
//                items.stream().map(WebElement::getText).toList());

//        assertEquals(List.of("Pogácsa", "Kakaócsiga", "Muffin"),
//                items.stream().map(WebElement::getText).collect(Collectors.toList()));

        assertThat(items)
                .extracting(WebElement::getText)
//                .containsExactly("Pogácsa", "Kakaóscsiga", "Muffin");
                .containsExactlyInAnyOrder("Pogácsa", "Kakaócsiga", "Muffin");

        var message = "Hewllo World";
//        assertTrue(message.startsWith("Hello"));

        assertThat(message)
                .startsWith("Hello");
    }
}

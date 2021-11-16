package htmlintro;


import extension.SeleniumTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SeleniumTest
class HtmlIntroTest {

    @Test
    void testLocators(WebDriver driver, URL url) {
        driver.get(url + "/htmlintro");
//        var headerOneWebElement = driver.findElement(By.id("page-header"));
//        var headerOne = headerOneWebElement.getText();

        var headerOneText = driver.findElement(By.id("page-header")).getText();
        assertEquals("Intro html format", headerOneText);

        var emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys("example@example.com");

        var link = driver.findElement(By.partialLinkText("page"));
        assertEquals("Training360 page", link.getText());

        var headerByClass = driver.findElement(By.className("red"));
        assertEquals("Intro html format", headerByClass.getText());

        var headerTwo = driver.findElement(By.cssSelector("body > h1:nth-child(3)"));
        assertEquals("Header two", headerTwo.getText());

        var linkByTagName = driver.findElement(By.tagName("a"));
        assertEquals("Training360 page", linkByTagName.getText());

        //linkByTagName.click();

        var headerByXPath = driver.findElement(By.xpath("/html/body/h1[2]"));
        assertEquals("Header two", headerByXPath.getText());

    }

    @Test
    void testTypeToInputThenCheckValue(WebDriver driver, URL url) {
        // Given
        driver.get(url + "/htmlintro");
        var input = driver.findElement(By.id("email-input"));
        // When
        input.sendKeys("example@example.com");
        // Then
        //var text = input.getText(); // Nem működik
        //var text = input.getAttribute("value");  // Működik, de megtévesztő az elnevezése
        var text = input.getDomAttribute("value"); // Jó az elnevezés, de üres értéket ad vissza
//        var text = input.getDomProperty("value");

        assertEquals("example@example.com", text);
    }
}

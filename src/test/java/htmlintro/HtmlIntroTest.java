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

        var headerOne = driver.findElement(By.id("page-header")).getText();
        assertEquals("Intro html format", headerOne);

        var emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys("example@example.com");


    }
}

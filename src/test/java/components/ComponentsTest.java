package components;

import extension.SeleniumTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SeleniumTest
@Slf4j
public class ComponentsTest {

    @Test
    void testComponents(WebDriver driver, URL url) {
        driver.get(String.format("%s/components", url));

        var checkbox = driver.findElement(By.name("checkbox"));
        checkbox.click();
        assertTrue(checkbox.isSelected());
        assertTrue(checkbox.isEnabled());

        var disabledCheckbox = driver.findElement(By.name("disabled-checkbox"));
        assertFalse(disabledCheckbox.isEnabled());

        var select = new Select(driver.findElement(By.id("dropdown")));
        select.getOptions().forEach(e -> System.out.println(e.getText()));
        select.selectByIndex(2);
        assertEquals("Option 3", select.getFirstSelectedOption().getText());


        var multiSelect = new Select(driver.findElement(By.id("multi-select")));
        multiSelect.selectByIndex(0);
        multiSelect.selectByIndex(2);

        assertThat(multiSelect.getAllSelectedOptions())
                .extracting(WebElement::getText)
                .containsExactly("Option 1", "Option 3");

        var password = driver.findElement(By.name("password"));
        password.sendKeys("password");
        assertEquals("password", password.getDomProperty("value"));
    }


}

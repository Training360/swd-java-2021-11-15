package js;

import extension.SeleniumTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

@SeleniumTest
class JsTest {

    @Test
    void testJavaScript(WebDriver driver, URL url) throws IOException {
        driver.get(url + "/grid");
        var executor = (JavascriptExecutor) driver;

//        var javascript = """
//                          document.querySelector('tr:nth-child(2) > td:nth-child(2)').style.border = 'thin solid #ff0000';
//                          console.log("Hello World!");
//                          alert("Grrrr!");
//                        """;

        var javascript = Files.readString(Path.of("./src/test/resources/grid.js"));

        executor.executeScript(javascript);
    }
}

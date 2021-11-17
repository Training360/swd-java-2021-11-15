package wizard;

import extension.SeleniumTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SeleniumTest
class WizardTest {

    @Test
    void testNavigate(WebDriver driver, URL url) {
        driver.get(url + "/wizard");
        driver.findElement(By.linkText("Next")).click();
        driver.findElement(By.linkText("Next")).click();
        assertEquals("Wizard2", driver.getTitle());

        driver.navigate().back();
        driver.navigate().back();
        assertEquals("Index", driver.getTitle());

        driver.navigate().forward();
        driver.navigate().forward();
        assertEquals("Wizard2", driver.getTitle());
    }

    @Test
    void testRefresh(WebDriver driver, URL url) {
        driver.get(url + "/wizard/wizard2.html");
//        for (int i = 0; i < 5; i++) {
//            driver.navigate().refresh();
//        }

        IntStream.range(0, 5).forEach((i) ->  driver.navigate().refresh());

        assertEquals("6", driver.findElement(By.id("counter-div")).getText());
        var date = driver.findElement(By.id("datetime-div")).getText();
        assertThat(date).
            startsWith(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
    }

    @Test
    void testRefreshWithScreenshot(WebDriver driver, URL url) throws IOException {
        driver.get(url + "/wizard/wizard2.html");
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        var filename = "./target/screenshot-" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss"))
                + ".png";

        Files.move(file.toPath(), Path.of(filename));

        File file2 = driver.findElement(By.tagName("h1"))
                .getScreenshotAs(OutputType.FILE);
//        var filename2 = "./target/screenshot-" +
//                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss"))
//                + ".png";
        //new Random().nextInt(10);
        //var filename2 = "./target/screenshot-" + UUID.randomUUID() + ".png";
        var filename2 = String.format("./target/screenshot-%s.%s", UUID.randomUUID(), "png");
        Files.move(file2.toPath(), Path.of(filename2));
        //, StandardCopyOption.REPLACE_EXISTING
    }
}

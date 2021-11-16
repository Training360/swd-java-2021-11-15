package extension;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.net.URL;
import java.util.List;

public class SeleniumExtension implements BeforeEachCallback, BeforeAllCallback, AfterEachCallback,
        ParameterResolver {

    private WebDriver driver;

    private URL url;

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        driver.quit();
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        WebDriverManager.chromedriver().setup();

        // Beolvasom az URL-t
        String urlPrefix = System.getProperty("selenium.url");
        if (urlPrefix == null) {
            urlPrefix = "https://training360.github.io/swd-java-2021-11-15";
        }
        url = new URL(urlPrefix);
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        var options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", List.of("enable-automation"));
        driver = new ChromeDriver(options);
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == WebDriver.class
                || parameterContext.getParameter().getType() == URL.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        if (parameterContext.getParameter().getType() == WebDriver.class) {
            return driver;
        }
        if (parameterContext.getParameter().getType() == URL.class) {
            return url;
        }
        return null;
    }
}

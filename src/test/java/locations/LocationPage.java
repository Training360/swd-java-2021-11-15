package locations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationPage {

    private WebDriver driver;

    @FindBy(linkText = "Create location")
    @CacheLookup
    private WebElement createLink;

    @FindBy(id = "location-name")
    private WebElement nameInput;

    @FindBy(id = "location-coords")
    private WebElement coordsInput;

    @FindBy(id = "update-location-name")
    private WebElement modifyNameInput;

    @FindBy(id = "update-location-coords")
    private WebElement modifyCoordsInput;

    @FindBy(css = "#location-form input.btn.btn-primary")
    private WebElement submitButton;

    @FindBy(css = "#update-location-form input.btn.btn-primary")
    private WebElement modifySubmitButton;


    @FindBy(id = "message-div")
    private WebElement messageDiv;

    @FindBy(css = "table tr:nth-child(1) button.btn.btn-link")
    private  WebElement firstEditButton;

    @FindBy(css = "#location-form input.btn.btn-primary")
    private WebElement updateButton;

    public LocationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LocationPage go() {
        driver.get("http://localhost:8080");
        return this;
    }

    public LocationPage clickOnCreateLocation() {
        createLink.click();
        return this;
    }

    public LocationPage enterLocationData(String name, String coords) {
        nameInput.sendKeys(name);
        coordsInput.sendKeys(coords);
        return this;
    }

    public LocationPage enterLocationDataToModify(String name, String coords) {
        modifyNameInput.sendKeys(name);
        modifyCoordsInput.sendKeys(coords);
        return this;
    }

    public LocationPage enterLocationDataToModify() {
        enterLocationDataToModify("Home", "1,1");
        return this;
    }


    public LocationPage enterLocationData() {
        enterLocationData("Home", "1,1");
        return this;
    }

    public LocationPage clickOnSubmitButton() {
        submitButton.click();
        return this;
    }

    public LocationPage clickOnModifySubmitButton() {
        modifySubmitButton.click();
        return this;
    }

    public LocationPage messageIs(String expectedMessage) {
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(messageDiv));
        var message = messageDiv.getText();
        assertEquals(expectedMessage, message);
        return this;
    }

    public LocationPage clickOnFirstEditButton() {
        firstEditButton.click();
        return this;
    }
}

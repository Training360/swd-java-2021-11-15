class: inverse, center, middle

# Felületi tesztelés Seleniummal

---

## Selenium

* Böngésző automatizálás, tipikusan webes alkalmazások tesztelésére
* Eszközkészlet, több eszközből áll


![Selenium logo](images/selenium-logo.png)

---

## Selenium tulajdonságai

* Platformfüggetlen (Windows, Apple OS X, Linux, pl. Ubuntu)
* Képes meghajtani a különböző böngészőket is (Firefox, Internet Explorer, Safari, Opera, Chrome)
* Nyílt forráskódú, ingyenes

![Selenium logo](images/browsers_200.png)

---

## Felhasználási területek

* Funkcionális tesztelés
* GUI tesztelés
* Regressziós tesztelés
* Böngészőfüggetlenség tesztelése

![Selenium felhasználási területek](images/usage_200x.png)

---

## Selenium helye a tesztpiramisban

* E2E tesztelés
* GUI oldalról
* E2E tesztelés erőforrásigényes teszt készítés és teszt futtatás szempontjából is
    * Rendszer tesztelése a környezetével integrálva
    * Környezet megfelelő állapotban van?
    * Böngészők, virtuális ablakozó rendszer
    * Lassú
* Ne függj törékeny, gyakran változó dolgoktól, mint a GUI
    * Fragile Test Problem - változik a GUI, változtatni kell a teszteseteket

---

## Selenium alkalmazási területei

* Ha kevés teljes folyamaton átívelő, több lépésből álló E2E
tesztem van, ami a core üzleti funkcionalitást teszteli (happy path), (sanity)
* Megkérdőjelezhetőek a klasszikus alapelvek:
    * Egy teszt egy dolgot tesztel, egy dologra ellenőrzök
        * Több lépés esetén több ellenőrzés
* Ha nem törik el: jön a pénz
* Ha eltörik: nem jön a pénz

---

## Alternatívák

* Robot Framework
* Cypress
* Katalon Studio
* Protractor: Angular környezetben
* Ranorex (kereskedelmi)
* MicroFocus UFT (Unified Functional Test) (kereskedelmi)
* SikuliX
* RAPISE by Inflectra (kereskedelmi)
* SmartBear TestComplete (kereskedelmi)

---

## Eszközök

* Selenium IDE: felvétel és visszajátszás grafikus felületen
* Selenium WebDriver: böngészővezérlés (pl. programozási nyelvekből API-n keresztül)
* Selenium Grid: automatizált tesztelés több gépről, összehangoltan

![Selenium tools](images/tools_400x.png)

---

class: inverse, center, middle

# Bevezetés a WebDriver használatába

---

## Selenium IDE

* Felvétel és visszajátszás (tesztesetek lépésekkel)
* Chrome vagy FireFox Add-On
* Programozási nyelv funkciók: parancskészlet, paraméterezés, változók, vezérlési elemek
* IDE funkciók, pl. projektkezelés, autocomplete, debug
* Parancssori futtatási lehetőség

---

## Példa Selenium IDE teszt

```shell
docker build -t sut-samples .
docker run -d -p 80:80 --name my-sut-samples sut-samples
```

* Elérhető: `http://localhost/`
* Teszt neve: `Intro`
* Export: `IntroTest`

---

## Selenium WebDriver

* Böngészővezérlés programozottan
* Különböző programozási nyelvekhez illesztés: C#, Groovy, Java, Perl, PHP, Python, Ruby and Scala
* Driver böngészőnként 
  * Firefoxhoz geckodriver, https://github.com/mozilla/geckodriver
  * Chromehoz ChromeDriver, https://chromedriver.chromium.org/
* W3C WebbDriver standard, https://w3c.github.io/webdriver/webdriver-spec.html

---

## WebDriver

![WebDriver](images/webdriver_500x.png)

---

## Selenium WebDriver

* https://github.com/SeleniumHQ/selenium/tree/trunk/java
* selenium-java 4.0.0 2021. október 21.

---

## Selenium IDE Java export

* Selenium IDE képes exportálni
* JUnit 4
* Hamcrest assertions

```java
public class IntroTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void intro() {
    driver.get("http://localhost/");
    driver.manage().window().setSize(new Dimension(1553, 844));
    driver.findElement(By.name("name")).click();    
    driver.findElement(By.name("name")).sendKeys("john doe");
    driver.findElement(By.id("welcome-button")).click();
    assertThat(driver.findElement(By.id("welcome-div")).getText(), is("Hello john doe!"));
  }
}
```

---

## Új projekt

```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.0.0</version>
    <scope>test</scope>
</dependency>
```

---

## webdrivermanager

```xml
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>5.0.3</version>
    <scope>test</scope>
</dependency>
```

---

## Első teszt osztály

```java
public class IntroTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
```

---

## Első teszteset

```java
@Test
void test() {
    driver.get("http://localhost:80");
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).sendKeys("John Doe");
    driver.findElement(By.id("welcome-button")).click();
    String message = driver.findElement(By.id("welcome-div")).getText();
    assertEquals("Hello John Doe!", message);
}
```

---

class: inverse, center, middle

# ChromeDriver

---

## ChromeDriver

* https://chromedriver.chromium.org/
* W3C WebDriver szabványt megvalósítja

---

## Security Considerations

https://chromedriver.chromium.org/security-considerations

---

## További opciók

Proxy

```java
Proxy proxy = new Proxy();
proxy.setHttpProxy("myhttpproxy:3337");
// options.setCapability("proxy", proxy);

ChromeOptions options = new ChromeOptions();
options.setProxy(proxy);
```

Extensions

```java
options.addExtensions(new File("/path/to/extension.crx"));
```

Capabilities

* Standard: https://www.w3.org/TR/webdriver/#capabilities
* Chrome specifikus: https://chromedriver.chromium.org/capabilities#h.p_ID_102
* `options.setCapability("key", "value")`, de sok ki is van vezetve

Parancssori kapcsolók

* `addArguments()` metódussal
* Alapból mindig létrehoz egyideiglenes profile-t

```java
options.addArguments("user-data-dir=/path/to/your/custom/profile");
```

Ellenőrizhető a `chrome://version` oldalon

Nem biztonságos tanúsítványok elfogadása

```java
options.setAcceptInsecureCerts(true)
```

---

## Experimental options

![Automatizált szoftverek](images/automatikus-szoftverek.png)

```java
ChromeOptions options = new ChromeOptions();
options.setExperimentalOption("excludeSwitches", List.of("enable-automation"));
// options.setExperimentalOption("useAutomationExtension", false); [WARNING]: Deprecated chrome option is ignored: useAutomationExtension
driver = new ChromeDriver(options);
```

Felugró ablakok blokkolása

```java
options.setExperimentalOption("excludeSwitches",
     Arrays.asList("disable-popup-blocking"));
```

---

## Fájlletöltés

```java
ChromeOptions options = new ChromeOptions();
Map<String, Object> prefs = new HashMap<String, Object>();
prefs.put("download.default_directory", "/directory/path");
options.setExperimentalOption("prefs", prefs);
```

* Bizonyos könyvtárak nem megengedettek (tmp?)
* Nem várja meg a letöltés végét, ha előbb van `quit()`, akkor a fájl félbemarad
* Abszolút elérési utat kell használni
* Figyelni kell a path separator karakterekre (Windows vs. Linux)

---

## Naplózás

```java
System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "D:\\chromedriver.log");
System.setProperty(ChromeDriverService.CHROME_DRIVER_VERBOSE_LOG_PROPERTY, "true");
```

```java
System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
```

---

class: inverse, center, middle

# Selenium WebDriver naplózása

---

## Selenium WebDriver naplózása

* `java.util.logging` (JUL) lib-en keresztül
* `org.slf4j:jul-to-slf4j` classpath-ra

```java
// Optionally remove existing handlers attached to j.u.l root logger
SLF4JBridgeHandler.removeHandlersForRootLogger();  // (since SLF4J 1.6.5)

// add SLF4JBridgeHandler to j.u.l's root logger, should be done once during
// the initialization phase of your application
SLF4JBridgeHandler.install();
```

---

class: inverse, center, middle

# Kódismétlés megszüntetése

---

## Kódismétlés megszüntetése

* Leszármazás
* JUnit 5 extension
* https://github.com/bonigarcia/selenium-jupiter


---

class: inverse, center, middle

# Komponensek kijelölése

---

## Komponensek kijelölése

* Id alapján
* Name alapján
* Link szövege alapján
  * Link szövegének egy részlete alapján
* CSS class name
* CSS selector
    * Developer tools/Inspector/Copy CSS selector
* Tag neve alapján
* XPath
    * Developer tools/Inspector/Copy XPath

---

## Egyszerű példa

```java
WebElement simpleDiv = driver.findElement(By.id("simple-div"));
WebElement button = simpleDiv.findElement(By.name("fullname-input"));
```

* `WebDriver` reprezentálja a böngészőt
* `WebElement` reprezentál egy DOM csomópontot
  * `getAttribute()` metódus - visszafele kompatibilitás miatt maradt benne, keveri az attribute és property fogalmát, valamint elég nagy JS megy át a hálózaton
  * `getDomAttribute()` és `getDomProperty` megfelelően működik
* `findElement()` mindkettőn meghívható
* `WebElement`-en hívva annak _kontextusában_ keres
* Kontextusban keresés gyorsabb

---

## Több elem keresése

```java
List<WebElement> inputElements = driver.findElements(By.tagName("input"));
```

---

## XPath

* W3C szabvány
* Egy XML dokumentum elemei és attribútumai közötti navigációt biztosítja
* XPath szintaktika segítségével definiálhatjuk az XML dokumentum részeit
* Kifejezések segítségével mozoghatunk az XML dokumentumban
* https://codebeautify.org/Xpath-Tester
* Kipróbálás: DevTools / Elements / `Ctrl + F`

---

## XPath példák

* `/html`
* `/html/body`
* `//input`
* `/html/@lang`

---

## XPath predicates 1.

* `/html/table/tbody/tr[1]` - első `tr`
* `/html/table/tbody/tr[last()]`  - utolsó `tr`
* `/html/table/tbody/tr[last() - 1]`  - utolsó előtti `tr`
* `/html/table/tbody/tr[position() < 3]` - első két `tr`

---

## XPath predicates 2.

* `//input[@id]` - van `id` attribútuma
* `//input[@id="name-input"]` - `id` attribútumának értéke `name-input`

---

## XPath ismeretlen csomópontok

* `/html/body/*` - összes gyerek
* `//*` - összes elem
* `//*[@id="name-input"]` - összes tag, megadott attribútummal

---

## CSS selector (basic selectors)

* CSS-ben az elemek kijelölésére, melyekre valamilyen formátumot szeretnénk alkalmazni
* W3C CSS specifikáció része
* Kipróbálás: DevTools / Elements / `Ctrl + F`


```plaintext
Lekérdezés id alapján (id selector)
CSS: #example
XPath: //div[@id='example']
```

```plaintext
Lekérdezés tag alapján (type selector)
CSS: input
XPath: //input
```

```plaintext
Lekérdezés class alapján (class selector)
CSS: .example
XPath: //div[@class='example']
```

```plaintext
Lekérdezés attribútum alapján (attribute selector)
CSS: [name='username']
XPath: //div[@name='username']
```

```plaintext
Mindent lekérdez (universal selector)
CSS: *
XPath: //
```

## CSS attribute selectors


* `[attr]` - létező attribútum
* `[attr=value]` - megegyező értékű attribútum
* `[attr~=value]` - szavakból álló attribútum egyik szava
* `[attr|=value]` - értéke vagy a `value`, vagy `value-` és utána bármilyen érték
* `[attr^=value]` - `value` értékkel kezdődik
* `[attr$=value]` - `value` értékkel végződik
* `[attr*=value]` - legalább egyszer tartalmazza
* `[attr operator value i]` - összehasonlítás kis- és nagybetű független (csak ASCII-ra)
* `[attr operator value s] ` - összehasonlítás kis- és nagybetű függő (csak ASCII-ra)

* `id` attribútum alapján: `css=tag#id`, pl. `css=input#name-input`
* CSS class alapján: `css=tag.class`, pl. `css=input.btn`, vagy tag nélkül `.btn`
* Attribútum érték alapján `css=tag[attribute=value]`, pl. `css=input[type=submit]`

---

## Grouping selectors

```plaintext
CSS: div, span
Eredménye: összes div és span csomópont
```

## Combinators

```plaintext
Descendant combinator
CSS: div span
XPath: //div//span
Eredménye: összes div csomópontban lévő span csomópont
```

```plaintext
Child combinator
CSS: div > span
XPath: //div/span
Eredménye: összes közvetlenül a div csomópontban lévő span csomópont
```

```plaintext
General sibling combinator
CSS: div ~ span
Eredménye: span csomópont, ami testvérként követi a div csomópontot
```

```plaintext
Adjacent sibling combinator
CSS: div + span
XPath: //div/following-sibling:span[1]
Eredménye: span csomópont, ami testvérként közvetlenül követi a div csomópontot
```

```plaintext
Column combinator
CSS: col || td
Eredménye: a col csomóponthoz tartozó összes td csomópont
```

## Pseudo classes

```plaintext
Pseudo classes - nem részei a DOM fának
CSS: a:visited
Eredménye: összes meglátogatott a csomópont
```

https://developer.mozilla.org/en-US/docs/Web/CSS/Pseudo-classes

```plaintext
CSS: #recordlist li::nth-of-type(4)
Eredmény: negyedik li gyermeke a #recordlist csomópontnak
CSS: #recordlist li::nth-child(4)
Eredmény: negyedik gyermeke a #recordlist csomópontnak, ha az li csomópont
CSS: #recordlist *::nth-child(4)
Eredmény: negyedik gyermeke független a típusától
```

```plaintext
Pseudo elements - HTML-ben nem szereplő elemek
CSS: p::first-line
Eredménye: p csomópont első sora
```

https://developer.mozilla.org/en-US/docs/Web/CSS/Pseudo-elements

---

## Locator best practices

* Ahol lehet kérdezzünk le id alapján, mert gyors és egyértelmű
* Ha id nincs, akkor preferáljuk a CSS selector használatát
* Az XPath nagyon kifejező, de bonyolult a szintaxis, és általában a böngészőkben 
    nincs teljesítményhangolva, így lassú lehet
* Link szövege alapján csak linket lehet találni
* Tag neve alapján veszélyes lehet, hiszen tipikusan több 

---

class: inverse, center, middle

# Relatív kijelölés

---

## Relatív kijelölés

```java
var cell5 = driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)"));

var cell2 = driver.findElement(with(By.tagName("td")).below(cell5));
```

* `above()`, `below()`, `toLeftOf()`, `toRightOf()`, `near()` (50px-elen belül)
* (Selenium 4 előtt friendly locators)
* Másik elem, vagy locator is átadható paraméterként
* `getBoundingClientRect()` JavaScript függvényt használja

---

class: inverse, center, middle

# Navigáció

---

## Navigáció

```java
driver.get("https://selenium.dev");
driver.navigate().to("https://selenium.dev");

driver.getCurrentUrl();
driver.navigate().back();
driver.navigate().forward();
```

---

## Refresh

```java
driver.navigate().refresh();

WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
wait.until((wd) -> Integer.parseInt(wd.findElement(By.id("counter-div")).getText()) == 3);

var counter = Integer.parseInt(driver.findElement(By.id("counter-div")).getText());
assertEquals(3, counter);
```

* Sose használjunk `Thread.sleep()` hívást!
* Lassítás Debuggolással

---

## Új tabok és ablakok kezelése

```java
driver.switchTo().newWindow(WindowType.TAB);
driver.switchTo().newWindow(WindowType.WINDOW);
```

```java
var handles = driver.getWindowHandles();
for (var handle: handles) {
    driver.switchTo().window(handle);
    log.debug(driver.getTitle());
}
```

* Szintén szükség lehet várakozásra, amíg betölt az oldal

```java
driver.close();
```

---

## Frame-ek kezelése

```java
driver.switchTo().frame("welcome-frame");

driver.switchTo().defaultContent();
```

---

## Ablak kezelése

```java
int width = driver.manage().window().getSize().getWidth();
int height = driver.manage().window().getSize().getHeight();

driver.manage().window().setSize(new Dimension(1024, 768));
```


```java
int x = driver.manage().window().getPosition().getX();
int y = driver.manage().window().getPosition().getY();

driver.manage().window().setPosition(new Point(0, 0));
```

```java
driver.manage().window().maximize();
```

```java
driver.manage().window().fullscreen();
```

---

## Screenshot

```java
File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
Files.move(file.toPath(), Path.of("./screenshot.png"), StandardCopyOption.REPLACE_EXISTING);
```

* Temp könyvtárban hozza létre
* `move()` metódus kivételt dob, ha a fájl már létezik
  * `StandardCopyOption.REPLACE_EXISTING` esetén felülírja

```java
File file = (driver.findElement(By.id("welcome-frame"))).getScreenshotAs(OutputType.FILE);
```

---

## JavaScript futtatás

```java
JavascriptExecutor js = (JavascriptExecutor)driver;
js.executeScript("document.querySelector('tr:nth-child(2) > td:nth-child(2)').style.border = 'thin solid #ff0000';");
```

* Java 15 text block
* Inkább olvassuk be fájlból (IDE-ben szerkeszthető)

```java
js.executeScript(Files.readString(Path.of("./src/test/resources/js/jstest.js")));
```

* Mindig átküldi a hálózaton, ezért lehet _pin_-elni

```java
var key = js.pin(Files.readString(Path.of("./src/test/resources/js/jstest.js")));
js.executeScript(key);
```

---

## Nyomtatás

* Csak Chromium headless módban: `options.addArguments("--headless");`

```java
PrintsPage printer = (PrintsPage) driver;
PrintOptions printOptions = new PrintOptions();
printOptions.setPageRanges("1");
Pdf pdf = printer.print(printOptions);
String content = pdf.getContent();
byte[] bytes = Base64.getDecoder().decode(content);
Files.write(Path.of("./grid.pdf"), bytes);
```

---

class: inverse, center, middle

# Komponensek

---

## Validation methods

* `WebElement.isDisplayed()`
* `WebElement.isSelected()`
* `WebElement.isEnabled()`

---

## Listák, táblázatok

```java
var items = driver.findElements(By.tagName("li"));
assertEquals(List.of("One", "Two", "Three", "Four"), items.stream().map(WebElement::getText).collect(Collectors.toList()));
```

## Textfield, textarea, password

```java
var password = driver.findElement(By.name("password"));
password.sendKeys("password");

assertEquals("", password.getText());
```

---

## Checkbox, radiobutton

```java
var checkbox = driver.findElement(By.name("checkbox"));
checkbox.click();
assertTrue(checkbox.isSelected());
```

---

## Select

```java
var select = new Select(driver.findElement(By.id("dropdown")));
select.getOptions().forEach(e -> e.getText());
select.selectByIndex(2);

assertEquals("Option 3", select.getFirstSelectedOption().getText());
```

---

class: inverse, center, middle

# Wait

---

## Explicit wait

* https://www.selenium.dev/documentation/webdriver/waits/

```java
var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
```

```java
wait.until(d -> {log.debug("Check condition"); return d.findElement(By.className("alert")).getText().startsWith("Nice");});
```

* Poll

```java
wait.until(ExpectedConditions.presenceOfElementLocated(By.className("alert")));
```

Ha nincs meg:

```
org.openqa.selenium.TimeoutException: Expected condition failed: waiting for presence of element located by: By.className: error (tried for 10 second(s) with 500 milliseconds interval)
```

---

## ExpectedConditions osztály

* `presenceOfElement` - elem létezik-e
* `numberOfElementsToBe...` - elemek számára vonatkozó feltétel
* `visibilityOf`, `invisibilityOf` - elem láthatósága (van `visibilityOfAllElements` metódus, mely több elemet vár)
* `textToBe` - elem szövegére feltételek
* `elementSelectionStateToBe`, `elementToBeSelected` - adott elem ki van-e választva
* `elementToBeClickable`- adott elem klikkelhető-e
* `attribute...` - attribútum meglétére, értékére vonatkozó feltételek
* `jsReturnsValue`, `javaScriptThrowsNoExceptions​` - JavaScript
* `title...` - cím szövegére feltételek
* `url...` - címre feltételek
* `alertIsPresent` - alert megjelent-e
* `frame...` - frame látszik-e, és rá lehet-e váltani
* `numberOfWindowsToBe` - ablakok számára feltétel
* `and`, `or` - összefűzhetőség
* `refreshed​(ExpectedCondition<T> condition)` - workaround, arra az esetre, ha elem megtalálása után újrarajzolás miatt `StaleElementReferenceException` kivétel jön

https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html

---

## Saját condition implementálása

```java
private static Function<WebDriver, Boolean> hasAlertWithText(String text) {
  return (driver) -> driver.findElement(By.className("alert")).getText().startsWith(text);
}
```

```java
wait.until(hasAlertWithText("Nice"));
```

---

## Implicit wait

```java
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Beállít
var timeout = driver.manage().timeouts().getImplicitWaitTimeout(); // Lekérdez
```

* Session szinten határozza meg, hogy az elemekre maximum mennyit várjon a WebDriver
* Az explicit és implicit wait nem keverhető!

---

## Fluent wait

```java
Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
  .withTimeout(Duration.ofSeconds(30))
  .pollingEvery(Duration.ofSeconds(5))
  .ignoring(NoSuchElementException.class);
```

---

class: inverse, center, middle

# AJAX

---

## AJAX

* Tesztelése wait használatával valósítható meg
* Lehet arra is várni, hogy ne legyen aktív AJAX kérés

---

## Vanilla JS

```javascript
window.ajax_active = 0;

(function(open) {
  return XMLHttpRequest.prototype.open = function(method, url, async, user, pass) {
    this.addEventListener('readystatechange', (function() {
     if (this.readyState == 1) {
        window.ajax_active += 1;
     } else if (this.readyState == 4){
        window.ajax_active -= 1;
     } 
    }), false);
    return open.call(this, method, url, async, user, pass);
  };
})(XMLHttpRequest.prototype.open);
```

---

## JQuery

```javascript
jQuery.active
```

---

## Angular

```javascript
angular.element(document.body).injector().get("$http").pendingRequests.length;
```

```javascript
angular.module('myApp',[])
.value('httpStatus',{arc:0})
.factory('activeHttpIntercepotrs',function(httpStatus,$q,$rootScope){
    //link up $rootScope and httpStatus
    $rootScope.httpStatus = httpStatus;
    return {
        'request': function(config){
            httpStatus.arc++; return config || $q.when(config);
        },
        'response': function(config){
            httpStatus.arc--; return config || $q.when(config);
        }
    }

})
.config(function($httpProvider){
    $httpProvider.interceptors.push('activeHttpIntercepotrs');
});
```

```html
<span class="dontDisplayMe">{{httpStatus.arc}}</span>
```

---

class: inverse, center, middle

# Events

---

```java
driver = new EventFiringDecorator(new EventListener()).decorate(driver);
```

```java
@Slf4j
public class EventListener implements WebDriverListener {
    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        log.debug("Find element: {}, {}", locator, result);
    }
}
```

---

class: inverse, center, middle

# Naplózás

---

## Naplózás

```java
LoggingPreferences preferences = new LoggingPreferences();
preferences.enable(LogType.BROWSER, Level.FINEST);
preferences.enable(LogType.DRIVER, Level.FINEST);
options.setCapability(CapabilityType.LOGGING_PREFS, preferences);
```

```java
driver.manage().logs().get(LogType.DRIVER).getAll().forEach(entry -> log.debug(entry.toString()));
```

* WebDriver BiDirectional Protocol
* WebSocket alapú kétirányú kommunikáció

---

class: inverse, center, middle

## Újrafelhasználás, page object

---

## Page object támogatás

```java
public class WelcomePage {

    private WebDriver webDriver;

    @FindBy(name = "name")
    private WebElement nameInput;

    public WelcomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void withName(String name) {
        nameInput.sendKeys(name);
    }
}
```

```java
var welcomePage = new WelcomePage(driver);
welcomePage.withName("John Doe");
```

* `@FindAll` - mindet visszaadja
* `@CacheLookup` - az elem nem változik

---

## Assert

```java
public void assertMessage(String message) {
    assertEquals(message, messageDiv.getText());
}
```

```java
welcomePage.assertMessage("Hello John Doe!");
```

---

## Default paraméter

---

## Fluent API

---

class: inverse, center, middle

# Legjobb gyakorlatok

---

## Legjobb gyakorlatok

* Körültekintően nevezzük el a teszteket
* Tesztesetek legyenek egyszerűek
* DRY - don't repeat yourself
* Idempotens és izolált
  * Tesztesetek sorrendje mindegy
  * Könnyebben párhuzamosítható
* Egyszerre egy dolgot teszteljen
* Előfeltételek beállítása: API-n keresztül
  * pl. adatbázis (Spring JdbcTemplate), REST (RestAssured)

---

## Állapot

* Tesztesetek egymásra hatással vannak
    * Állapot: pl. adatbázis
* Ugyanazon tesztkörnyezeten több tesztelő vagy harness dolgozik
* Megoldás:
    * Teszteset "rendet tesz" maga előtt, un. set-up
    * "Rendet tesz" maga után, un. tear down
    * Test fixture
        * Legszélsőségesebb megoldás: adatbázistörlés

---

## Teszt típusok

* Funkcionális tesztek
* Acceptance testing
* Performance testing - nem erre való
  * Load testing
  * Stress testing
* Regression testing
* Test driven development (TDD)
* Behavior-driven development (BDD) - ráültethető

---

## Külső rendszerek mockolása

* WireMock

---

## Continuous Integration

---

## Rossz gyakorlatok

* Captcha: tesztkörnyezetben legyen kikapcsolható
* Fájlletöltés: más könyvtárral

```java
byte[] dowloadedFile = RestAssured.given().when()
    .get("https://chercher.tech/files/minion.zip")
    .then().extract().asByteArray();
```

* HTTP response code

```java
RestAssured.given()
    when().
            get("/lotto/{id}", 5).
    then().
            statusCode(200);
```

* Autentikáció: API-n keresztül
* Two Factor Authentication: kapcsoljuk ki teszt környezetben
* Link spidering, web crawler: használjunk más eszközt (curl, wget, stb.)


---

class: inverse, center, middle

# Adatvezérelt tesztelés, CSV

---

## Libek

* https://commons.apache.org/proper/commons-csv/
* http://opencsv.sourceforge.net/

---

class: inverse, center, middle

# Data Driven - Excel

https://poi.apache.org/

---

---

class: inverse, center, middle

# Adatvezérelt tesztelés, DB

---

class: inverse, center, middle

# Adatbázis fixture

---

class: inverse, center, middle

# REST hívás - RestAssured

---

class: inverse, center, middle

# SOAP hívás - CXF

---

class: inverse, center, middle

# Selenium Grid

---

## Startup

```shell
mvn -N io.takari:maven:0.7.7:wrapper
git update-index --chmod=+x mvnw
docker build -t sut-samples .
docker build -t intro-tests .
docker compose up
```

http://localhost:4444/ui/index.html#/

---

## VNC

* VNC kliens feltelepítése: https://www.realvnc.com/en/connect/download/viewer/
* VNC csatlakozás a `localhost:5900`-as címre
* Alapértelmezett jelszó: `secret`

---

## Chrome futtatás

https://github.com/SeleniumHQ/docker-selenium

```java
ChromeOptions options = new ChromeOptions();
options.addArguments("--disable-dev-shm-usage");
driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
url = new URL("http://sut-samples");
```

* Docker 64M-ban maximalizálja a `/dev/shm` shared memory méretét, mely a Chrome-nak kicsi

```
(unknown error: devtoolsactiveport file doesn't exist) (the process started from chrome location /usr/bin/google-chrome is no longer running, so chromedriver is assuming that chrome has crashed.)
```

A `--disable-dev-shm-usage` kapcsoló hatására a `/tmp`-et fogja használni

---

## Tesztek futtatása

```shell
docker compose --profile runtests up --abort-on-container-exit
```
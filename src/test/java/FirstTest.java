

/**
 * Created by natalia.chaplygina on 22.11.2018.
 */

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;

import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FirstTest {


    static String str = createParam();

    public static String createParam() {
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 0);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String str = format1.format(c.getTime());
        return str;
    }

    private static WebDriver driver;


    @BeforeClass

    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/natalia.chaplygina/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://prototype.datasynthes.com/synthes-frontend/model");
        WebElement loginField = driver.findElement(By.name("userName"));
        loginField.sendKeys("admin");
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("UsaeZ9ph");
        WebElement loginButton = driver.findElement(By.xpath("//button[text()='LOG IN']"));
        loginButton.click();
    }

    @Test
    @Ignore
    public void userLoginTest() {

        WebElement loginField = driver.findElement(By.name("userName"));
        loginField.sendKeys("admin");
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("UsaeZ9ph");
        WebElement loginButton = driver.findElement(By.xpath("//button[text()='LOG IN']"));
        loginButton.click();

    }

    @Test
    @Ignore
    public void createEntityTest() {

      /*  Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 0);
        //  SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String str = format1.format(c.getTime());
*/

        driver.findElement(By.xpath("//div[@class='hider']")).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("//div[@class='hider']")));
        WebElement addEntityButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='hider']/div[@class='to-hide']/div[@class='sidebar-bottom']/button[3]")));
        addEntityButton.click();

        //create entity
 /*       driver.findElement(By.xpath("//div[@class='ui tiny modal transition visible active']/div[@class='actions']/button[1]")).click();
          // WebElement displayName = driver.findElement(By.name("displayName"));
          //  displayName.sendKeys("entiy_"+"test_"+str);
        driver.findElement(By.name("displayName")).sendKeys("entity_" + "test_" + str);
        driver.findElement(By.name("name")).sendKeys("entity_" + "test_" + str);
        driver.findElement(By.xpath("//div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui pointing secondary menu']/a[@class='right item']/i[@class='large icon icon-floppy-disk']")).click();
*/
        //create  lookup

        WebElement newLookup = driver.findElement(By.xpath("//div[@class='ui tiny modal transition visible active']/div[@class='actions']/button[2]"));
        // driver.findElement(By.xpath("//div[@class='ui tiny modal transition visible active']/div[@class='actions']/button[2]")).click();
        newLookup.click();

        String nameLookup = "lookup" + "test_" + str;
        driver.findElement(By.name("displayName")).sendKeys(nameLookup);
        driver.findElement(By.name("name")).sendKeys(nameLookup);
        driver.findElement(By.xpath("//div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui pointing secondary menu']/a[@class='right item']/i[@class='large icon icon-floppy-disk']")).click();

        WebElement check = driver.findElement(By.xpath("//*[text()='" + nameLookup + "']"));


        assertNotNull(check);
        assertTrue(check.isDisplayed());

        System.out.println(check.toString());
        driver.findElement(By.xpath("//*[text()='Business Glossary']")).click();


//это работает
        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='hider invert']/div[@class='to-hide']/div[@class='attribute-tree-buttons']"));
        builder.moveToElement(element).build().perform();

        // WebElement checkBox = driver.findElement(By.cssSelector(".ui active visible button upward dropdown icon left floated add-button"));
        Select oSelect = new Select(driver.findElement(By.xpath("//div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='hider invert']/div[@class='to-hide']/div[@class='attribute-tree-buttons']/div[@class='ui active visible button upward dropdown icon left floated add-button']/i[@class='large icon icon-file-empty']")));

        oSelect.selectByVisibleText("Add attribute");


        ///работа с текущим lookup


        ///---это кнопка вверх/вниз  driver.findElement(By.xpath("//div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='hider invert']/div[@class='to-hide']/div[@class='attribute-tree-buttons']/button[1]")).click();

/*
        Actions builder = new Actions(driver);
        builder.moveToElement("тут элемент на который наводим").click("тут элемент по которому кликаем")
        Action mouseoverAndClick = builder.build();
        mouseoverAndClick.perform();


*/


        // driver.findElement(By.xpath("//div[@class='ui card panel']//div[@class='content']//div[@class='hider invert']//div[@class='to-hide']//div[@class='attribute-tree-buttons']")).click();
        webDriverWait = new WebDriverWait(driver, 5);


        //webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("//div[@class='ui card panel']//div[@class='content']//div[@class='hider invert']//div[@class='to-hide']//div[@class='attribute-tree-buttons']")));
        //  webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated (By.xpath("//div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='hider invert']/div[@class='to-hide']/div[@class='attribute-tree-buttons']")));


        // WebElement addAttribute = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='large icon icon-file-empty']")));
        //addAttribute.click();









        //create DC
        driver.findElement(By.xpath("//div[@class='ui tiny modal transition visible active']/div[@class='actions']/button[4]")).click();
        driver.findElement(By.name("displayName")).sendKeys("DC" + "test_" + str);
        driver.findElement(By.name("name")).sendKeys("DC" + "test_" + str);
        driver.findElement(By.xpath("//div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui pointing secondary menu']/a[@class='right item']/i[@class='large icon icon-floppy-disk']")).click();


        //      Thread.sleep(5000);


    }



        @Test
     @Ignore
        //эскпортируем модель
      public  void ExportmodelTest(){




        driver.findElement(By.xpath("//div[@class='hider']")).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("//div[@class='hider']")));
        WebElement exim = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='hider']/div[@class='to-hide']/div[@class='sidebar-bottom']/button[1]")));
        exim.click();


        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='actions']/button[@class='ui primary button'][2]")).click();
        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='actions']/button[@class='ui primary button']")).click();

        //идем скачать модель
    }

    @Test
    @Ignore
    public void loadmodelTest() {
        driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/div[@id='navigation']/ul[@class='navigation-menu bottom-menu']/li[2]/a/i[@class='commenting outline large icon']")).click();
        //здесь бы надо получить имя
        driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/div[@class='notifications-bar js-notifications-bar-element']/div[@class='notifications js-notifications-bar-element']/div[@class='notification'][last()]")).getText();

        //проверка что все загрузка прошла успешна
        driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/div[@class='notifications-bar js-notifications-bar-element']/div[@class='notifications js-notifications-bar-element']/div[@class='notification'][last()]/*[text()=' Metamodel export - successfully finished']"));
        driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/div[@class='notifications-bar js-notifications-bar-element']/div[@class='notifications js-notifications-bar-element']/div[@class='notification'][last()]/*[text()='Download result']")).click();
        //импортируем модель
        // driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/div[@id='navigation']/ul[@class='navigation-menu main-menu']/li[2]/ul/li[1]/a[1]")).click();

        driver.findElement(By.xpath("//div[@class='hider']")).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("//div[@class='hider']")));
        WebElement exim = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='hider']/div[@class='to-hide']/div[@class='sidebar-bottom']/button[1]")));
        exim.click();
        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='actions']/button[@class='ui primary button'][1]")).click();

        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='content']/div[@class='ui checkbox']/label[1]")).click();
        WebElement uploadElement = driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='content']/div[@class='ui action input']/input"));
/// надо передвавть переменную
        uploadElement.sendKeys("C:\\Users\\natalia.chaplygina\\Downloads\\default2018-12-06_14-22-16.zip");
        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='content']/div[@class='ui action input']/button[@class='ui icon button']/i[@class='upload icon']")).click();
        // driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='actions']/button[@class='ui primary button']C:\Users\natalia.chaplygina\Downloads

        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='actions']/button[@class='ui primary button']")).click();
        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='actions']/button[@class='ui primary button']")).click();
        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='actions']/button[@class='ui primary button']")).click();
    }

    //проверка существующего Data Catalog




  /*  @AfterClass
    public static void tearDown() {
        WebElement menuUser = driver.findElement(By.cssSelector(".login-button__menu-icon"));
        menuUser.click();
        WebElement logoutButton = driver.findElement(By.id("login__logout"));
        logoutButton.click();
        driver.quit();
    }
    */
}
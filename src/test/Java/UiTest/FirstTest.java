package UiTest;

/**
 * Created by natalia.chaplygina on 22.11.2018.
 */

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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
        File file = new File(".\\mydownloads");
        if (file.exists()) {
            try {
                FileUtils.deleteDirectory(file);
                file.mkdir();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file.mkdir();
        }
        String driverepath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", driverepath+"\\tmp\\chromedriver.exe");

        String downloadFilepath = System.getProperty("user.dir");
        System.out.println(downloadFilepath);
        downloadFilepath = downloadFilepath + "\\mydownloads";
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", downloadFilepath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, options);

        driver = new ChromeDriver(cap);
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

    @AfterClass
    public static void tearDown()
    {
        driver.quit();
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


    }


    @Test
    @DisplayName("IU Export model")
    //эскпортируем модель
    public void ExportmodelTest() {


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
    @DisplayName("IU load and Import model")

    public void loadModelTest() {

        String downloadFilepath = System.getProperty("user.dir");
        System.out.println(downloadFilepath);
        downloadFilepath = downloadFilepath + "\\mydownloads";


        driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/div[@id='navigation']/ul[@class='navigation-menu bottom-menu']/li[2]/a/i[@class='commenting outline large icon']")).click();
        //здесь бы надо получить имя
        WebElement namemodel = driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/div[@class='notifications-bar js-notifications-bar-element']/div[@class='notifications js-notifications-bar-element']/div[@class='notification'][last()]"));
        String partname = namemodel.getText();
        // driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/div[@class='notifications-bar js-notifications-bar-element']/div[@class='notifications js-notifications-bar-element']/div[@class='notification'][last()]")).getText();

        System.out.println(partname);
        //записали дату в переменную
        //  SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        // DateFormat format = new SimpleDateFormat(("yyyy-MM-dd'T'HH:mm:ss");
        // Date Dpartnamefull = format.parse(partnamefull);
        // Date date = format1.parse(partnamefull);
        //   System.out.println(Dpartnamefull); // Sat Jan 02 00:00:00 GMT 2010

        //проверка что все загрузка прошла успешна - хреново работает
        //      driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/div[@class='notifications-bar js-notifications-bar-element']/div[@class='notifications js-notifications-bar-element']/div[@class='notification'][last()]/*[text()=' Metamodel export - successfully finished']"));
        WebElement downloadModel = driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/div[@class='notifications-bar js-notifications-bar-element']/div[@class='notifications js-notifications-bar-element']/div[@class='notification'][last()]/span[@class='link']"));
        downloadModel.click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String fileName = findFile(downloadFilepath);
        System.out.println(fileName);


        //импортируем модель
        // driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/div[@id='navigation']/ul[@class='navigation-menu main-menu']/li[2]/ul/li[1]/a[1]")).click();

        // driver.findElement(By.xpath("//div[@class='hider']")).click();

        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/div[@id='navigation']/ul[@class='navigation-menu main-menu']/li[2]/ul/li[1]/a/i[@class='puzzle large icon']")).click();

        WebElement element = driver.findElement(By.xpath("//div[@class='sidebar']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        // actions.click();
        actions.perform();

        driver.findElement(By.xpath("//div[@class='hider']")).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("//div[@class='hider']")));
        WebElement exim = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='hider']/div[@class='to-hide']/div[@class='sidebar-bottom']/button[1]")));
        exim.click();
        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='actions']/button[@class='ui primary button'][1]")).click();

        WebElement uploadElement = driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='content']/div[@class='ui action input']/input"));
        // надо передвавть переменную
        uploadElement.sendKeys(fileName);
        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='content']/div[@class='ui action input']/button[@class='ui icon button']/i[@class='upload icon']")).click();
        // driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='actions']/button[@class='ui primary button']C:\Users\natalia.chaplygina\Downloads

        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='actions']/button[@class='ui primary button']")).click();
        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='actions']/button[@class='ui primary button']")).click();
        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='actions']/button[@class='ui primary button']")).click();
    }

    private String findFile(String downloadFilepath) {
        File dir = new File(downloadFilepath);
        File[] matches = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith("default") && name.endsWith(".zip");
            }
        });
        String fileName = "";
        if (matches != null) {
            fileName = matches[0].toString();
        }
        return fileName;    }





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
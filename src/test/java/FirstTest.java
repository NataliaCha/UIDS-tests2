

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


    String str = createParam();

    public String createParam() {
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
    public void userLogin() {

        WebElement loginField = driver.findElement(By.name("userName"));
        loginField.sendKeys("admin");
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("UsaeZ9ph");
        WebElement loginButton = driver.findElement(By.xpath("//button[text()='LOG IN']"));
        loginButton.click();

    }

    @Test
    @Ignore
    public void createEntity() {

        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 0);
        //  SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String str = format1.format(c.getTime());


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







/*
        //create dq policy

        driver.findElement(By.xpath("//div[@class='ui tiny modal transition visible active']/div[@class='actions']/button[3]")).click();
        driver.findElement(By.name("displayName")).sendKeys("policy" + "test_" + str);
        driver.findElement(By.name("name")).sendKeys("policy" + "test_" + str);
        driver.findElement(By.xpath("//div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui pointing secondary menu']/a[@class='right item']/i[@class='large icon icon-floppy-disk']")).click();


        //create DC
        driver.findElement(By.xpath("//div[@class='ui tiny modal transition visible active']/div[@class='actions']/button[4]")).click();
        driver.findElement(By.name("displayName")).sendKeys("DC" + "test_" + str);
        driver.findElement(By.name("name")).sendKeys("DC" + "test_" + str);
        driver.findElement(By.xpath("//div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui pointing secondary menu']/a[@class='right item']/i[@class='large icon icon-floppy-disk']")).click();
*/

        //      Thread.sleep(5000);


    }


    //проверка существующего lookup
    @Test
    @Ignore
    public void realLookUp() {


        driver.findElement(By.xpath("//li[@class='bp3-tree-node bp3-tree-node-expanded'][5]//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-open']")).click();
        driver.findElement(By.xpath("//li[@class='bp3-tree-node']//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-closed']")).click();
        driver.findElement(By.xpath("//*[text()='Biz Day Conventions']")).click();
        driver.findElement(By.xpath("//*[text()='Business Glossary']")).click();


        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='hider invert']/div[@class='to-hide']/div[@class='attribute-tree-buttons']"));
        builder.moveToElement(element).build().perform();

        driver.findElement(By.xpath("//i[@class='large icon icon-file-empty']")).click();
        driver.findElement(By.xpath("//div[@class='visible menu transition']//div[@class='item'][1]")).click();


        String nameLookup = "lookup" + "test_" + str;
        //заполняеем Properties

        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][1]/div[@class='field']/div[@class='ui input']/input")).sendKeys("testnotcoded" + str);

        driver.findElement(By.name("definition")).sendKeys("testnotcoded" + str);
        driver.findElement(By.name("example")).sendKeys("testnotcoded" + str);
        driver.findElement(By.name("acronym")).sendKeys("testnotcoded" + str);


        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][6]//div[@class='field']//div[@class='ui fluid selection dropdown']")).click();
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][6]//div[@class='field']//div[@class='ui active visible fluid selection dropdown']//div[@class='visible menu transition']//div[@class='item'][2]")).click();
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][6]//div[@class='field'][2]//div[@class='ui fluid selection dropdown']")).click();
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][6]//div[@class='field'][2]//div[@class='ui active visible fluid selection dropdown']//div[@class='visible menu transition']//div[@class='item'][2]")).click();

        //все рaботает

        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][10]//div[@class='field']//div[@class='ui fluid multiple selection dropdown']")).click();
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][10]//div[@class='field']//div[@class='ui active visible fluid multiple selection dropdown']//div[@class='visible menu transition']//div[@class='item'][1]")).click();
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][10]//div[@class='field']//div[@class='ui active visible fluid multiple selection dropdown']//i[@class='dropdown icon']")).click();
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][11]//div[@class='field']//div[@class='ui fluid multiple selection dropdown']")).click();
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][11]//div[@class='field']//div[@class='ui active visible fluid multiple selection dropdown']//div[@class='visible menu transition']//div[@class='item'][1]")).click();
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][11]//div[@class='field']//div[@class='ui active visible fluid multiple selection dropdown']")).click();
        driver.findElement(By.name("useCases")).sendKeys("test123");


        //заполняем Synonyms:
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][9]//div[@class='field']//div[@class='entity-synonyms']//i[@class='plus circle large icon']")).click();
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][9]//div[@class='field']//div[@class='entity-synonyms']//i[@class='dropdown icon']")).click();
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][9]//div[@class='field']//div[@class='entity-synonyms']//div[@class='visible menu transition']//*[text()='SSS']")).click();
        driver.findElement(By.name("synonym")).sendKeys("asdasd");

        //заполняем MANAGED BY
        driver.findElement(By.name("dataSteward")).sendKeys("QA");
        driver.findElement(By.name("phone")).sendKeys("892100305ABC"); //на перспективу проверку на цифры
        driver.findElement(By.name("email")).sendKeys("abc@yandex.ru");
        driver.findElement(By.name("location")).sendKeys("London");
        driver.findElement(By.xpath("//div[@id='root']//div[@id='layout']//main//div[@class='data-model-container sidebar-show']//div[@class='content items-tab']//div[@class='active tab ui']//div[@class='container-with-side-buttons']//div[@class='main entities-tab']//div[@class='ui segment active tab']//div[@class='SplitPane  vertical ']//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][3]//div[@class='content']//div[@class='equal width fields'][4]//div[@class='field'][1]//div[@class='ui fluid selection dropdown']//i[@class='dropdown icon']")).click();
        driver.findElement(By.xpath("//div[@id='root']//div[@id='layout']//main//div[@class='data-model-container sidebar-show']//div[@class='content items-tab']//div[@class='active tab ui']//div[@class='container-with-side-buttons']//div[@class='main entities-tab']//div[@class='ui segment active tab']//div[@class='SplitPane  vertical ']//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][3]//div[@class='content']//div[@class='equal width fields'][4]//div[@class='field'][1]//div[@class='ui active visible fluid selection dropdown']//div[@class='visible menu transition']//div[@class='selected item'][1]")).click();


        driver.findElement(By.xpath("//div[@id='root']//div[@id='layout']//main//div[@class='data-model-container sidebar-show']//div[@class='content items-tab']//div[@class='active tab ui']//div[@class='container-with-side-buttons']//div[@class='main entities-tab']//div[@class='ui segment active tab']//div[@class='SplitPane  vertical ']//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][3]//div[@class='content']//div[@class='equal width fields'][4]//div[@class='field'][2]//div[@class='ui fluid selection dropdown']//i[@class='dropdown icon']")).click();

        driver.findElement(By.xpath("//div[@id='root']//div[@id='layout']//main//div[@class='data-model-container sidebar-show']//div[@class='content items-tab']//div[@class='active tab ui']//div[@class='container-with-side-buttons']//div[@class='main entities-tab']//div[@class='ui segment active tab']//div[@class='SplitPane  vertical ']//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][3]//div[@class='content']//div[@class='equal width fields'][4]//div[@class='field'][2]//div[@class='ui active visible fluid selection dropdown']//div[@class='visible menu transition']//div[@class='selected item'][1]")).click();


        driver.findElement(By.name("businessFunc")).sendKeys("QA test");

        //Переходим к закладке Technical Glossary

        driver.findElement(By.xpath("//*[text()='Technical Glossary']")).click();
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][1]/div[@class='content']/div[@class='equal width fields'][1]/div[@class='field']/div[@class='ui input']/input")).sendKeys("testnotcoded" + str);
        WebElement datatypedef = driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][1]/div[@class='content']/div[@class='equal width fields'][3]/div[@class='field']/div[@class='ui fluid selection dropdown']/div[@class='text']"));
//        datatypedef.getText();
//        System.out.println(datatypedef.getText());
        Assert.assertEquals("String", datatypedef.getText());//значение по дефолту подставилось верно

//изменим datatype
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][1]/div[@class='content']/div[@class='equal width fields'][3]/div[@class='field']/div[@class='ui fluid selection dropdown']/i[@class='dropdown icon']")).click();
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][1]/div[@class='content']/div[@class='equal width fields'][3]/div[@class='field']/div[@class='ui active visible fluid selection dropdown']/div[@class='visible menu transition']/div[@class='item'][3]")).click();


//заполнение галочек

        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][1]/div[@class='content']/div[@class='equal width fields'][5]/div[@class='field']/div[@class='ui fitted checkbox']")).click();
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][1]/div[@class='content']/div[@class='equal width fields'][7]/div[@class='field']/div[@class='ui fitted checkbox']")).click();
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][1]/div[@class='content']/div[@class='equal width fields'][8]/div[@class='field']/div[@class='ui fitted checkbox']")).click();
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][1]/div[@class='content']/div[@class='equal width fields'][9]/div[@class='field']/div[@class='ui fitted checkbox']")).click();

        //ДОБАВЛЕНИЕ  coded attribute

        driver.findElement(By.xpath("//*[text()='Business Glossary']")).click();


        Actions builder1 = new Actions(driver);
        WebElement element1 = driver.findElement(By.xpath("//div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='hider invert']/div[@class='to-hide']/div[@class='attribute-tree-buttons']"));
        builder.moveToElement(element1).build().perform();

        driver.findElement(By.xpath("//i[@class='large icon icon-file-empty']")).click();
        // driver.findElement(By.xpath("//div[@class='visible menu transition']//div[@class='active item']")).click();
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='hider invert']/div[@class='to-hide']/div[@class='attribute-tree-buttons']/div[@class='ui active visible button upward dropdown icon left floated add-button']/div[@class='visible menu transition']/div[@class='item'][1]")).click();


        //заполняеем Properties

        //driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields']//div[@class='field']//div[@class='ui input']//div[@name='displayName']"));

        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][1]/div[@class='field']/div[@class='ui input']/input")).sendKeys("codedtest" + str);
        driver.findElement(By.name("definition")).sendKeys("codedtest" + str);
        driver.findElement(By.name("example")).sendKeys("codedtest" + str);
        driver.findElement(By.name("acronym")).sendKeys("codedtest" + str);


        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][6]//div[@class='field']//div[@class='ui fluid selection dropdown']")).click();
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][6]//div[@class='field']//div[@class='ui active visible fluid selection dropdown']//div[@class='visible menu transition']//div[@class='item'][2]")).click();
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][6]//div[@class='field'][2]//div[@class='ui fluid selection dropdown']")).click();
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][6]//div[@class='field'][2]//div[@class='ui active visible fluid selection dropdown']//div[@class='visible menu transition']//div[@class='item'][2]")).click();

        //все рвботает

        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][10]//div[@class='field']//div[@class='ui fluid multiple selection dropdown']")).click();
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][10]//div[@class='field']//div[@class='ui active visible fluid multiple selection dropdown']//div[@class='visible menu transition']//div[@class='item'][1]")).click();
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][10]//div[@class='field']//div[@class='ui active visible fluid multiple selection dropdown']//i[@class='dropdown icon']")).click();

        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][11]//div[@class='field']//div[@class='ui fluid multiple selection dropdown']")).click();
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][11]//div[@class='field']//div[@class='ui active visible fluid multiple selection dropdown']//div[@class='visible menu transition']//div[@class='item'][1]")).click();

        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][11]//div[@class='field']//div[@class='ui active visible fluid multiple selection dropdown']")).click();

        driver.findElement(By.name("useCases")).sendKeys("test12345");


        //заполняем Synonyms:
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][9]//div[@class='field']//div[@class='entity-synonyms']//i[@class='plus circle large icon']")).click();
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][9]//div[@class='field']//div[@class='entity-synonyms']//i[@class='dropdown icon']")).click();
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][9]//div[@class='field']//div[@class='entity-synonyms']//div[@class='visible menu transition']//*[text()='SSS']")).click();
        driver.findElement(By.name("synonym")).sendKeys("tttttt");

        //заполняем MANAGED BY
        driver.findElement(By.name("dataSteward")).sendKeys("QA123");
        driver.findElement(By.name("phone")).sendKeys("892100305ABC"); //на перспективу проверку на цифры
        driver.findElement(By.name("email")).sendKeys("abc@yandex.ru");
        driver.findElement(By.name("location")).sendKeys("London");
        driver.findElement(By.xpath("//div[@id='root']//div[@id='layout']//main//div[@class='data-model-container sidebar-show']//div[@class='content items-tab']//div[@class='active tab ui']//div[@class='container-with-side-buttons']//div[@class='main entities-tab']//div[@class='ui segment active tab']//div[@class='SplitPane  vertical ']//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][3]//div[@class='content']//div[@class='equal width fields'][4]//div[@class='field'][1]//div[@class='ui fluid selection dropdown']//i[@class='dropdown icon']")).click();
        driver.findElement(By.xpath("//div[@id='root']//div[@id='layout']//main//div[@class='data-model-container sidebar-show']//div[@class='content items-tab']//div[@class='active tab ui']//div[@class='container-with-side-buttons']//div[@class='main entities-tab']//div[@class='ui segment active tab']//div[@class='SplitPane  vertical ']//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][3]//div[@class='content']//div[@class='equal width fields'][4]//div[@class='field'][1]//div[@class='ui active visible fluid selection dropdown']//div[@class='visible menu transition']//div[@class='selected item'][1]")).click();
        driver.findElement(By.xpath("//div[@id='root']//div[@id='layout']//main//div[@class='data-model-container sidebar-show']//div[@class='content items-tab']//div[@class='active tab ui']//div[@class='container-with-side-buttons']//div[@class='main entities-tab']//div[@class='ui segment active tab']//div[@class='SplitPane  vertical ']//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][3]//div[@class='content']//div[@class='equal width fields'][4]//div[@class='field'][2]//div[@class='ui fluid selection dropdown']//i[@class='dropdown icon']")).click();
        driver.findElement(By.xpath("//div[@id='root']//div[@id='layout']//main//div[@class='data-model-container sidebar-show']//div[@class='content items-tab']//div[@class='active tab ui']//div[@class='container-with-side-buttons']//div[@class='main entities-tab']//div[@class='ui segment active tab']//div[@class='SplitPane  vertical ']//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][3]//div[@class='content']//div[@class='equal width fields'][4]//div[@class='field'][2]//div[@class='ui active visible fluid selection dropdown']//div[@class='visible menu transition']//div[@class='selected item'][1]")).click();
        driver.findElement(By.name("businessFunc")).sendKeys("QA test");


        driver.findElement(By.xpath("//*[text()='Technical Glossary']")).click();
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][1]/div[@class='content']/div[@class='equal width fields'][1]/div[@class='field']/div[@class='ui input']/input")).sendKeys("codedtest" + str);

        WebElement datatypedef1 = driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][1]/div[@class='content']/div[@class='equal width fields'][3]/div[@class='field']/div[@class='ui fluid selection dropdown']/div[@class='text']"));
//        datatypedef.getText();
//        System.out.println(datatypedef.getText());
        Assert.assertEquals("String", datatypedef1.getText());//значение по дефолту для datatype подставилось верно

//изменим datatype
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][1]/div[@class='content']/div[@class='equal width fields'][3]/div[@class='field']/div[@class='ui fluid selection dropdown']/i[@class='dropdown icon']")).click();
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][1]/div[@class='content']/div[@class='equal width fields'][3]/div[@class='field']/div[@class='ui active visible fluid selection dropdown']/div[@class='visible menu transition']/div[@class='item'][3]")).click();


//заполнение галочек

        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][1]/div[@class='content']/div[@class='equal width fields'][5]/div[@class='field']/div[@class='ui fitted checkbox']")).click();
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][1]/div[@class='content']/div[@class='equal width fields'][7]/div[@class='field']/div[@class='ui fitted checkbox']")).click();
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][1]/div[@class='content']/div[@class='equal width fields'][8]/div[@class='field']/div[@class='ui fitted checkbox']")).click();
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][1]/div[@class='content']/div[@class='equal width fields'][9]/div[@class='field']/div[@class='ui fitted checkbox']")).click();


        //сохраняем

        driver.findElement(By.xpath("//div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui pointing secondary menu']/a[@class='right item']/i[@class='large icon icon-floppy-disk']")).click();

        //подвигаем вверх/вниз
        //?????????????????????????

        //************************НАДО ВЫНЕСТИ
        //эскпортируем модель
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
    public void loadmodel() {
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


    @Test
    @Ignore
    public void realDC() {


        driver.findElement(By.xpath("//li[@class='bp3-tree-node bp3-tree-node-expanded'][1]//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-open']")).click();
        driver.findElement(By.xpath("//li[@class='bp3-tree-node'][1]//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-closed']")).click();
        driver.findElement(By.xpath("//*[text()='Test_Datacatalog_23112018']")).click();


        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='top-buttons']/button[@class='ui icon button'][2]/i[@class='large icon icon-plus']")).click();
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div/div[@class='ui card panel collapse-panel']/div[@class='content']/form[@class='ui form']/div[@class='equal width fields'][1]/div[@class='field']/div[@class='ui input']/input")).sendKeys("DS" + str);
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div/div[@class='ui card panel collapse-panel']/div[@class='content']/form[@class='ui form']/div[@class='equal width fields'][2]/div[@class='field']/div[@class='ui input']/input")).sendKeys("DS" + str);

        //сохраняем
        // driver.findElement(By.xpath("//div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui pointing secondary menu']/a[@class='right item']/i[@class='large icon icon-floppy-disk']")).click();


        driver.findElement(By.xpath("//*[text()='Attributes']")).click();
        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='hider invert']/div[@class='to-hide']/div[@class='attribute-tree-buttons']"));
        builder.moveToElement(element).build().perform();

        //создание файлы
        //driver.findElement(By.xpath("//i[@class=' large icon icon-file-empty']")).click();
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='hider invert']/div[@class='to-hide']/div[@class='attribute-tree-buttons']/button[@class='ui circular icon left floated button'][1]/i[@class='large icon icon-file-empty']")).click();

        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']/form[@class='ui form']/div[@class='ui card panel collapse-panel']/div[@class='content']/div[@class='equal width fields'][1]/div[@class='field']/div[@class='ui input']/input")).sendKeys("DS1" + str);
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']/form[@class='ui form']/div[@class='ui card panel collapse-panel']/div[@class='content']/div[@class='equal width fields'][2]/div[@class='field']/div[@class='ui input']/input")).sendKeys("DS1" + str);
        ///  //сохраняем
        // driver.findElement(By.xpath("//div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui pointing secondary menu']/a[@class='right item']/i[@class='large icon icon-floppy-disk']")).click();
//++проверить,что реально есть
        //проверим импорт файла
        driver.findElement(By.xpath("//i[@class='large icon icon-upload']")).click();
        WebElement uploadElement1 = driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='content']/div[@class='ui action input']/input"));
        uploadElement1.sendKeys("C:\\Users\\natalia.chaplygina\\Documents\\Рабочее\\VendorSource_EQ_source.xlsx");
        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='content']/div[@class='ui action input']/button[@class='ui icon button']")).click();

        //*****тут надо проверить что файл реально загрузился,т.е. появился ряд атрибутов
        //сохраняем
        // driver.findElement(By.xpath("//div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui pointing secondary menu']/a[@class='right item']/i[@class='large icon icon-floppy-disk']")).click();

//посчитать кол-вл элементов здесь
        WebElement checkmove = driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='bp3-tree']/ul[@class='bp3-tree-node-list bp3-tree-root']/li[@class='bp3-tree-node'][28]/div[@class='bp3-tree-node-content bp3-tree-node-content-0']/span[@class='bp3-tree-node-label']"));

//checkmove.getText();
        System.out.println(checkmove.getText());
        checkmove.click();
//двинем элемент вверх
        driver.findElement(By.xpath("//i[@class='large icon icon-exit-up2']")).click();
        driver.findElement(By.xpath("//i[@class='large icon icon-exit-up2']")).click();
        driver.findElement(By.xpath("//i[@class='large icon icon-exit-down2']")).click();


        WebElement checkmove1 = driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='bp3-tree']/ul[@class='bp3-tree-node-list bp3-tree-root']/li[@class='bp3-tree-node'][27]/div[@class='bp3-tree-node-content bp3-tree-node-content-0']/span[@class='bp3-tree-node-label']"));
        System.out.println(checkmove1.getText());
        ////////////////ОШИБКА, ПРИЧИНА НЕЯСНА
        // Assert.assertEquals(checkmove1.getText(), checkmove.getText());//значение по дефолту подставилось верно


        //удалить что-нибудь наполседок
        WebElement checkmove3 = driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='bp3-tree']/ul[@class='bp3-tree-node-list bp3-tree-root']/li[@class='bp3-tree-node'][7]/div[@class='bp3-tree-node-content bp3-tree-node-content-0']/span[@class='bp3-tree-node-label']"));
        checkmove3.click();
        driver.findElement(By.xpath("//i[@class='large icon icon-trash2']")).click();
        driver.findElement(By.xpath("//body[@class='dimmable dimmed blurring']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui tiny modal transition visible active']/div[@class='actions']/button[@class='ui primary button']")).click();
        WebElement checkmove4 = driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='bp3-tree']/ul[@class='bp3-tree-node-list bp3-tree-root']/li[@class='bp3-tree-node'][7]/div[@class='bp3-tree-node-content bp3-tree-node-content-0']/span[@class='bp3-tree-node-label']"));
        //проверка реально ли удалило
        Assert.assertNotEquals(checkmove3.getText(), checkmove4.getText());
        //сохраняем
        // driver.findElement(By.xpath("//div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui pointing secondary menu']/a[@class='right item']/i[@class='large icon icon-floppy-disk']")).click();


    }

    @Test

    public void newDQ() {
        //create dq policy


        driver.findElement(By.xpath("//div[@class='hider']")).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("//div[@class='hider']")));
        WebElement addDQButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='hider']/div[@class='to-hide']/div[@class='sidebar-bottom']/button[3]")));
        addDQButton.click();
        driver.findElement(By.xpath("//div[@class='ui tiny modal transition visible active']/div[@class='actions']/button[3]")).click();
        //  driver.findElement(By.name("displayName")).sendKeys("policy" + "test_" + str);

        WebElement DQname = driver.findElement(By.name("displayName"));
        DQname.sendKeys("policy" + "test_" + str);

        driver.findElement(By.name("name")).sendKeys("policy" + "test_" + str);
        driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons dq-policy']/div[@class='main']/div[@class='ui segment']/div[@class='two-sides']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/form[@class='ui form']/div[@class='equal width fields'][3]/div[@class='field']/div[@class='ui fluid selection dropdown']/i[@class='dropdown icon']")).click();
        driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons dq-policy']/div[@class='main']/div[@class='ui segment']/div[@class='two-sides']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/form[@class='ui form']/div[@class='equal width fields'][3]/div[@class='field']/div[@class='ui active visible fluid selection dropdown']/div[@class='visible menu transition']/div[@class='item'][1]")).click();


        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons dq-policy']/div[@class='main']/div[@class='ui segment']/div[@class='two-sides']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/form[@class='ui form']/div[@class='equal width fields'][4]/div[@class='field'][1]/div[@class='ui fluid multiple search selection dropdown']"));
        actions.moveToElement(element);
        actions.click();
        actions.sendKeys("TEST");
        actions.build().perform();

        driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons dq-policy']/div[@class='main']/div[@class='ui segment']/div[@class='two-sides']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/form[@class='ui form']/div[@class='equal width fields'][4]/div[@class='field'][1]/div[@class='ui active visible fluid multiple search selection dropdown']/div[@class='visible menu transition']/div[@class='selected item']")).click();

        driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons dq-policy']/div[@class='main']/div[@class='ui segment']/div[@class='two-sides']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/form[@class='ui form']/div[@class='equal width fields'][5]/div[@class='field']/div/div[@class='hwt-container']/textarea[@class='antlr-input hwt-input hwt-content']")).sendKeys("listallinstrumentswherecountry of inc=''orcountry of inc=''");
///проставить галки на рулах//не получается

        driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons dq-policy']/div[@class='main']/div[@class='ui segment']/div[@class='SplitPane vertical']/div[@class='Pane vertical Pane2  ']/div/div[@class='ui card panel collapse-panel']/div[@class='content']/div[3]/div[@class='ReactTable']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][1]/div[@class='rt-tr un-dq-rule-system -odd']/div[@class='rt-td xh-highlight']/div[@class='ui checkbox'][1]")).click();

        // закрываем окно без сохранения
        //    driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='ui pointing secondary menu']/a[@class='active item']/span[@class='close-tab']")).click();
        //   driver.findElement(By.xpath("//body[@class='dimmable dimmed blurring']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui tiny modal transition visible active']/div[@class='actions']/button[@class='ui primary button']")).click();

        //сохраняем
        //  driver.findElement(By.xpath("//div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui pointing secondary menu']/a[@class='right item']/i[@class='large icon icon-floppy-disk']")).click();


    }

    @Test
    @Ignore
    public void realDQ() {


        driver.findElement(By.xpath("//li[@class='bp3-tree-node bp3-tree-node-expanded'][3]//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-open']")).click();
        driver.findElement(By.xpath("//li[@class='bp3-tree-node']//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-closed']")).click();
        driver.findElement(By.xpath("//*[text()='Test_policy_23112018']")).click();
        //   /html/body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons dq-policy']/div[@class='main']/div[@class='ui segment']/div[@class='two-sides']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/form[@class='ui form']/div[@class='equal width fields'][1]/div[@class='field'][1]/div[@class='ui input']/input

    }


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
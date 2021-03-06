package UiTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by natalia.chaplygina on 10.12.2018.
 */
public class LookupTest {


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
        String driverepath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", driverepath+"\\tmp\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // driver.get("http://prototype.datasynthes.com/synthes-frontend/model");
        driver.get("https://prototype.datasynthes.com/synthes-frontend/model");


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
    @DisplayName("Creation new lookup")
    // @Description("Upload large file with checking the presence of the file with the results and links to the logs ")
    public void A() {
//createLookUpTest

        driver.findElement(By.xpath("//div[@class='hider']")).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("//div[@class='hider']")));
        WebElement addEntityButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='hider']/div[@class='to-hide']/div[@class='sidebar-bottom']/button[3]")));
        addEntityButton.click();


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
        // driver.findElement(By.xpath("//*[text()='Business Glossary']")).click();


        driver.findElement(By.xpath("//div[@class='ui pointing secondary menu']/a[@class='active item']/span[@class='close-tab']/span[@class='bp3-icon bp3-icon-small-cross']")).click();

    }


    //работа с  существующим lookup
    @Test
    @DisplayName("Update lookup")
    @Description("Update lookup with filling some attributes")
    public void B() {
//realLookUpTest

        driver.findElement(By.xpath("//li[@class='bp3-tree-node bp3-tree-node-expanded'][4]//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-open']")).click();
        driver.findElement(By.xpath("//li[@class='bp3-tree-node']//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-closed']")).click();

//ищем созданный в предыдущем тесте
        driver.findElement(By.xpath("//*[text()='lookuptest_" + str + "']")).click();

        driver.findElement(By.xpath("//*[text()='Business Glossary']")).click();


        //проверим,что уже существует автоматически созданный coded

//       Assert.assertNotNull();****************************************************************************
        driver.findElement(By.xpath("//*[text()='Code']")).click();





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

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!НЕ рaботает

        //         driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][10]//div[@class='field']//div[@class='ui fluid multiple selection dropdown']")).click();
        //         driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][10]//div[@class='field']//div[@class='ui active visible fluid multiple selection dropdown']//div[@class='visible menu transition']//div[@class='item'][1]")).click();
        //          driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][10]/div[@class='field']/div[@class='ui active visible fluid multiple selection dropdown']/i[@class='dropdown icon']")).click();

        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][11]//div[@class='field']//div[@class='ui fluid multiple selection dropdown']")).click();
        driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][11]//div[@class='field']//div[@class='ui active visible fluid multiple selection dropdown']//div[@class='visible menu transition']//div[@class='item'][1]")).click();

///html/body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][11]/div[@class='field']/div[@class='ui active visible fluid multiple selection dropdown']/i[@class='dropdown icon']


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

        //все рaботает

        //    driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][10]//div[@class='field']//div[@class='ui fluid multiple selection dropdown']")).click();
        //    driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][10]//div[@class='field']//div[@class='ui active visible fluid multiple selection dropdown']//div[@class='visible menu transition']//div[@class='item'][1]")).click();
        //    driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][10]//div[@class='field']//div[@class='ui active visible fluid multiple selection dropdown']//i[@class='dropdown icon']")).click();

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

        //подвигаем вверх/вниз
        //?????????????????????????

        //сохраняем

        driver.findElement(By.xpath("//div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui pointing secondary menu']/a[@class='right item']/i[@class='large icon icon-floppy-disk']")).click();


        System.out.println ("нажали сохранить");
        String alertText = "";
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification-popup")));
        WebElement toast1 = driver.findElement(By.className("notification-popup"));
        alertText = toast1.getText();
        System.out.println(alertText);
        Assert.assertEquals("Data is saved!", alertText);
        ///а вот тут частенько падает ошибка, потому что каким то чудом не сохраняется и при закрытии всплывает окно
        //закрыли
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='ui pointing secondary menu']/a[@class='active item']/span[@class='close-tab']/span[@class='bp3-icon bp3-icon-small-cross']")).click();


    }

    //открыли и проверили что все что ввели- сохранилось

    @Test
    @DisplayName("Delete lookup")
    //   @Description("Update lookup with filling some attributes")
    public void C() {

//CheckLookupTest
        //      driver.findElement(By.xpath("//li[@class='bp3-tree-node bp3-tree-node-expanded'][5]//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-open']")).click();
        //      driver.findElement(By.xpath("//li[@class='bp3-tree-node']//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-closed']")).click();

        System.out.println ("нажали это все");
//ищем созданный в предыдущем тесте
        driver.findElement(By.xpath("//*[text()='lookuptest_" + str + "']")).click();


        System.out.println ("нашли элемент");

        Assert.assertEquals(driver.findElement(By.name("displayName")).getAttribute("value"), "lookup" + "test_" + str);

        System.out.println ("1");

        Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"), "lookup" + "test_" + str);

        System.out.println ("2");

//порверим что стало для testnotcoded_
        driver.findElement(By.xpath("//*[text()='Business Glossary']")).click();
        driver.findElement(By.xpath("//*[text()='testnotcoded" + str + "']")).click();
//        Assert.assertEquals(driver.findElement(By.name("displayName")).getAttribute("value"), "testnotcoded" + str);
        Assert.assertEquals(driver.findElement(By.name("definition")).getAttribute("value"), "testnotcoded" + str);
        Assert.assertEquals(driver.findElement(By.name("example")).getAttribute("value"), "testnotcoded" + str);
        Assert.assertEquals(driver.findElement(By.name("acronym")).getAttribute("value"), "testnotcoded" + str);


        //проверяем Synonyms:

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][9]/div[@class='field']/div[@class='entity-synonyms']/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group']/div[@class='rt-tr -odd']/div[@class='rt-td'][1]/div[@class='ui fluid selection dropdown']")).getText(),"SSS");
        //ошибка
//        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][9]/div[@class='field']/div[@class='entity-synonyms']/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group']/div[@class='rt-tr -odd']/div[@class='rt-td'][2]/div[@class='ui input']")).getText(),"asdasd");


        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][11]/div[@class='field']/div[@class='ui fluid multiple selection dropdown']/a[@class='ui label']")).getText(),"Accounting Glossary");
        Assert.assertEquals(driver.findElement(By.name("synonym")).getAttribute("value"),"asdasd");


        //проверяем MANAGED BY
        Assert.assertEquals(driver.findElement(By.name("dataSteward")).getAttribute("value"),"QA");
        Assert.assertEquals(driver.findElement(By.name("phone")).getAttribute("value"),"892100305ABC");
        Assert.assertEquals(driver.findElement(By.name("email")).getAttribute("value"),"abc@yandex.ru");
        Assert.assertEquals(driver.findElement(By.name("location")).getAttribute("value"),"London");


        //  driver.findElement(By.xpath("//div[@id='root']//div[@id='layout']//main//div[@class='data-model-container sidebar-show']//div[@class='content items-tab']//div[@class='active tab ui']//div[@class='container-with-side-buttons']//div[@class='main entities-tab']//div[@class='ui segment active tab']//div[@class='SplitPane  vertical ']//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][3]//div[@class='content']//div[@class='equal width fields'][4]//div[@class='field'][1]//div[@class='ui fluid selection dropdown']//i[@class='dropdown icon']")).click();
        // driver.findElement(By.xpath("//div[@id='root']//div[@id='layout']//main//div[@class='data-model-container sidebar-show']//div[@class='content items-tab']//div[@class='active tab ui']//div[@class='container-with-side-buttons']//div[@class='main entities-tab']//div[@class='ui segment active tab']//div[@class='SplitPane  vertical ']//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][3]//div[@class='content']//div[@class='equal width fields'][4]//div[@class='field'][1]//div[@class='ui active visible fluid selection dropdown']//div[@class='visible menu transition']//div[@class='selected item'][1]")).click();


        //driver.findElement(By.xpath("//div[@id='root']//div[@id='layout']//main//div[@class='data-model-container sidebar-show']//div[@class='content items-tab']//div[@class='active tab ui']//div[@class='container-with-side-buttons']//div[@class='main entities-tab']//div[@class='ui segment active tab']//div[@class='SplitPane  vertical ']//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][3]//div[@class='content']//div[@class='equal width fields'][4]//div[@class='field'][2]//div[@class='ui fluid selection dropdown']//i[@class='dropdown icon']")).click();

        //river.findElement(By.xpath("//div[@id='root']//div[@id='layout']//main//div[@class='data-model-container sidebar-show']//div[@class='content items-tab']//div[@class='active tab ui']//div[@class='container-with-side-buttons']//div[@class='main entities-tab']//div[@class='ui segment active tab']//div[@class='SplitPane  vertical ']//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][3]//div[@class='content']//div[@class='equal width fields'][4]//div[@class='field'][2]//div[@class='ui active visible fluid selection dropdown']//div[@class='visible menu transition']//div[@class='selected item'][1]")).click();

        Assert.assertEquals(driver.findElement(By.name("businessFunc")).getAttribute("value"),"QA test");










//порверим что стало для testcoded_

        driver.findElement(By.xpath("//*[text()='Business Glossary']")).click();
        driver.findElement(By.xpath("//*[text()='codedtest" + str + "']")).click();
//        Assert.assertEquals(driver.findElement(By.name("displayName")).getAttribute("value"), "codedtest" + str);
        Assert.assertEquals(driver.findElement(By.name("definition")).getAttribute("value"), "codedtest" + str);
        Assert.assertEquals(driver.findElement(By.name("example")).getAttribute("value"), "codedtest" + str);
        Assert.assertEquals(driver.findElement(By.name("acronym")).getAttribute("value"), "codedtest" + str);
    }


    @Test

    public void D() {
//DeletenewLookupTes

        //       driver.findElement(By.xpath("//li[@class='bp3-tree-node bp3-tree-node-expanded'][5]//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-open']")).click();
        //      driver.findElement(By.xpath("//li[@class='bp3-tree-node']//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-closed']")).click();

//ищем созданный в предыдущем тесте
        driver.findElement(By.xpath("//*[text()='lookuptest_" + str + "']")).click();

        ///на перспектву вынести удаление в отдельный класс
        driver.findElement(By.xpath("//div[@class='hider']")).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("//div[@class='hider']")));
        WebElement deleteButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='hider']/div[@class='to-hide']/div[@class='sidebar-bottom']/button[4]")));
        deleteButton.click();
        driver.findElement(By.xpath("//div[@class='ui tiny modal transition visible active custom-confirm']/div[@class='actions']/button[@class='ui primary button']")).click();
        //проверить всплывающее окно с инфо об удалении

        String alertText = "";
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification-popup")));
        WebElement toast1 = driver.findElement(By.className("notification-popup"));
        alertText = toast1.getText();
        System.out.println(alertText);
        Assert.assertEquals("Data is deleted!", alertText);


    }


}

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
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

    public void createLookUpTest() {

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

    public void realLookUpTest() {


        driver.findElement(By.xpath("//li[@class='bp3-tree-node bp3-tree-node-expanded'][5]//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-open']")).click();
        driver.findElement(By.xpath("//li[@class='bp3-tree-node']//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-closed']")).click();

//ищем созданный в предыдущем тесте
        driver.findElement(By.xpath("//*[text()='lookuptest_" + str + "']")).click();

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

        //НЕ рaботает

    //    driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][10]//div[@class='field']//div[@class='ui fluid multiple selection dropdown']")).click();
    //    driver.findElement(By.xpath("//div[@class='Pane vertical Pane2  ']//div[@class='entity-business-glossary']//form[@class='ui form']//div[@class='ui card panel collapse-panel'][2]//div[@class='content']//div[@class='equal width fields'][10]//div[@class='field']//div[@class='ui active visible fluid multiple selection dropdown']//div[@class='visible menu transition']//div[@class='item'][1]")).click();
    //     driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][10]/div[@class='field']/div[@class='ui active visible fluid multiple selection dropdown']/i[@class='dropdown icon']")).click();

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


        //сохраняем

        driver.findElement(By.xpath("//div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui pointing secondary menu']/a[@class='right item']/i[@class='large icon icon-floppy-disk']")).click();

        //подвигаем вверх/вниз
        //?????????????????????????

        //************************НАДО ВЫНЕСТИ


        //закрыли
   //    driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='ui pointing secondary menu']/a[@class='active item']/span[@class='close-tab']/span[@class='bp3-icon bp3-icon-small-cross']']")).click();
    }


    @Test

    public void WDeletenewLookupTest() {

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


    }


}

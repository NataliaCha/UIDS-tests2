package UiTest; /**
 * Created by natalia.chaplygina on 19.12.2018.
 */

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
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class NestedAttrTest {

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
        System.setProperty("webdriver.chrome.driver", driverepath + "\\tmp\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://prototype.datasynthes.com/synthes-frontend/model");
        WebElement loginField = driver.findElement(By.name("userName"));
        loginField.sendKeys("admin");
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("UsaeZ9ph");
        WebElement loginButton = driver.findElement(By.xpath("//button[text()='LOG IN']"));
        loginButton.click();
    }


    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    // создание nested attr
    public void A() {


        driver.findElement(By.xpath("//li[@class='bp3-tree-node bp3-tree-node-expanded'][4]//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-open']")).click();
        driver.findElement(By.xpath("//li[@class='bp3-tree-node']//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-closed']")).click();


        //  driver.findElement(By.xpath("//*[text()='Instrument Model']")).click();

        driver.findElement(By.xpath("//*[contains(text(),'Instrument Model')]")).click();


        driver.findElement(By.xpath("//*[text()='Business Glossary']")).click();
        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='hider invert']/div[@class='to-hide']/div[@class='attribute-tree-buttons']"));
        builder.moveToElement(element).build().perform();

        driver.findElement(By.xpath("//i[@class='large icon icon-file-empty']")).click();
        driver.findElement(By.xpath("//div[@class='visible menu transition']//div[@class='item'][2]")).click();


        driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][1]/div[@class='field']/div[@class='ui input']/input")).sendKeys("nestedattr" + str);
        driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][2]/div[@class='field']/div[@class='ui input']/input")).sendKeys("nestedattr" + str);
        driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][3]/div[@class='field']/textarea")).sendKeys("nestedattr" + str);

        driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][4]/div[@class='field']/div[@class='ui fluid selection dropdown']/i[@class='dropdown icon']")).click();
        driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][4]/div[@class='field']/div[@class='ui active visible fluid selection dropdown']/div[@class='visible menu transition']/div[@class='item'][1]/span[@class='text']")).click();


        driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][4]/div[@class='field']/div[@class='ui fluid selection dropdown']")).getText();
        ///div[@class='text']


        driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][5]/div[@class='field']/div[@class='ui fitted checkbox']")).click();

        driver.findElement(By.xpath("//div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui pointing secondary menu']/a[@class='right item']/i[@class='large icon icon-floppy-disk']")).click();


        System.out.println("нажали сохранить");
        String alertText = "";
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification-popup")));
        WebElement toast1 = driver.findElement(By.className("notification-popup"));
        alertText = toast1.getText();
        System.out.println(alertText);
        Assert.assertEquals("Data is saved!", alertText);


        //Проверим,что в relationships все появилось
        WebElement relfrom = driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/div[@class='ui card panel collapse-panel']/div[@class='content']/div/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group']/div[@class='rt-tr un-dq-rule-system -odd']/div[@class='rt-td'][1]"));
        Assert.assertEquals("Instrument.nestedattr" + str, relfrom.getText());
        WebElement relto = driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/div[@class='ui card panel collapse-panel']/div[@class='content']/div/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group']/div[@class='rt-tr un-dq-rule-system -odd']/div[@class='rt-td'][2]"));
        Assert.assertEquals("Instrument", relto.getText());
        WebElement reltype = driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/div[@class='ui card panel collapse-panel']/div[@class='content']/div/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group']/div[@class='rt-tr un-dq-rule-system -odd']/div[@class='rt-td'][3]"));

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][4]/div[@class='field']/div[@class='ui fluid selection dropdown']")).getText(), reltype.getText());

//тут ошибка из-за бага
        //     Assert.assertEquals("YES",driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/div[@class='ui card panel collapse-panel']/div[@class='content']/div/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group']/div[@class='rt-tr un-dq-rule-system -odd']/div[@class='rt-td'][4]")).getText());

//проверить,что элемент послений среди mested
        // driver.findElement(By.xpath("//div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='bp3-tree']/ul[@class='bp3-tree-node-list bp3-tree-root']/li[@class='bp3-tree-node bp3-tree-node-expanded']/div[@class='bp3-collapse']/div[@class='bp3-collapse-body'][last()]")).getText();
//закрое мокно

        driver.findElement(By.xpath("//div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='ui pointing secondary menu']/a[@class='active item']/span[@class='close-tab']/span[@class='bp3-icon bp3-icon-small-cross']")).click();
    }

    @Test

    public void B() {

//add attribute
        driver.findElement(By.xpath("//li[@class='bp3-tree-node bp3-tree-node-expanded'][4]//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-open']")).click();
        driver.findElement(By.xpath("//li[@class='bp3-tree-node']//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-closed']")).click();

//ищем созданный в предыдущем тесте
        //  driver.findElement(By.xpath("//*[text()='Instrument Model']")).click();

        driver.findElement(By.xpath("//*[contains(text(),'Instrument Model')]")).click();


        driver.findElement(By.xpath("//*[text()='Business Glossary']")).click();
        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='hider invert']/div[@class='to-hide']/div[@class='attribute-tree-buttons']"));
        builder.moveToElement(element).build().perform();

        driver.findElement(By.xpath("//i[@class='large icon icon-file-empty']")).click();
        driver.findElement(By.xpath("//div[@class='visible menu transition']//div[@class='item'][1]")).click();


        driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][1]/div[@class='field']/div[@class='ui input']//input")).sendKeys("notnestedattr" + str);
        driver.findElement(By.name("definition")).sendKeys("notnestedattr" + str);
        driver.findElement(By.name("example")).sendKeys("notnestedattr" + str);
        driver.findElement(By.name("acronym")).sendKeys("notnestedattr" + str);


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


        System.out.println("нажали сохранить");
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

    @Test

    public void C() {

//add attribute in nested


        driver.findElement(By.xpath("//li[@class='bp3-tree-node bp3-tree-node-expanded'][4]//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-open']")).click();
        driver.findElement(By.xpath("//li[@class='bp3-tree-node']//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-closed']")).click();

//ищем созданный в предыдущем тесте
        //driver.findElement(By.xpath("//*[text()='Instrument Model']")).click();

        driver.findElement(By.xpath("//*[contains (text(),'Instrument Model')]")).click();

        driver.findElement(By.xpath("//*[text()='Business Glossary']")).click();
        driver.findElement(By.xpath("//*[text()='nestedattr" + str + "']")).click();

        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='hider invert']/div[@class='to-hide']/div[@class='attribute-tree-buttons']"));
        builder.moveToElement(element).build().perform();

        driver.findElement(By.xpath("//i[@class='large icon icon-file-empty']")).click();
        driver.findElement(By.xpath("//div[@class='visible menu transition']//div[@class='item'][1]")).click();


        driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][1]/div[@class='field']/div[@class='ui input']//input")).sendKeys("notnestedattr_1" + str);
        driver.findElement(By.name("definition")).sendKeys("notnestedattr_1" + str);
        driver.findElement(By.name("example")).sendKeys("notnestedattr_1" + str);
        driver.findElement(By.name("acronym")).sendKeys("notnestedattr_1" + str);


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


        System.out.println("нажали сохранить");
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


    @Test
//Add nested in nested
    public void D() {
        driver.findElement(By.xpath("//li[@class='bp3-tree-node bp3-tree-node-expanded'][4]//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-open']")).click();
        driver.findElement(By.xpath("//li[@class='bp3-tree-node']//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-closed']")).click();


        // driver.findElement(By.xpath("//*[text()='Instrument Model']")).click();


        driver.findElement(By.xpath("//*[contains(text(),'Instrument Model')]")).click();

        driver.findElement(By.xpath("//*[text()='Business Glossary']")).click();
        driver.findElement(By.xpath("//*[text()='nestedattr" + str + "']")).click();

        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='hider invert']/div[@class='to-hide']/div[@class='attribute-tree-buttons']"));
        builder.moveToElement(element).build().perform();

        driver.findElement(By.xpath("//i[@class='large icon icon-file-empty']")).click();
        driver.findElement(By.xpath("//div[@class='visible menu transition']//div[@class='item'][2]")).click();


        driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][1]/div[@class='field']/div[@class='ui input']/input")).sendKeys("nestednestedattr" + str);
        driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][2]/div[@class='field']/div[@class='ui input']/input")).sendKeys("nestednestedattr" + str);
        driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][3]/div[@class='field']/textarea")).sendKeys("nestednestedattr" + str);

        driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][4]/div[@class='field']/div[@class='ui fluid selection dropdown']/i[@class='dropdown icon']")).click();
        driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][4]/div[@class='field']/div[@class='ui active visible fluid selection dropdown']/div[@class='visible menu transition']/div[@class='item'][1]/span[@class='text']")).click();


        driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][4]/div[@class='field']/div[@class='ui fluid selection dropdown']")).getText();
        ///div[@class='text']


        driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][5]/div[@class='field']/div[@class='ui fitted checkbox']")).click();


/*
        driver.findElement(By.xpath("//div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui pointing secondary menu']/a[@class='right item']/i[@class='large icon icon-floppy-disk']")).click();

        System.out.println ("нажали сохранить");
        String alertText = "";
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification-popup")));
        WebElement toast1 = driver.findElement(By.className("notification-popup"));
        alertText = toast1.getText();
        System.out.println(alertText);
        Assert.assertEquals("Data is saved!", alertText);
*/

        //Проверим,что в relationships все появилось
        WebElement relfrom = driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/div[@class='ui card panel collapse-panel']/div[@class='content']/div/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group']/div[@class='rt-tr un-dq-rule-system -odd']/div[@class='rt-td'][1]"));
        Assert.assertEquals("Instrument.nestedattr" + str + ".nestednestedattr" + str, relfrom.getText());
        WebElement relto = driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/div[@class='ui card panel collapse-panel']/div[@class='content']/div/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group']/div[@class='rt-tr un-dq-rule-system -odd']/div[@class='rt-td'][2]"));
        Assert.assertEquals("nestedattr" + str, relto.getText());
        WebElement reltype = driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/div[@class='ui card panel collapse-panel']/div[@class='content']/div/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group']/div[@class='rt-tr un-dq-rule-system -odd']/div[@class='rt-td'][3]"));

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/div[@class='equal width fields'][4]/div[@class='field']/div[@class='ui fluid selection dropdown']")).getText(), reltype.getText());

//тут ошибка из-за бага
        //     Assert.assertEquals("YES",driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-complex-attribute']/div[@class='ui card panel collapse-panel']/div[@class='content']/div/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group']/div[@class='rt-tr un-dq-rule-system -odd']/div[@class='rt-td'][4]")).getText());

//проверить,что элемент послений среди mested
        // driver.findElement(By.xpath("//div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='bp3-tree']/ul[@class='bp3-tree-node-list bp3-tree-root']/li[@class='bp3-tree-node bp3-tree-node-expanded']/div[@class='bp3-collapse']/div[@class='bp3-collapse-body'][last()]")).getText();


        driver.findElement(By.xpath(".//*[text()='nestedattr" + str + "']")).click();
        driver.findElement(By.xpath("//div[@class='attribute-tree-buttons']/button[3]")).click();
        WebElement cl=driver.findElement(By.xpath("//div[@class='actions']/button[@class='ui primary button']"));
        String clTex= cl.getText();
        cl.click();

        // driver.findElement(By.xpath("//body[@class='dimmable dimmed blurring']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui tiny modal transition visible active custom-confirm']/div[@class='actions']/button[@class='ui primary button']")).click();




        driver.findElement(By.xpath(".//*[text()='notnestedattr" + str + "']")).click();
        driver.findElement(By.xpath("//div[@class='attribute-tree-buttons']/button[3]")).click();
        driver.findElement(By.xpath("//body[@class='dimmable dimmed blurring']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui tiny modal transition visible active custom-confirm']/div[@class='actions']/button[@class='ui primary button']")).click();

//закрое мокноvv
        driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui pointing secondary menu']/a[@class='right item']/i[@class='large icon icon-floppy-disk']")).click();

        driver.findElement(By.xpath("//div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='ui pointing secondary menu']/a[@class='active item']/span[@class='close-tab']/span[@class='bp3-icon bp3-icon-small-cross']")).click();
        driver.findElement(By.xpath("//body[@class='dimmable dimmed blurring']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui tiny modal transition visible active custom-confirm']/div[@class='actions']/button[@class='ui primary button']")).click();


    }

}

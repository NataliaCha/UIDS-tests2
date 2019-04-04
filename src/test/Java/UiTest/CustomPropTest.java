package UiTest;
/**
 * Created by natalia.chaplygina on 25.12.2018.
 */


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


public class CustomPropTest {


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
    public static void tearDown()
    {
        driver.quit();
    }

    @Test
    @DisplayName(" Creation custom property")
    // создание cusutom property to itsself+cusutom property to root
    public void A() {


        driver.findElement(By.xpath("//li[@class='bp3-tree-node bp3-tree-node-expanded'][4]//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-open']")).click();
        driver.findElement(By.xpath("//li[@class='bp3-tree-node']//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-closed']")).click();


        //  driver.findElement(By.xpath("//*[text()='Instrument Model']")).click();

        driver.findElement(By.xpath("//*[contains(text(),'Instrument Model')]")).click();

        driver.findElement(By.xpath("//*[text()='Business Glossary']")).click();
        driver.findElement(By.xpath("/html/body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='bp3-tree']/ul[@class='bp3-tree-node-list bp3-tree-root']/li[@class='bp3-tree-node bp3-tree-node-expanded']/div[@class='bp3-collapse']/div[@class='bp3-collapse-body']/ul[@class='bp3-tree-node-list']/li[@class='bp3-tree-node inheritance']/div[@class='bp3-tree-node-content bp3-tree-node-content-1']/span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-closed']")).click();

        driver.findElement(By.xpath("//*[text()='Divided Frequency Code']")).click();

        driver.findElement(By.xpath("//div[@class='ui card panel collapse-panel'][4]/div[@class='header header']/a[@class='setting-button']/i[@class='setting big icon']")).click();

        driver.findElement(By.xpath("//div[@class='ui page modals dimmer transition visible active']/div[@class='ui large modal transition visible active']/div[@class='image scrolling content']/div[@class='description']/div[2]/a/i[@class='plus circle large icon']")).click();
        /*
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div[@class='entity-business-glossary']/form[@class='ui form']/div[@class='ui card panel collapse-panel'][4]/div[@class='content']/div/a/i[@class='plus circle large icon']")).click();
       */
        driver.findElement(By.xpath("//div[@class='ui page modals dimmer transition visible active']/div[@class='ui large modal transition visible active']/div[@class='image scrolling content']/div[@class='description']/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group']/div[@class='rt-tr -odd']/div[@class='rt-td'][2]/div")).clear();
        driver.findElement(By.xpath("//div[@class='ui page modals dimmer transition visible active']/div[@class='ui large modal transition visible active']/div[@class='image scrolling content']/div[@class='description']/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group']/div[@class='rt-tr -odd']/div[@class='rt-td'][2]/div")).sendKeys("one");


        driver.findElement(By.xpath("//div[@class='ui page modals dimmer transition visible active']/div[@class='ui large modal transition visible active']/div[@class='image scrolling content']/div[@class='description']/div[2]/a/i[@class='plus circle large icon']")).click();
        driver.findElement(By.xpath("//div[@class='ui page modals dimmer transition visible active']/div[@class='ui large modal transition visible active']/div[@class='image scrolling content']/div[@class='description']/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group']/div[@class='rt-tr -even']/div[@class='rt-td'][2]/div")).clear();
        driver.findElement(By.xpath("//div[@class='ui page modals dimmer transition visible active']/div[@class='ui large modal transition visible active']/div[@class='image scrolling content']/div[@class='description']/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group']/div[@class='rt-tr -even']/div[@class='rt-td'][2]/div")).sendKeys("two");
        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui large modal transition visible active']/div[@class='image scrolling content']/div[@class='description']/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][2]/div[@class='rt-tr -even']/div[@class='rt-td'][8]/div[@class='ui dropdown']/span/a")).click();

        //привязади two к root
        driver.findElement(By.xpath("//html/body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui large modal transition visible active']/div[@class='image scrolling content']/div[@class='description']/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][2]/div[@class='rt-tr -even']/div[@class='rt-td'][8]/div[@class='ui active visible dropdown']/div[@class='menu transition visible']/div/div[@class='bp3-tree']/ul[@class='bp3-tree-node-list bp3-tree-root']/li[@class='bp3-tree-node bp3-tree-node-expanded']/div[@class='bp3-tree-node-content bp3-tree-node-content-0']/span[@class='bp3-tree-node-label']")).click();
        driver.findElement(By.xpath("//div[@class='ui page modals dimmer transition visible active']/div[@class='ui large modal transition visible active']/div[@class='actions']/button[@class='ui primary button']")).click();

        //проверим,что Property больше ни у кого не появилось

        driver.findElement(By.xpath("//*[text()='When Issued flag']")).click();
        driver.findElement(By.xpath("//div[@class='ui card panel collapse-panel'][4]/div[@class='header header']/a[@class='setting-button']/i[@class='setting big icon']")).click();
        WebElement props = driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui large modal transition visible active']/div[@class='image scrolling content']/div[@class='description']/div[@class='ReactTable -striped -highlight ds-table']"));

        String textprops = props.getText();
        System.out.println("какой текст получили" + textprops);

        //    System.out.println(textprops);
        Assert.assertFalse(textprops.contains("one"));
        Assert.assertTrue(textprops.contains("two"));
        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui large modal transition visible active']/i[@class='close icon']")).click();
//можно добавить еще атрибут для проверки

        driver.findElement(By.xpath("//*[text()='Country Code of the instrument identifier']")).click();
        driver.findElement(By.xpath("//div[@class='ui card panel collapse-panel'][4]/div[@class='header header']/a[@class='setting-button']/i[@class='setting big icon']")).click();
        WebElement props1 = driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui large modal transition visible active']/div[@class='image scrolling content']/div[@class='description']/div[@class='ReactTable -striped -highlight ds-table']"));

        String textprops1 = props1.getText();
        //    System.out.println(textprops);
        Assert.assertFalse(textprops1.contains("one"));
        Assert.assertTrue(textprops1.contains("two"));

        ///изменим имя



        WebElement bucl= driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui large modal transition visible active']/div[@class='actions']/button[@class='ui primary button']"));

        String tx= bucl.getText();
        System.out.print ("text which we received" + tx);
        bucl.click();




        //   driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui large modal transition visible active']/i[@class='close icon']"));


        // driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui large modal transition visible active']/div[@class='actions']/button[@class='ui primary button']"));


        driver.findElement(By.xpath("//*[text()='Divided Frequency Code']")).click();

        driver.findElement(By.xpath("//div[@class='ui card panel collapse-panel'][4]/div[@class='header header']/a[@class='setting-button']/i[@class='setting big icon']")).click();

        //   driver.findElement(By.xpath("//div[@class='ui page modals dimmer transition visible active']/div[@class='ui large modal transition visible active']/div[@class='image scrolling content']/div[@class='description']/div[2]/a/i[@class='plus circle large icon']")).click();


        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui large modal transition visible active']/div[@class='image scrolling content']/div[@class='description']/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][1]/div[@class='rt-tr -odd']/div[@class='rt-td'][9]/div/i[@class='trash outline alternate link icon']")).click();


        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active'][2]/div[@class='ui small modal transition visible active']/div[@class='actions']/button[@class='ui primary button']")).click();



        driver.findElement(By.xpath("//div/i[@class='trash outline alternate link icon']")).click();


        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active'][2]/div[@class='ui small modal transition visible active']/div[@class='actions']/button[@class='ui primary button']")).click();

        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui large modal transition visible active']/div[@class='actions']/button[@class='ui primary button']")).click();
//        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui large modal transition visible active']/i[@class='close icon']")).click();
        driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui pointing secondary menu']/a[@class='right item']/i[@class='large icon icon-floppy-disk']")).click();

    }





}
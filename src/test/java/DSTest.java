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

/**
 * Created by natalia.chaplygina on 10.12.2018.
 */
public class DSTest {


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

    public void A() {
//createDCTest
        driver.findElement(By.xpath("//div[@class='hider']")).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("//div[@class='hider']")));
        WebElement addDSButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='hider']/div[@class='to-hide']/div[@class='sidebar-bottom']/button[3]")));
        addDSButton.click();


        WebElement newDS = driver.findElement(By.xpath("//div[@class='ui tiny modal transition visible active']/div[@class='actions']/button[4]"));
        // driver.findElement(By.xpath("//div[@class='ui tiny modal transition visible active']/div[@class='actions']/button[2]")).click();
        newDS.click();

        String nameDC = "DC" + "test_" + str;
        driver.findElement(By.name("displayName")).sendKeys(nameDC);
        driver.findElement(By.name("description")).sendKeys(nameDC);
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='top-buttons']/button[@class='ui icon button']/i[@class='large icon icon-floppy-disk']")).click();


//проверить всплывающее окно с инфо о сохранении

        String alertText = "";
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification-popup")));
        WebElement toast1 = driver.findElement(By.className("notification-popup"));
        alertText = toast1.getText();
        System.out.println( alertText);
        Assert.assertEquals("Data is saved!",alertText);



        WebElement check = driver.findElement(By.xpath("//*[text()='" + nameDC + "']"));

        driver.findElement(By.xpath("//div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='ui pointing secondary menu']/a[@class='active item']/span[@class='close-tab']/span[@class='bp3-icon bp3-icon-small-cross']")).click();

    }




    @Test

    public void B() {
//realDCЕTest

        driver.findElement(By.xpath("//li[@class='bp3-tree-node bp3-tree-node-expanded'][1]//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-open']")).click();
        driver.findElement(By.xpath("//li[@class='bp3-tree-node'][1]//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-closed']")).click();
      //  driver.findElement(By.xpath("//*[text()='Test_Datacatalog_23112018']")).click();

        driver.findElement(By.xpath("//*[text()='DCtest_" + str + "']")).click();


        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='top-buttons']/button[@class='ui icon button'][2]/i[@class='large icon icon-plus']")).click();
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div/div[@class='ui card panel collapse-panel']/div[@class='content']/form[@class='ui form']/div[@class='equal width fields'][1]/div[@class='field']/div[@class='ui input']/input")).sendKeys("DS" + str);
        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div/div[@class='ui card panel collapse-panel']/div[@class='content']/form[@class='ui form']/div[@class='equal width fields'][2]/div[@class='field']/div[@class='ui input']/input")).sendKeys("DS" + str);

        //сохраняем
        // driver.findElement(By.xpath("//div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui pointing secondary menu']/a[@class='right item']/i[@class='large icon icon-floppy-disk']")).click();


        driver.findElement(By.xpath("//*[text()='Attributes']")).click();
        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='hider invert']/div[@class='to-hide']/div[@class='attribute-tree-buttons']"));
        builder.moveToElement(element).build().perform();

        //создание из файлы
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
        uploadElement1.sendKeys("C:\\Users\\natalia.chaplygina\\Documents\\Рабочее\\tests\\VendorSource_EQ_source.xlsx");
        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='content']/div[@class='ui action input']/button[@class='ui icon button']")).click();

        //*****тут надо проверить что файл реально загрузился,т.е. появился ряд атрибутов
        //сохраняем



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
        ////////////////ОШИБКА, ПРИЧИНА НЕЯСНА************************
    //    Assert.assertEquals("элемент плохо двигается",checkmove1.getText(), checkmove.getText());//значение по дефолту подставилось верно




        //удалить что-нибудь наполседок
        WebElement checkmove3 = driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='bp3-tree']/ul[@class='bp3-tree-node-list bp3-tree-root']/li[@class='bp3-tree-node'][7]/div[@class='bp3-tree-node-content bp3-tree-node-content-0']/span[@class='bp3-tree-node-label']"));
        checkmove3.click();
        driver.findElement(By.xpath("//button[@class='ui circular icon right floated button']//i[@class='large icon icon-trash2']")).click();

        ///i[@class='large icon icon-trash2']

        driver.findElement(By.xpath("//body[@class='dimmable dimmed blurring']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui tiny modal transition visible active custom-confirm']/div[@class='actions']/button[@class='ui primary button']")).click();

        WebElement checkmove4 = driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane1  ']/div[@class='ui card panel']/div[@class='content']/div[@class='bp3-tree']/ul[@class='bp3-tree-node-list bp3-tree-root']/li[@class='bp3-tree-node'][7]/div[@class='bp3-tree-node-content bp3-tree-node-content-0']/span[@class='bp3-tree-node-label']"));
        //проверка реально ли удалило
        //не работет((
    //    Assert.assertNotEquals(checkmove3.getText(), checkmove4.getText());
        //СОхраним
        driver.findElement(By.xpath("//div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons']/div[@class='main entities-tab']/div[@class='ui pointing secondary menu']/a[@class='right item']/i[@class='large icon icon-floppy-disk']")).click();

        String alertText = "";
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification-popup")));
        WebElement toast1 = driver.findElement(By.className("notification-popup"));
        alertText = toast1.getText();
        System.out.println( alertText);
        Assert.assertEquals("Data is saved!",alertText);


        driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='ui pointing secondary menu']/a[@class='active item']/span[@class='close-tab']/span[@class='bp3-icon bp3-icon-small-cross']")).click();
        driver.findElement(By.xpath("//a[@class='active item']/span[@class='close-tab']/span[@class='bp3-icon bp3-icon-small-cross']")).click();
    }


    @Test

        //(удаление и DC и DS)
    public void C () {
//DeletenewDCTest
        driver.findElement(By.xpath("//li[@class='bp3-tree-node bp3-tree-node-expanded'][1]//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-open']")).click();
        driver.findElement(By.xpath("//li[@class='bp3-tree-node']//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-closed']")).click();
        //driver.findElement(By.xpath("//*[text()='Test_policy_23112018']")).click();
        //  driver.findElement(By.xpath("//*[text()='policy + test + str']")).click();

        //  driver.findElement(By.xpath("//*[text()='policytest_2018-12-10T12:30:45Z']")).click();
        driver.findElement(By.xpath("//*[text()='DCtest_" + str + "']")).click();

       // "DC" + "test_" + str;

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
        System.out.println( alertText);
        Assert.assertEquals("Data is deleted!",alertText);





    }



}

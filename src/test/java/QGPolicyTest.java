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
public class QGPolicyTest {


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

    public void CreatenewDQTest() {
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
        Assert.assertEquals("policy" + "test_" + str, driver.findElement(By.name("name")).getAttribute("value"));


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


///проставить галки на рулах


        driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons dq-policy']/div[@class='main']/div[@class='ui segment']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div/div[@class='ui card panel collapse-panel']/div[@class='content']/div[3]/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][2]/div[@class='rt-tr un-dq-rule-system -even']/div[@class='rt-td'][1]")).click();
        driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons dq-policy']/div[@class='main']/div[@class='ui segment']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div/div[@class='ui card panel collapse-panel']/div[@class='content']/div[3]/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][8]/div[@class='rt-tr un-dq-rule-system -even']/div[@class='rt-td'][1]")).click();
        driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons dq-policy']/div[@class='main']/div[@class='ui segment']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div/div[@class='ui card panel collapse-panel']/div[@class='content']/div[3]/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][10]/div[@class='rt-tr un-dq-rule-system -even']/div[@class='rt-td'][1]")).click();



        //assigned rules only

        driver.findElement(By.xpath("//body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons dq-policy']/div[@class='main']/div[@class='ui segment']/div[@class='SplitPane  vertical ']/div[@class='Pane vertical Pane2  ']/div/div[@class='ui card panel collapse-panel']/div[@class='content']/div[@class='ui toggle checkbox']")).click();


        // закрываем окно без сохранения
        //    driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='ui pointing secondary menu']/a[@class='active item']/span[@class='close-tab']")).click();
        //   driver.findElement(By.xpath("//body[@class='dimmable dimmed blurring']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui tiny modal transition visible active']/div[@class='actions']/button[@class='ui primary button']")).click();

        //сохраняем
      //  driver.findElement(By.xpath("// div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons dq-policy']/div[@class='top-buttons']/button[@class='ui icon button']/i[@class='large icon icon-floppy-disk']")).click();

        //а теперь проверим что значения реально сохранились// подумать про закрыыть вкладку, открыть  потом проверить или полсе эскпорта
        Assert.assertEquals("policy" + "test_" + str,  DQname.getAttribute("value"));
        Assert.assertEquals("policy" + "test_" + str, driver.findElement(By.name("name")).getAttribute("value"));
        Assert.assertEquals("listallinstrumentswherecountry of inc=''orcountry of inc=''",driver.findElement(By.xpath("//div[@class='hwt-container']/textarea[@class='antlr-input hwt-input hwt-content']")).getAttribute("value"));

        //сохраняем
        driver.findElement(By.xpath("// div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons dq-policy']/div[@class='top-buttons']/button[@class='ui icon button']/i[@class='large icon icon-floppy-disk']")).click();


    }

    @Test

    public void DeletenewDQTest() {

        driver.findElement(By.xpath("//li[@class='bp3-tree-node bp3-tree-node-expanded'][3]//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-open']")).click();
        driver.findElement(By.xpath("//li[@class='bp3-tree-node']//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-closed']")).click();
        //driver.findElement(By.xpath("//*[text()='Test_policy_23112018']")).click();
        //  driver.findElement(By.xpath("//*[text()='policy + test + str']")).click();

      //  driver.findElement(By.xpath("//*[text()='policytest_2018-12-10T12:30:45Z']")).click();
        driver.findElement(By.xpath("//*[text()='policytest_" + str + "']")).click();



        ///на перспектву вынести удаление в отдельный класс
        driver.findElement(By.xpath("//div[@class='hider']")).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("//div[@class='hider']")));
        WebElement deleteButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='hider']/div[@class='to-hide']/div[@class='sidebar-bottom']/button[4]")));
        deleteButton.click();
        driver.findElement(By.xpath("//div[@class='ui tiny modal transition visible active custom-confirm']/div[@class='actions']/button[@class='ui primary button']")).click();


    }

    @Test
    @Ignore
    public void realDQTest() {


        driver.findElement(By.xpath("//li[@class='bp3-tree-node bp3-tree-node-expanded'][3]//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-open']")).click();
        driver.findElement(By.xpath("//li[@class='bp3-tree-node']//span[@class='bp3-icon bp3-icon-chevron-right bp3-tree-node-caret bp3-tree-node-caret-closed']")).click();
        driver.findElement(By.xpath("//*[text()='Test_policy_23112018']")).click();
        //   /html/body/div[@id='root']/div[@id='layout']/main/div[@class='data-model-container sidebar-show']/div[@class='content items-tab']/div[@class='active tab ui']/div[@class='container-with-side-buttons dq-policy']/div[@class='main']/div[@class='ui segment']/div[@class='two-sides']/div[@class='ui card panel collapse-panel'][2]/div[@class='content']/form[@class='ui form']/div[@class='equal width fields'][1]/div[@class='field'][1]/div[@class='ui input']/input

    }





}

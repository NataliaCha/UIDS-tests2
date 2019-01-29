package UiTest; /**
 * Created by natalia.chaplygina on 20.12.2018.
 */

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JobsTest {

    //http://prototype.datasynthes.com/synthes-frontend/operational-metadata


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




        driver.get("http://prototype.datasynthes.com/synthes-frontend/operational-metadata");
        WebElement loginField = driver.findElement(By.name("userName"));
        loginField.sendKeys("admin");
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("UsaeZ9ph");
        WebElement loginButton = driver.findElement(By.xpath("//button[text()='LOG IN']"));
        loginButton.click();
    }


    @Test
    @DisplayName("Upload small file")
    @Description("Upload small file with checking the presence of the file with the results and links to the logs ")
    @Severity (SeverityLevel.CRITICAL)
//update package
    public void A() {


        //Для сохрананения файла с результатами себе
        String downloadFilepath = System.getProperty("user.dir");
        System.out.println(downloadFilepath);
        downloadFilepath = downloadFilepath + "\\mydownloads";


        //Начинается работа с пакетами **можно исключить

        //    driver.findElement(By.xpath("//div[@class='ui card panel']/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][1]/div[@class='rt-tr -odd']/div[@class='rt-td'][1]/a")).click();


        if (driver.findElement(By.xpath("//div[@class='ui card panel']/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][1]/div[@class='rt-tr -odd']/div[@class='rt-td'][1]/a")) != null) {
            driver.findElement(By.xpath("//div[@class='ui card panel']/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][1]/div[@class='rt-tr -odd']/div[@class='rt-td'][1]/a")).click();
        } else {
            System.out.print("Нет пакета ,надо создавать");
        }

        WebElement policy = driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui small modal transition visible active']/div[@class='content']/form[@class='ui form operational-status-form']/div[@class='equal width fields'][6]/div[@class='field']"));
        String policyname;
        policyname= policy.getText();
        System.out.print (policyname);

        if (policyname.length()>0){
            System.out.print("в пакете есть поилитики, оставим так ");
        }else {
            driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui small modal transition visible active']/div[@class='content']/form[@class='ui form operational-status-form']/div[@class='equal width fields'][6]/div[@class='field']/div[@class='ui fluid multiple selection dropdown']/i[@class='dropdown icon']")).click();
            // /html/body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui small modal transition visible active']/div[@class='content']/form[@class='ui form operational-status-form']/div[@class='equal width fields'][6]/div[@class='field']/div[@class='ui fluid multiple selection dropdown']/i[@class='dropdown icon']


            ///Проблема если уже выбраын политики
            driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui small modal transition visible active']/div[@class='content']/form[@class='ui form operational-status-form']/div[@class='equal width fields'][6]/div[@class='field']/div[@class='ui active visible fluid multiple selection dropdown']/div[@class='visible menu transition']/div[@class='selected item']/span[@class='text']")).click();

        }

        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui small modal transition visible active']/div[@class='actions']/button[@class='ui primary button']")).click();
        WebElement pack = driver.findElement(By.xpath("//div[@class='ui card panel']/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][1]/div[@class='rt-tr -odd']/div[@class='rt-td'][1]/a"));
        String packaname = pack.getText();
        System.out.println(packaname);

        driver.findElement(By.xpath("//*[contains(text(),'OPERATIONS')]")).click();
        // ис этим вопрос

//        driver.findElement(By.xpath("//*[contains(text(),packaname)]")).click();

        //Надо поудмать как вычислять кнопку
        driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[2]/a/i[@class='plus circle large icon']")).click();


        WebElement uploadElement1 = driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui tiny modal transition visible active']/div[@class='content']/form[@class='ui form']/div[@class='equal width fields']/div[@class='field']/div[@class='ui input']/input"));

        //добавить загрузку из папки
        String downloadFilepath1 = System.getProperty("user.dir");
        uploadElement1.sendKeys(downloadFilepath1 + "\\tmp\\VendorSource_EQ_source.xlsx");
        // driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='content']/div[@class='ui action input']/button[@class='ui icon button']")).click();

        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui tiny modal transition visible active']/div[@class='actions']/button[@class='ui primary button']")).click();


        System.out.println("нажали сохранить");
        String alertText;
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification-popup")));
        WebElement toast1 = driver.findElement(By.className("notification-popup"));
        alertText = toast1.getText();
        System.out.println(alertText);
        Assert.assertEquals("Data is saved!", alertText);


        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        List<WebElement> webElements = driver.findElements(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group']"));
        int i = webElements.size();
        System.out.println(i);
        WebElement status = null;
        String first = "";
        if ((i % 2) == 0) {
            //имя джоба
            System.out.println(driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']" +
                    "/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']" +
                    "/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']" +
                    "/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -even']/div[@class='rt-td'][1]")).getText());
            //статус
            status = driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']/div[@class='content']" +
                    "/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']" +
                    "/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']" +
                    "/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -even']/div[@class='rt-td'][5]"));
            first = status.getText();
            System.out.println("статус джобы при запуске " + first);
            Assert.assertEquals("STARTING", first);

            try {
                TimeUnit.MINUTES.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String second = status.getText();
            System.out.println("статус джобы при окончании " + second);
            Assert.assertEquals("SUCCESS", second);

            WebElement result = driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -even']/div[@class='rt-td'][8]/a[@class='link']"));
            System.out.println(result.getText());
            System.out.println(result.getAttribute("href"));

            result.click();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String fileName = findFile(downloadFilepath);
  //           System.out.println(fileName);


            WebElement log = driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -even']/div[@class='rt-td'][9]/a"));

            System.out.println(log.getAttribute("href"));

        } else {
            //имя джоба
            System.out.println(driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']" +
                    "/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']" +
                    "/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']" +
                    "/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -odd']/div[@class='rt-td'][1]")).getText());
            //статус
            status = driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']/div[@class='content']" +
                    "/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']" +
                    "/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']" +
                    "/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -odd']/div[@class='rt-td'][5]"));
            first = status.getText();
            System.out.println("статус джобы при запуске маленького файла " + first);
            Assert.assertEquals("STARTING", first);

            try {
                TimeUnit.MINUTES.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String second = status.getText();
            System.out.println("статус джобы при окончании маленького файла" + second);
            Assert.assertEquals("SUCCESS", second);


            WebElement result1 = driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -odd']/div[@class='rt-td'][8]/a[@class='link']"));
            System.out.println(result1.getAttribute("href"));

            result1.click();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String fileName = findFile(downloadFilepath);
            System.out.println(fileName);


            WebElement log1 = driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -odd']/div[@class='rt-td'][9]/a"));

            System.out.println(log1.getAttribute("href"));

        }
    }


    @Test
    @Severity (SeverityLevel.CRITICAL)
    @DisplayName("Upload large file")
    @Description("Upload large file with checking the presence of the file with the results and links to the logs ")
//update package
    public void B() {


        //Для сохрананения файла с результатами себе
        String downloadFilepath = System.getProperty("user.dir");
        System.out.println(downloadFilepath);
        downloadFilepath = downloadFilepath + "\\mydownloads";


        //Начинается работа с пакетами **можно исключить

        //    driver.findElement(By.xpath("//div[@class='ui card panel']/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table']/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][1]/div[@class='rt-tr -odd']/div[@class='rt-td'][1]/a")).click();



        //Надо поудмать как вычислять кнопку
        driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[2]/a/i[@class='plus circle large icon']")).click();


        WebElement uploadElement1 = driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui tiny modal transition visible active']/div[@class='content']/form[@class='ui form']/div[@class='equal width fields']/div[@class='field']/div[@class='ui input']/input"));

        //добавить загрузку из папки
        String downloadFilepath1 = System.getProperty("user.dir");
        uploadElement1.sendKeys(downloadFilepath1 + "\\tmp\\VendorSource_EQ_1_volume.csv");
        // driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='content']/div[@class='ui action input']/button[@class='ui icon button']")).click();

        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui tiny modal transition visible active']/div[@class='actions']/button[@class='ui primary button']")).click();


        System.out.println("нажали сохранить");
        String alertText;
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification-popup")));
        WebElement toast1 = driver.findElement(By.className("notification-popup"));
        alertText = toast1.getText();
        System.out.println(alertText);
        Assert.assertEquals("Data is saved!", alertText);


        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        List<WebElement> webElements = driver.findElements(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group']"));
        int i = webElements.size();
        System.out.println(i);
        WebElement status = null;
        String first = "";
        if ((i % 2) == 0) {
            //имя джоба
            System.out.println(driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']" +
                    "/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']" +
                    "/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']" +
                    "/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -even']/div[@class='rt-td'][1]")).getText());
            //статус
            status = driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']/div[@class='content']" +
                    "/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']" +
                    "/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']" +
                    "/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -even']/div[@class='rt-td'][5]"));
            first = status.getText();
            System.out.println("статус джобы при запуске большого файла " + first);
            Assert.assertEquals("STARTING", first);

            try {
                TimeUnit.MINUTES.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String second = status.getText();
            System.out.println("статус джобы при окончании большого файла " + second);
            Assert.assertEquals("SUCCESS", second);

            WebElement result = driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -even']/div[@class='rt-td'][8]/a[@class='link']"));
            System.out.println(result.getText());
            System.out.println(result.getAttribute("href"));

            result.click();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String fileName = findFile(downloadFilepath);
            //           System.out.println(fileName);


            WebElement log = driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -even']/div[@class='rt-td'][9]/a"));

            System.out.println(log.getAttribute("href"));

        } else {
            //имя джоба
            System.out.println(driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']" +
                    "/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']" +
                    "/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']" +
                    "/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -odd']/div[@class='rt-td'][1]")).getText());
            //статус
            status = driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']/div[@class='content']" +
                    "/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']" +
                    "/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']" +
                    "/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -odd']/div[@class='rt-td'][5]"));
            first = status.getText();
            System.out.println("статус джобы при запуске " + first);
            Assert.assertEquals("STARTING", first);

            try {
                TimeUnit.MINUTES.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String second = status.getText();
            System.out.println("статус джобы при окончании " + second);
            Assert.assertEquals("SUCCESS", second);


            WebElement result1 = driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -odd']/div[@class='rt-td'][8]/a[@class='link']"));
            System.out.println(result1.getAttribute("href"));

            result1.click();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String fileName = findFile(downloadFilepath);
            System.out.println(fileName);


            WebElement log1 = driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -odd']/div[@class='rt-td'][9]/a"));

            System.out.println(log1.getAttribute("href"));

        }
    }

            private String findFile(String downloadFilepath) {
                File dir = new File(downloadFilepath);
                File[] matches = dir.listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.startsWith("result_grades") /*&& name.endsWith(".csv")*/;
                    }
                });
                String fileName = "";
                if (matches != null&& matches.length > 0) {
                    fileName = matches[0].toString();
                }
                return fileName;    }







    /*
        System.out.println("статус джобы при запуске " + first);

        try {
            TimeUnit.MINUTES.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String second = status.getText();
        System.out.println("статус джобы при окончании " + second);
        Assert.assertEquals("SUCCESS", second);

*/

      /*
        WebElement result = driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -even']/div[@class='rt-td'][8]/a[@class='link']"));
        System.out.println(result.getText());

        result.click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //  String fileName = findFile(downloadFilepath);
        //  System.out.println(fileName);


        WebElement log = driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -even']/div[@class='rt-td'][9]/a/i[@class='blue info circle large icon']"));

        System.out.println(log.getText());


*/





 /*
//повтор для большого файла

        driver.findElement(By.xpath("//div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[2]/a/i[@class='plus circle large icon']")).click();


        WebElement uploadElement2 = driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui tiny modal transition visible active']/div[@class='content']/form[@class='ui form']/div[@class='equal width fields']/div[@class='field']/div[@class='ui input']/input"));

        //добавить загрузку из папки
        uploadElement2.sendKeys("C:\\Users\\natalia.chaplygina\\Documents\\Рабочее\\2\\VendorSource_EQ_1_volume.xlsx");

        // driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui mini modal transition visible active']/div[@class='content']/div[@class='ui action input']/button[@class='ui icon button']")).click();

        driver.findElement(By.xpath("//body[@class='dimmable dimmed']/div[@class='ui page modals dimmer transition visible active']/div[@class='ui tiny modal transition visible active']/div[@class='actions']/button[@class='ui primary button']")).click();


        //имя джоба
        System.out.println(driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -even']/div[@class='rt-td'][1]")).getText());

        //статус

        WebElement status1 = driver.findElement(By.xpath("//div[@id='root']/div[@id='layout']/main/div/div[@class='ui card panel']/div[@class='content']/div[@class='main entities-tab']/div[@class='ui segment active tab']/div[@class='accordion ui fluid ds-accordion']/div[@class='content active']/div[@class='ReactTable -striped -highlight ds-table operational-status-table'][1]/div[@class='rt-table']/div[@class='rt-tbody']/div[@class='rt-tr-group'][last()]/div[@class='rt-tr -even']/div[@class='rt-td'][5]"));
        String first1 = status1.getText();

        System.out.println("статус джобы при запуске "+ first1);

        try {
            TimeUnit.MINUTES.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String second1 = status1.getText();
        System.out.println("статус джобы при окончании "+ second1);
        Assert.assertEquals("SUCCESS",second1);


*/





}
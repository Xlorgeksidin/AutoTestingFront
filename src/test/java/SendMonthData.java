import helpers.Table;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class SendMonthData {
    private WebDriver driver;
    private JavascriptExecutor js;


    @BeforeEach
    public void setupPage() throws InterruptedException {
//        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Java\\Selenium\\WebDrivers\\geckodriver.exe");
//        driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\Selenium\\WebDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://127.0.0.1:5500/index.html");
        Thread.sleep(1000);
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void sendMonthData(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSend();



//      Если в хроме просто кликнуть по полю даты, то датапикер не вызывается. Поэтому было предпринято следующее обходное решение
        driver.findElement(By.xpath("//*[@id=\"date\"]")).sendKeys(Keys.TAB);
//      Без ожидания выбор не происходит
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id=\"date\"]")).sendKeys(Keys.SPACE);
        driver.findElement(By.xpath("//*[@id=\"date\"]")).sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//*[@id=\"coldData\"]")).sendKeys("10");
        driver.findElement(By.xpath("//*[@id=\"hotData\"]")).sendKeys("20");
        driver.findElement(By.xpath("//*[@id=\"gasData\"]")).sendKeys("30");
        driver.findElement(By.xpath("//*[@id=\"elecData\"]")).sendKeys("40");
        driver.findElement(By.xpath("//*[@id=\"button\"]")).click();

    }

    @Test
    public void checkDataInTable(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSend();

        driver.findElement(By.xpath("//*[@id=\"date\"]")).sendKeys(Keys.TAB);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id=\"date\"]")).sendKeys(Keys.SPACE);
        driver.findElement(By.xpath("//*[@id=\"date\"]")).sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//*[@id=\"coldData\"]")).sendKeys("10");
        driver.findElement(By.xpath("//*[@id=\"hotData\"]")).sendKeys("20");
        driver.findElement(By.xpath("//*[@id=\"gasData\"]")).sendKeys("30");
        driver.findElement(By.xpath("//*[@id=\"elecData\"]")).sendKeys("40");
        driver.findElement(By.xpath("//*[@id=\"button\"]")).click();

        Table table = new Table(driver.findElement(By.xpath("//*[@id=\"table\"]")), driver);

        for (List<WebElement> list : table.getRowsWithColumns()) {
            for(WebElement element : list){
                Assertions.assertEquals(false, element.getText().isEmpty());
            }
        }


    }


    @AfterEach
    public void closePage() {
        driver.quit();
    }
}

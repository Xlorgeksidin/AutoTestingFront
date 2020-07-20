import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageNavigationTest {
    private WebDriver driver;

    @BeforeEach
    public void setupPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\eduWebsiteTesting\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://127.0.0.1:5500/index.html");
        Thread.sleep(1000);
    }

    @Test
    public void FromMainToSendAndBack () throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSend();
        Assertions.assertEquals(driver.findElement(By.xpath("/html/body/h1")).getText(), "Передача показаний");
        driver.findElement(By.xpath("//*[@id=\"back_button\"]")).click();
        Assertions.assertEquals(driver.findElement(By.xpath("/html/body/h1")).getText(), "Neo ЖКХ");
    }

    @Test
    public void FromMainToHistoryAndBack () throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickHistory();
        Assertions.assertEquals(driver.findElement(By.xpath("/html/body/h1")).getText(), "История показаний");
        driver.findElement(By.xpath("//*[@id=\"back_button\"]")).click();
        Assertions.assertEquals(driver.findElement(By.xpath("/html/body/h1")).getText(), "Neo ЖКХ");
    }

    @Test
    public void FromMainToPriceAndBack () {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPrice();
        Assertions.assertEquals(driver.findElement(By.xpath("/html/body/h1")).getText(), "Справочник стоимости услуг");
        driver.findElement(By.xpath("//*[@id=\"back_button\"]")).click();
        Assertions.assertEquals(driver.findElement(By.xpath("/html/body/h1")).getText(), "Neo ЖКХ");
    }

    @AfterEach
    public void closePage() {
        driver.quit();
    }
}
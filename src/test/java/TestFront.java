import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestFront {
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Java\\Selenium\\WebDrivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://127.0.0.1:5500/");
        WebElement header = driver.findElement(By.xpath("/html/body/h1"));
        System.out.println(header.getText());
        driver.quit();
    }
}

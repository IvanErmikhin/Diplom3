package configs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class DriverConfig {


    protected WebDriver driver;

    private static WebDriver initYandexDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
        WebDriver yandexDriver = new ChromeDriver();
        yandexDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        yandexDriver.manage().window().maximize();
        return yandexDriver;
    }

    private static WebDriver initChromeDriver() {
        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        chromeDriver.manage().window().maximize();
        return chromeDriver;
    }

    public WebDriver initDriver(String browserType) {
        switch (browserType) {
            case "yandex":
                this.driver = initYandexDriver();
                return driver;
            case "chrome":
            default:
                this.driver = initChromeDriver();
                return driver;
        }
    }
}



package Configs;

import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

import static Configs.AppConfig.APP_URL;

public class DriverConfig {
    static WebDriver webDriver;
    public static WebDriver GET_DRIVER_CONFIG() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless","--remote-allow-origins=*");
        //options.addArguments("--remote-allow-origins=*");
        //options.setBinary(YANDEX_BIN_PATH);
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get(APP_URL);
        RestAssured.baseURI = APP_URL;

        return webDriver;
    }
}



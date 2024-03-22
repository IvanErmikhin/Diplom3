import configs.DriverConfig;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static configs.AppConfig.*;
import static pages.LocatorsMainPage.*;
import static org.junit.Assert.assertEquals;

public class ConstructorTest extends DriverConfig {
    @Before
    public void setUp(){
        driver = initDriver(System.getProperty("webdriver.driver"));
        driver.get(APP_URL);
    }

    @Test
    @DisplayName("раздел Булки")
    @Description("Переход к разделу Булки")
    public void goingToBunSectionTest() {
        WebElement filling = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(FILLING));
        filling.click();
        WebElement bun =  new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(BUN));
        bun.click();
        String actualResult = driver.findElement(ACTIVE_TAB).getText();
        assertEquals(EXPECTED_BUN_SECTION_TEXT,actualResult);
    }

    @Test
    @DisplayName("Раздел Соусы")
    @Description("Переход к разделу Соусы")
    public void goingToSaucesSectionTest() {
        driver.findElement(SAUCES).click();
        String actualResult = driver.findElement(ACTIVE_TAB).getText();
        assertEquals(EXPECTED_SAUCES_SECTION_TEXT,actualResult);
    }

    @Test
    @DisplayName("Раздел Начинки")
    @Description("Переход к разделу Начинки")
    public void goingToFillingSectionTest() {
        driver.findElement(FILLING).click();
        String actualResult = driver.findElement(ACTIVE_TAB).getText();
        assertEquals(EXPECTED_FILLING_SECTION_TEXT,actualResult);
    }

    @After
    public void cleanUp() {
        driver.quit();
    }

}

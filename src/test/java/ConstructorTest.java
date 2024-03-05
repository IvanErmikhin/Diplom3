import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static Configs.AppConfig.*;
import static Configs.DriverConfig.GET_DRIVER_CONFIG;
import static PageObject.LocatorsMainPage.*;
import static org.junit.Assert.assertEquals;

public class ConstructorTest {
    private WebDriver webDriver;
    @Before
    public void setUp(){
        webDriver = GET_DRIVER_CONFIG();
    }

    @Test
    @DisplayName("раздел Булки")
    @Description("Переход к разделу Булки")
    public void goingToBunSectionTest() {
        WebElement filling = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(FILLING));
        filling.click();
        WebElement bun =  new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(BUN));
        bun.click();
        String actualResult = webDriver.findElement(ACTIVE_TAB).getText();
        assertEquals(EXPECTED_BUN_SECTION_TEXT,actualResult);
    }

    @Test
    @DisplayName("Раздел Соусы")
    @Description("Переход к разделу Соусы")
    public void goingToSaucesSectionTest() {
        webDriver.findElement(SAUCES).click();
        String actualResult = webDriver.findElement(ACTIVE_TAB).getText();
        assertEquals(EXPECTED_SAUCES_SECTION_TEXT,actualResult);
    }

    @Test
    @DisplayName("Раздел Начинки")
    @Description("Переход к разделу Начинки")
    public void goingToFillingSectionTest() {
        webDriver.findElement(FILLING).click();
        String actualResult = webDriver.findElement(ACTIVE_TAB).getText();
        assertEquals(EXPECTED_FILLING_SECTION_TEXT,actualResult);
    }

    @After
    public void cleanUp() {
        webDriver.quit();
    }

}

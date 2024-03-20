import configs.DriverConfig;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static configs.AppConfig.*;
import static pages.LocatorsAuthPage.SIGN_IN_BUTTON;
import static pages.LocatorsMainPage.*;
import static pages.LocatorsPersonalCabinetPage.EXIT_BUTTON;
import static pages.LocatorsPersonalCabinetPage.SAVE_BUTTON;
import static org.junit.Assert.assertEquals;

public class PersonalCabinetTest extends DriverConfig {
    private static String userToken;

    @Before
    public void setUp(){
        driver = initDriver(System.getProperty("webdriver.driver"));
        driver.get(APP_URL);
        RestAssured.baseURI = APP_URL;
        UserController.newUser(new createUser(EMAIL, PASSWORD, NAME));
        userToken = UserController.getUserToken(new loginUser(EMAIL, PASSWORD));
        AuthPage authenticationPage = new AuthPage(driver);
        driver.findElement(ACCOUNT_SIGNIN_BUTTON).click();
        authenticationPage.loginFromMainPage(EMAIL, PASSWORD);
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Успешный тест входа в профиль учетной записи")
    public void successTransferToPersonalCabinetTest() {
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        String actualResult = driver.findElement(SAVE_BUTTON).getText();
        assertEquals(EXPECTED_SAVE_BUTTON_TEXT,actualResult);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на Конструктор")
    @Description("Успешный переход на конструктор бургеров из профильного теста")
    public void successTransferToConstructorTest() {
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        driver.findElement(BURGER_CONSTRUCTOR_BUTTON).click();
        String actualResult = driver.findElement(MAKE_BURGER_TEXT).getText();
        assertEquals(EXPECTED_MAKE_BURGER_TEXT,actualResult);
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Успешный тест выхода из системы")
    public void successLogoutTest() throws InterruptedException {
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        driver.findElement(EXIT_BUTTON).click();
        TimeUnit.SECONDS.sleep(5);
        String actualResult = driver.findElement(SIGN_IN_BUTTON).getText();
        assertEquals(EXPECTED_SUCCESSFUL_TEXT,actualResult);
    }

    @After
    public void cleanUp() {
        driver.quit();
        UserController.deleteUser(userToken);
    }
}

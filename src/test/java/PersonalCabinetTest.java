import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static Configs.AppConfig.*;
import static Configs.DriverConfig.GET_DRIVER_CONFIG;
import static PageObject.LocatorsAuthPage.SIGN_IN_BUTTON;
import static PageObject.LocatorsMainPage.*;
import static PageObject.LocatorsPersonalCabinetPage.EXIT_BUTTON;
import static PageObject.LocatorsPersonalCabinetPage.SAVE_BUTTON;
import static org.junit.Assert.assertEquals;

public class PersonalCabinetTest {
    private WebDriver webDriver;
    private static String userToken;

    @Before
    public void setUp(){
        webDriver = GET_DRIVER_CONFIG();
        UserController.newUser(new CreateUser(EMAIL, PASSWORD, NAME));
        userToken = UserController.getUserToken(new LoginUser(EMAIL, PASSWORD));
        AuthPage authenticationPage = new AuthPage(webDriver);
        webDriver.findElement(ACCOUNT_SIGNIN_BUTTON).click();
        authenticationPage.loginFromMainPage(EMAIL, PASSWORD);
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Успешный тест входа в профиль учетной записи")
    public void successTransferToPersonalCabinetTest() {
        webDriver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        String actualResult = webDriver.findElement(SAVE_BUTTON).getText();
        assertEquals(EXPECTED_SAVE_BUTTON_TEXT,actualResult);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на Конструктор")
    @Description("Успешный переход на конструктор бургеров из профильного теста")
    public void successTransferToConstructorTest() {
        webDriver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        webDriver.findElement(BURGER_CONSTRUCTOR_BUTTON).click();
        String actualResult = webDriver.findElement(MAKE_BURGER_TEXT).getText();
        assertEquals(EXPECTED_MAKE_BURGER_TEXT,actualResult);
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Успешный тест выхода из системы")
    public void successLogoutTest() {
        webDriver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        webDriver.findElement(EXIT_BUTTON).click();
        String actualResult = webDriver.findElement(SIGN_IN_BUTTON).getText();
        assertEquals(EXPECTED_SUCCESSFUL_TEXT,actualResult);
    }

    @After
    public void cleanUp() {
        webDriver.quit();
        UserController.deleteUser(userToken);
    }
}

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static Configs.AppConfig.*;
import static Configs.DriverConfig.GET_DRIVER_CONFIG;
import static PageObject.LocatorsAuthPage.RESET_PASS_BUTTON;
import static PageObject.LocatorsMainPage.*;
import static PageObject.LocatorsRegistrationPage.REGISTRATION_BUTTON;
import static PageObject.LocatorsRegistrationPage.SIGN_IN_BUTTON;
import static org.junit.Assert.assertEquals;


public class LoginTest {
    private WebDriver webDriver;
    private static AuthPage authPage;
    private static String userToken;
    @Before
    public void setUp(){
        webDriver = GET_DRIVER_CONFIG();
        UserController.newUser(new CreateUser(EMAIL, PASSWORD, NAME));
        userToken = UserController.getUserToken(new LoginUser(EMAIL, PASSWORD));
        authPage = new AuthPage(webDriver);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка авторизации со страницы личного кабинета.")
    public void successLoginFromSignInPersonalCabinetTest() {
        webDriver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        authPage.loginFromMainPage(EMAIL,PASSWORD);
        String actualResult = webDriver.findElement(MAKE_ORDER_BUTTON).getText();
        assertEquals(EXPECTED_SIGN_IN_TEXT,actualResult);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Проверка авторизации с главной страницы страницы.")
    public void successLoginFromPersonalCabinetTest() {
        webDriver.findElement(ACCOUNT_SIGNIN_BUTTON).click();
        authPage.loginFromMainPage(EMAIL,PASSWORD);
        String actualResult = webDriver.findElement(MAKE_ORDER_BUTTON).getText();
        assertEquals(EXPECTED_SIGN_IN_TEXT,actualResult);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка вход со страницы регистрации.")
    public void successLoginFromRegisterFormTest() {
        webDriver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        webDriver.findElement(REGISTRATION_BUTTON).click();
        webDriver.findElement(SIGN_IN_BUTTON).click();
        authPage.loginFromMainPage(EMAIL, PASSWORD);
        String actualResult = webDriver.findElement(MAKE_ORDER_BUTTON).getText();
        assertEquals(EXPECTED_SIGN_IN_TEXT,actualResult);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка вход со страницы сброса пароля.")
    public void successLoginFromForgotPasswordFormTest() {
        webDriver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        webDriver.findElement(RESET_PASS_BUTTON).click();
        webDriver.findElement(SIGN_IN_BUTTON).click();
        authPage.loginFromMainPage(EMAIL, PASSWORD);
        String actualResult = webDriver.findElement(MAKE_ORDER_BUTTON).getText();
        assertEquals(EXPECTED_SIGN_IN_TEXT,actualResult);
    }

    @After
    public void cleanUp() {
        webDriver.quit();
        UserController.deleteUser(userToken);
    }
}

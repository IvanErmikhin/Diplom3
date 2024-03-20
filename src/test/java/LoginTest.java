import configs.DriverConfig;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static configs.AppConfig.*;
import static pages.LocatorsAuthPage.RESET_PASS_BUTTON;
import static pages.LocatorsMainPage.*;
import static pages.LocatorsRegistrationPage.REGISTRATION_BUTTON;
import static pages.LocatorsRegistrationPage.SIGN_IN_BUTTON;
import static org.junit.Assert.assertEquals;


public class LoginTest extends DriverConfig {
    private static AuthPage authPage;
    private static String userToken;
    @Before
    public void setUp(){
        driver = initDriver(System.getProperty("webdriver.driver"));
        driver.get(APP_URL);
        RestAssured.baseURI = APP_URL;
        UserController.newUser(new createUser(EMAIL, PASSWORD, NAME));
        userToken = UserController.getUserToken(new loginUser(EMAIL, PASSWORD));
        authPage = new AuthPage(driver);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка авторизации со страницы личного кабинета.")
    public void successLoginFromSignInPersonalCabinetTest() {
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        authPage.loginFromMainPage(EMAIL,PASSWORD);
        String actualResult = driver.findElement(MAKE_ORDER_BUTTON).getText();
        assertEquals(EXPECTED_SIGN_IN_TEXT,actualResult);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Проверка авторизации с главной страницы страницы.")
    public void successLoginFromPersonalCabinetTest() {
        driver.findElement(ACCOUNT_SIGNIN_BUTTON).click();
        authPage.loginFromMainPage(EMAIL,PASSWORD);
        String actualResult = driver.findElement(MAKE_ORDER_BUTTON).getText();
        assertEquals(EXPECTED_SIGN_IN_TEXT,actualResult);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка вход со страницы регистрации.")
    public void successLoginFromRegisterFormTest() {
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        driver.findElement(REGISTRATION_BUTTON).click();
        driver.findElement(SIGN_IN_BUTTON).click();
        authPage.loginFromMainPage(EMAIL, PASSWORD);
        String actualResult = driver.findElement(MAKE_ORDER_BUTTON).getText();
        assertEquals(EXPECTED_SIGN_IN_TEXT,actualResult);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка вход со страницы сброса пароля.")
    public void successLoginFromForgotPasswordFormTest() {
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        driver.findElement(RESET_PASS_BUTTON).click();
        driver.findElement(SIGN_IN_BUTTON).click();
        authPage.loginFromMainPage(EMAIL, PASSWORD);
        String actualResult = driver.findElement(MAKE_ORDER_BUTTON).getText();
        assertEquals(EXPECTED_SIGN_IN_TEXT,actualResult);
    }

    @After
    public void cleanUp() {
        driver.quit();
        UserController.deleteUser(userToken);
    }
}

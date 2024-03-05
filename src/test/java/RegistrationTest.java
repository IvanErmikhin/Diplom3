import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static Configs.AppConfig.*;
import static Configs.DriverConfig.GET_DRIVER_CONFIG;
import static PageObject.LocatorsAuthPage.SIGN_IN_BUTTON;
import static PageObject.LocatorsRegistrationPage.INVALID_PASS;
import static org.junit.Assert.assertEquals;

public class RegistrationTest {
    private WebDriver webDriver;
    private static String userToken;

    @Before
    public void setUp(){
        webDriver = GET_DRIVER_CONFIG();
        RestAssured.baseURI = APP_URL;
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Успешная регистрация нового пользователя")
    public void successRegistrationTest() {
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.registration(NAME, EMAIL, PASSWORD);
        LoginUser loginUser = new LoginUser(EMAIL, PASSWORD);
        userToken = UserController.getUserToken(loginUser);
        String actualResult = webDriver.findElement(SIGN_IN_BUTTON).getText();
        assertEquals(EXPECTED_SUCCESSFUL_TEXT, actualResult);
    }

    @Test
    @DisplayName("Некорректный пароль")
    @Description("Проверка регистрации с неверным паролем")
    public void failRegistrationTest() {
        RegisterPage registrationPage = new RegisterPage(webDriver);
        registrationPage.registration(NAME,EMAIL,"five");
        String actualResult = webDriver.findElement(INVALID_PASS).getText();
        assertEquals(EXPECTED_INVALID_PASS_TEXT,actualResult);
    }

    @After
    public void cleanUp() {
        webDriver.quit();
        if (userToken != null){ UserController.deleteUser(userToken); }
    }
}

import configs.DriverConfig;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static configs.AppConfig.*;
import static pages.LocatorsAuthPage.SIGN_IN_BUTTON;
import static pages.LocatorsRegistrationPage.INVALID_PASS;
import static org.junit.Assert.assertEquals;

public class RegistrationTest extends DriverConfig {
    private static String userToken;

    @Before
    public void setUp(){
        driver = initDriver(System.getProperty("webdriver.driver"));
        driver.get(APP_URL);
        RestAssured.baseURI = APP_URL;
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Успешная регистрация нового пользователя")
    public void successRegistrationTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registration(NAME, EMAIL, PASSWORD);
        LoginUser loginUser = new LoginUser(EMAIL, PASSWORD);
        userToken = UserController.getUserToken(loginUser);
        String actualResult = driver.findElement(SIGN_IN_BUTTON).getText();
        assertEquals(EXPECTED_SUCCESSFUL_TEXT, actualResult);
    }

    @Test
    @DisplayName("Некорректный пароль")
    @Description("Проверка регистрации с неверным паролем")
    public void failRegistrationTest() {
        RegisterPage registrationPage = new RegisterPage(driver);
        registrationPage.registration(NAME,EMAIL,"five");
        String actualResult = driver.findElement(INVALID_PASS).getText();
        assertEquals(EXPECTED_INVALID_PASS_TEXT,actualResult);
    }

    @After
    public void cleanUp() {
        driver.quit();
        if (userToken != null){ UserController.deleteUser(userToken); }
    }
}

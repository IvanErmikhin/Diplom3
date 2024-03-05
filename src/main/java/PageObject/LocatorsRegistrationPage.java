package PageObject;

import org.openqa.selenium.By;

public class LocatorsRegistrationPage {

    public static final By SIGN_IN_BUTTON = By.xpath(".//a[text()='Войти']");
    public static final By REGISTRATION_BUTTON = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[1]/a");
    public static final By FINAL_REG_BUTTON = By.xpath("//*[@id=\"root\"]/div/main/div/form/button");
    public static final By NAME_INPUT = By.xpath(".//form/fieldset[1]/div/div/input");
    public static final By EMAIL_INPUT = By.xpath(".//form/fieldset[2]/div/div/input");
    public static final By PASSWORD_INPUT = By.xpath(".//form/fieldset[3]/div/div/input");
    public static final By INVALID_PASS = By.xpath(".//p[text()='Некорректный пароль']");
}

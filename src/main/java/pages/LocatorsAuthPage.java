package pages;

import org.openqa.selenium.By;

public class LocatorsAuthPage {
    public static final By EMAIL_INPUT = By.xpath("//div[@class=\"Auth_login__3hAey\"]//input[@name = \"name\"]");
    public static final By PASS_INPUT = By.xpath("//div[@class=\"Auth_login__3hAey\"]//input[@name = \"Пароль\"]");
    public static final By SIGN_IN_BUTTON = By.xpath("//*[@id=\"root\"]//button");
    public static final By RESET_PASS_BUTTON = By.xpath("//*[@id=\"root\"]//p[2]/a");
}

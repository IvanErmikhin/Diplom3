package PageObject;

import org.openqa.selenium.By;

public class LocatorsAuthPage {
    public static final By EMAIL_INPUT = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    public static final By PASS_INPUT = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    public static final By SIGN_IN_BUTTON = By.xpath("//*[@id=\"root\"]/div/main/div/form/button");
    public static final By RESET_PASS_BUTTON = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[2]/a");
}

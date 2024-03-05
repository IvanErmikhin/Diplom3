package PageObject;

import org.openqa.selenium.By;

public class LocatorsMainPage {
    public static final By PERSONAL_ACCOUNT_BUTTON = By.xpath("//*[@id=\"root\"]/div/header/nav/a");
    public static final By ACCOUNT_SIGNIN_BUTTON = By.xpath(".//button[text()='Войти в аккаунт']");
    public static final By MAKE_ORDER_BUTTON = By.xpath("/html/body/div/div/main/section[2]/div/button");
    public static final By BURGER_CONSTRUCTOR_BUTTON = By.xpath("//*[@id=\"root\"]/div/header/nav/ul/li[1]/a");
    public static final By MAKE_BURGER_TEXT = By.xpath("//*[@id=\"root\"]/div/main/section[1]/h1");
    public static final By BUN = By.xpath(".//span[text()='Булки']");
    public static final By SAUCES = By.xpath(".//span[text()='Соусы']");
    public static final By FILLING = By.xpath(".//span[text()='Начинки']");
    public static final By ACTIVE_TAB = By.className("tab_tab_type_current__2BEPc");
}

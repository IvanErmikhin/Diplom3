import org.openqa.selenium.WebDriver;

import static pages.LocatorsMainPage.PERSONAL_ACCOUNT_BUTTON;
import static pages.LocatorsRegistrationPage.*;

public class RegisterPage {
    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickOnPersonalAccountButton() {
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
    }
    public void clickOnRegisterButton() {
        driver.findElement(REGISTRATION_BUTTON).click();
    }
    public void clickOnFinalRegisterButton() {
        driver.findElement(FINAL_REG_BUTTON).click();
    }

    public void sendKeyName(String name) {
        driver.findElement(pages.LocatorsRegistrationPage.NAME_INPUT).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(pages.LocatorsRegistrationPage.EMAIL_INPUT).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(pages.LocatorsRegistrationPage.PASSWORD_INPUT).sendKeys(password);
    }

    public void registration(String name, String email, String password) {
        clickOnPersonalAccountButton();
        clickOnRegisterButton();
        sendKeyName(name);
        setEmail(email);
        setPassword(password);
        clickOnFinalRegisterButton();
    }
}

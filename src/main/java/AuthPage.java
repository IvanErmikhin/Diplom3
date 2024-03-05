import org.openqa.selenium.WebDriver;

import static PageObject.LocatorsAuthPage.*;


public class AuthPage {
    private final WebDriver webDriver;

    public AuthPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public void sendKeyEmail(String email) {
        webDriver.findElement(PageObject.LocatorsAuthPage.EMAIL_INPUT).sendKeys(email);
    }
    public void sendKeyPassword(String password) {
        webDriver.findElement(PageObject.LocatorsAuthPage.PASS_INPUT).sendKeys(password);
    }
    public void signIn() {
        webDriver.findElement(SIGN_IN_BUTTON).click();
    }
    public void loginFromMainPage(String email, String password) {
        sendKeyEmail(email);
        sendKeyPassword(password);
        signIn();
    }
}

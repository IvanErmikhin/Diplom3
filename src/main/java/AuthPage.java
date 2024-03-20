import org.openqa.selenium.WebDriver;

import static pages.LocatorsAuthPage.*;


public class AuthPage {
    private final WebDriver webDriver;

    public AuthPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public void sendKeyEmail(String email) {
        webDriver.findElement(pages.LocatorsAuthPage.EMAIL_INPUT).sendKeys(email);
    }
    public void sendKeyPassword(String password) {
        webDriver.findElement(pages.LocatorsAuthPage.PASS_INPUT).sendKeys(password);
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

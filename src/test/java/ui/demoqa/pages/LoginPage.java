package ui.demoqa.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    public final static String LOGIN_URL = "https://demoqa.com/login";

    private final SelenideElement inputUserName = $x("//input[@id='userName']");
    private final SelenideElement inputPassword = $x("//input[@id='password']");
    private final SelenideElement buttonLogin = $x("//button[@id='login']");

    public void openLoginPage() {
        open(LOGIN_URL);
    }

    public void fillFormLogin(String username, String password) {
        inputUserName.setValue(username);
        inputPassword.setValue(password);
    }

    public void submitButtonLogin() {
        buttonLogin.click();
    }
}
package ui.demoqa.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class RegisterPage {

    public final static String REG_URL = "https://demoqa.com/register";

    private final SelenideElement inputFirstName = $x("//input[@id='firstname']");
    private final SelenideElement inputLastName = $x("//input[@id='lastname']");
    private final SelenideElement inputUserName = $x("//input[@id='userName']");
    private final SelenideElement inputPassword = $x("//input[@id='password']");

    private final SelenideElement buttonRegister = $x("//button[@id='register']");

    public void openRegister(){
        open(REG_URL);
    }

    public void fillFormRegister(String firstName, String lastName, String userName, String password){
        inputFirstName.setValue(firstName);
        inputLastName.setValue(lastName);
        inputUserName.setValue(userName);
        inputPassword.setValue(password);
    }

    public void solveCaptchaManually(){
        Selenide.sleep(10000);
    }

    public void submitRegisterButton(){
        buttonRegister.click();
    }
}
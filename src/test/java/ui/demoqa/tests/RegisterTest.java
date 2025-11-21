package ui.demoqa.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.demoqa.pages.LoginPage;
import ui.demoqa.pages.ProfilePage;
import ui.demoqa.pages.RegisterPage;

import static ui.demoqa.RegUserData.*;

public class RegisterTest extends BaseTest {

    @Test
    @Tag("smoke")
    @Tag("regression")
    public void successRegTest(){

        String userName = "IvanIvanov1";

        RegisterPage registerPage = new RegisterPage();
        registerPage.openRegister();
        registerPage.fillFormRegister(
                FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD
        );
        registerPage.solveCaptchaManually();
        registerPage.submitRegisterButton();

        LoginPage loginPage = new LoginPage();
        loginPage.openLoginPage();
        loginPage.fillFormLogin(USER_NAME, PASSWORD);
        loginPage.submitButtonLogin();

        ProfilePage profilePage = new ProfilePage();
        Assertions.assertEquals(userName, profilePage.getActualUserName());
    }
}

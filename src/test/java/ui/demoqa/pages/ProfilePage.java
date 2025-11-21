package ui.demoqa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {

    private final SelenideElement actualUserName = $x("//label[@id='userName-value']");

    public String getActualUserName() {
       return actualUserName.getText();
    }
}

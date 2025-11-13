package ui.demoqa.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private final SelenideElement cardButton = $x("//div[@class='card-up']");

    public MainPage(String url) {
        Selenide.open(url);
    }

    public void clickButton(){
        cardButton.click();
    }
}
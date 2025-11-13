package ui.demoqa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class ElementsPage {

    private final SelenideElement textBoxButton = $x("//span[@class='text']");
    private final SelenideElement webTablesButton = $("#item-3");
    private final SelenideElement submitButton = $x("//button[@id='submit']");
    private final SelenideElement brokenLinksButton = $x("//li[@id ='item-6']");

    public void clickTextButton(){
        textBoxButton.click();
    }

    public void clickWebTablesButton() {
        webTablesButton.click();
    }

    public void clickBrokenLinksButton() {
        brokenLinksButton.click();
    }

}
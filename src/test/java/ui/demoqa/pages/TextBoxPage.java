package ui.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import ui.demoqa.textBoxTest.PersonalInfo;

import static com.codeborne.selenide.Selenide.$x;

public class TextBoxPage {

    private final SelenideElement submitButton = $x("//button[@id='submit']");

    private final SelenideElement inputFullName = $x("//input[@autocomplete='off']");
    private final SelenideElement inputEmail = $x("//input[@placeholder='name@example.com']");
    private final SelenideElement inputCurrentAddress = $x("//textarea[@placeholder='Current Address']");
    private final SelenideElement inputPermanentAddress = $x("//textarea[@id='permanentAddress']");

    private final SelenideElement actualFullName = $x("//p[@id='name']");
    private final SelenideElement actualEmail = $x("//p[@id='email']");
    private final SelenideElement actualCurrentAddress = $x("//p[@id='currentAddress']");
    private final SelenideElement actualPermanentAddress = $x("//p[@id='permanentAddress']");

    public void addNewData(String fullName, String email, String currentAddress, String permanentAddress) {
        fillFormTextBox(fullName, email, currentAddress, permanentAddress);
        submitButton.click();
    }

    public void fillFormTextBox(String fullName, String email, String currentAddress, String permanentAddress){
        inputFullName.setValue(fullName);
        inputEmail.setValue(email);
        inputCurrentAddress.setValue(currentAddress);
        inputPermanentAddress.setValue(permanentAddress);
    }

    public PersonalInfo getActualData(){
        return new PersonalInfo(
                actualFullName.getText(),
                actualEmail.getText(),
                actualCurrentAddress.getText(),
                actualPermanentAddress.getText()
        );
    }
}

package ui.demoqa.tests;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ui.demoqa.pages.ElementsPage;
import ui.demoqa.pages.MainPage;
import ui.demoqa.pages.TextBoxPage;
import ui.demoqa.textBoxTest.PersonalInfo;

import static ui.demoqa.TestData.*;

public class TextBoxTest extends BaseTest {

    private final static String BASE_URL = "https://demoqa.com/";

    @Test
    public void ShowCorrectData(){
        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.clickButton();

        ElementsPage elementsPage = new  ElementsPage();
        elementsPage.clickTextButton();

        TextBoxPage textBoxPage = new TextBoxPage();
        textBoxPage.addNewData(FULL_NAME, EMAIL, CURRENT_ADDRESS, PERMANENT_ADDRESS);

        PersonalInfo expectedAttributes = new PersonalInfo(
                "Name:Kirill Aminev",
                "Email:kirill@test.ru",
                "Current Address :Tokyo, japan street 5",
                "Permanent Address :Tokyo, japan street 10");

        PersonalInfo actualAttributes = textBoxPage.getActualData();

        Assertions.assertTrue(!EqualsBuilder.reflectionEquals(expectedAttributes, actualAttributes));
    }
}
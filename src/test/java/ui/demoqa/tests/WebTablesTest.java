package ui.demoqa.tests;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.demoqa.pages.ElementsPage;
import ui.demoqa.pages.MainPage;
import ui.demoqa.pages.WebTablesPage;
import ui.demoqa.textBoxTest.PersonalInfoTable;

import java.util.List;

import static ui.demoqa.TestData.*;

public class WebTablesTest extends BaseTest {

    private final static String BASE_URL = "https://demoqa.com/";

    @Test
    @Tag("regression")
    public void addDataTablesTest(){

        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.clickButton();

        ElementsPage  elementsPage = new ElementsPage();
        elementsPage.clickWebTablesButton();

        WebTablesPage webTablesPage = new WebTablesPage();
        webTablesPage.addNewRecord(NAME, LAST_NAME, EMAIL, AGE, SALARY, DEPARTMENT);

        PersonalInfoTable expectedAttributes = new PersonalInfoTable(
                NAME, LAST_NAME, EMAIL, AGE, SALARY, DEPARTMENT
        );

        List<String> actualData = webTablesPage.getActualUserData();

        PersonalInfoTable actualAttributes = new PersonalInfoTable(
                actualData
        );

        Assertions.assertTrue(EqualsBuilder.reflectionEquals(expectedAttributes, actualAttributes));
    }
}
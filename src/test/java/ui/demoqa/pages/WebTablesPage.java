package ui.demoqa.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class WebTablesPage {

    private final SelenideElement addNewRecordButton = $("#addNewRecordButton");
    private final SelenideElement submitAddTableButton = $("#submit");

    private final SelenideElement inputFirstName = $("#firstName");
    private final SelenideElement inputLastName = $("#lastName");
    private final SelenideElement inputEmail = $("#userEmail");
    private final SelenideElement inputAge = $("#age");
    private final SelenideElement inputSalary = $("#salary");
    private final SelenideElement inputDepartment = $("#department");

    private static final String ROW_CSS_SELECTOR = ".rt-tr.-even";
    private static final String CELL_XPATH_RELATIVE = "./div[@role='gridcell']";

    public void addNewRecord(String firstName, String lastName, String email, String age, String salary, String department) {
        addNewRecordButton.click();
        fillForm(firstName, lastName, email, age, salary, department);
        submitAddTableButton.click();
    }

    public void fillForm(String firstName, String lastName, String email, String age, String salary, String department){
        inputFirstName.setValue(firstName);
        inputLastName.setValue(lastName);
        inputEmail.setValue(email);
        inputAge.setValue(age);
        inputSalary.setValue(salary);
        inputDepartment.setValue(department);
    }

    private SelenideElement getTargetRow() {
        return $$(ROW_CSS_SELECTOR).get(1);
    }

    public List<String> getActualUserData() {
        SelenideElement rowElement = getTargetRow();
        ElementsCollection cellElements = rowElement.findAll(By.xpath(CELL_XPATH_RELATIVE));
        return  cellElements.texts();
    }
}
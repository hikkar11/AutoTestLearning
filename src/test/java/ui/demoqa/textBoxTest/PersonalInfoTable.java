package ui.demoqa.textBoxTest;

import java.util.List;

public class PersonalInfoTable {

    private String firstName;
    private String lastName;
    private String email;
    private String age;
    private String salary;
    private String department;

    public PersonalInfoTable(String firstName, String lastName, String email, String age, String salary, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }

    public PersonalInfoTable(List<String> actualData) {
        this.firstName = actualData.get(0);
        this.lastName = actualData.get(1);
        this.age = actualData.get(2);
        this.email = actualData.get(3);
        this.salary = actualData.get(4);
        this.department = actualData.get(5);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }

    public String getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }
}
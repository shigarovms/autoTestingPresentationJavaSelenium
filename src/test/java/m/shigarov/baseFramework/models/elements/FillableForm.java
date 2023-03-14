package m.shigarov.baseFramework.models.elements;

import m.shigarov.baseFramework.models.BaseForm;
import m.shigarov.baseFramework.models.User;
import org.openqa.selenium.By;

public class FillableForm extends BaseForm {
    public FillableForm(By correspondingUniqueElementLocator, String name) {
        super(correspondingUniqueElementLocator, name);
    }
    private final By firstNameFieldLocator = By.xpath("//input[@id='firstName']");
    private final By lastNameFieldLocator= By.xpath("//input[@id='lastName']");
    private final By userEmailLocator= By.xpath("//input[@id='userEmail']");
    private final By ageFieldLocator= By.xpath("//input[@id='age']");
    private final By salaryFieldFieldLocator= By.xpath("//input[@id='salary']");
    private final By departmentFieldLocator= By.xpath("//input[@id='department']");
    private final By submitButtonLocator = By.xpath("//button[@id='submit']");

    private final InputTextField firstNameField = new InputTextField(firstNameFieldLocator, "firstNameField");
    private final InputTextField lastNameField = new InputTextField(lastNameFieldLocator, "lastNameField");
    private final InputTextField emailField = new InputTextField(userEmailLocator, "emailField");
    private final InputTextField ageField = new InputTextField(ageFieldLocator, "ageField");
    private final InputTextField salaryField = new InputTextField(salaryFieldFieldLocator, "salaryField");
    private final InputTextField departmentField = new InputTextField(departmentFieldLocator, "departmentField");
    public Button submitButton = new Button(submitButtonLocator, "submitButton");

    public void fillWithUserData(User user) {
        firstNameField.fillWithText(user.getFirstName());
        lastNameField.fillWithText(user.getLastName());
        emailField.fillWithText(user.getEmail());
        ageField.fillWithText(String.valueOf(user.getAge()));
        salaryField.fillWithText(String.valueOf(user.getSalary()));
        departmentField.fillWithText(user.getDepartment());
    }
    public void submit() {
        submitButton.clickTheElement();
    }
}

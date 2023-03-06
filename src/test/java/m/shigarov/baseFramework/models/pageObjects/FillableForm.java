package m.shigarov.baseFramework.models.pageObjects;

import m.shigarov.baseFramework.models.BaseForm;
import m.shigarov.baseFramework.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static m.shigarov.baseFramework.driver.WebDriverUtils.getDriver;

public class FillableForm extends BaseForm {

    public FillableForm(String correspondingUniqueElementLocator, String name) {
        super(correspondingUniqueElementLocator, name);
    }
    private final WebElement firstNameField = getDriver().findElement(By.xpath("//input[@id='firstName']"));
    private final WebElement lastNameField = getDriver().findElement(By.xpath("//input[@id='lastName']"));
    private final WebElement emailField = getDriver().findElement(By.xpath("//input[@id='userEmail']"));
    private final WebElement ageField = getDriver().findElement(By.xpath("//input[@id='age']"));
    private final WebElement salaryField = getDriver().findElement(By.xpath("//input[@id='salary']"));
    private final WebElement departmentField = getDriver().findElement(By.xpath("//input[@id='department']"));

    public Clickable submitButton = new Clickable("//button[@id='submit']", "submitButton");

    public void fillWithUserDataAndSubmit(User user) {
        firstNameField.sendKeys(user.getFirstName());
        lastNameField.sendKeys(user.getLastName());
        emailField.sendKeys(user.getEmail());
        ageField.sendKeys(String.valueOf(user.getAge()));
        salaryField.sendKeys(String.valueOf(user.getSalary()));
        departmentField.sendKeys(user.getDepartment());
        submitButton.clickTheElement();
    }
}

package m.shigarov.baseFramework.models.pageObjects;

import m.shigarov.baseFramework.models.BaseForm;
import m.shigarov.baseFramework.models.elements.Button;
import m.shigarov.baseFramework.models.elements.FillableForm;
import m.shigarov.baseFramework.models.elements.WebTable;
import org.openqa.selenium.By;
import m.shigarov.baseFramework.models.User;
public class WebTablesPage extends BaseForm {
    private static final By uniqueElementsLocator = By.xpath("//div[@class=\"web-tables-wrapper\"]");
    private static final  String name = "webTablesPage";

    public WebTablesPage() {
        super(uniqueElementsLocator, name);
    }
    private final By webTableOfUsersLocator = By.xpath("//div[@class='web-tables-wrapper']");
    private final By addButtonLocator = By.xpath("//button[@id='addNewRecordButton']");
    private final By registrationFormLocator = By.xpath("//div[@class='modal-content']");

    private final WebTable webTableOfUsers = new WebTable(webTableOfUsersLocator, "webTableForm");
    private final Button addButton = new Button(addButtonLocator, "addButton");
    private final FillableForm registrationForm = new FillableForm(registrationFormLocator, "registrationForm");

    public void clickAddButton() {
        addButton.clickTheElement();
    }
    public boolean isRegistrationFormVisible() {
        return registrationForm.isVisible();
    }
    public void addUserToTheWebTable(User user) {
        registrationForm.fillWithUserData(user);
        registrationForm.submit();
    }
    public void removeUserFromTable(User user) {
        webTableOfUsers.removeUserFromTable(user);
    }
    public boolean isTheUserInTable(User user) {
        return webTableOfUsers.isUserInTable(user);
    }
}

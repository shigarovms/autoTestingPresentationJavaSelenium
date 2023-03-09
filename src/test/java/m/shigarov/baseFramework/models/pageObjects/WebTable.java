package m.shigarov.baseFramework.models.pageObjects;

import m.shigarov.baseFramework.models.BaseForm;
import m.shigarov.baseFramework.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static m.shigarov.baseFramework.config.ConfigurationManager.conf;
import static m.shigarov.baseFramework.driver.WebDriverUtils.waitUntilAndFindAnElement;

public class WebTable extends BaseForm {
    private final By locator;
    public WebTable(String strLocator, String uniqueElementLocator, String name) {
        super(uniqueElementLocator, name);
        this.locator = By.xpath(strLocator);
    }

    private WebElement getAsElement() {
        return waitUntilAndFindAnElement(this.locator);
    }

    public List<User> getTheListOfUsersFromTable() {
        List<User> listOfUsers = new ArrayList<>();
        By locatorForAnyFilledRow = By.xpath(conf().allFilledRows());
        List<WebElement> listOfAllFilledRows = this.getAsElement().findElements(locatorForAnyFilledRow);
        for (WebElement row: listOfAllFilledRows) {
            List<WebElement> userData = row.findElements(By.xpath(conf().eachCellInTheRow()));
            User user = new User(
                    userData.get(0).getText(),
                    userData.get(1).getText(),
                    Integer.parseInt(userData.get(2).getText()),
                    userData.get(3).getText(),
                    Integer.parseInt(userData.get(4).getText()),
                    userData.get(5).getText()
            );
            listOfUsers.add(user);
        }
        return listOfUsers;
    }

    public boolean checkIfUserInTable(User user) {
        return this.getTheListOfUsersFromTable().contains(user);
    }

    public void removeUserFromTable(User user) {
        By userRowLocator = By.xpath(String.format(conf().xpathToFormatToGetUserRow(), user.getEmail()));
        WebElement thisUserRow = waitUntilAndFindAnElement(userRowLocator);
        By removeThisUserButtonLocator = By.xpath(conf().xpathToFormatToGetRemoveUserButton());
        WebElement removeThisUserButton = thisUserRow.findElement(removeThisUserButtonLocator);
        removeThisUserButton.click();
    }
}

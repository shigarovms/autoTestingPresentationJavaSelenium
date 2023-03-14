package m.shigarov.baseFramework.models.elements;

import m.shigarov.baseFramework.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static m.shigarov.baseFramework.driver.WaitUtils.waitUntilAndFindAnElement;

public class WebTable {
    private final By locator;
    private final String name;
    public WebTable(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }
    private final By locatorForAnyFilledRow =
            By.xpath("//div[@class='rt-tr-group' and ./div/div[string-length(text()) > 0]]");
    private final By locatorForEachCellInTheRow = By.xpath(".//div[@class='rt-td']");
    private final String xpathToFormatToGetUserRow = "//div[@class='rt-tr-group' and .//div[text()='%s']]";
    private final By removeThisUserButtonLocator = By.xpath(".//span[@title='Delete']/*");
    private WebElement getAsElement() {
        return waitUntilAndFindAnElement(this.locator);
    }
    private List<WebElement> getListOfAllFilledRows() {
        return this.getAsElement().findElements(locatorForAnyFilledRow);
    }
    public List<User> getTheListOfUsersFromTable() {
        List<User> listOfUsers = new ArrayList<>();
        for (WebElement row: getListOfAllFilledRows()) {
            List<WebElement> userData = row.findElements(locatorForEachCellInTheRow);
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
    public boolean isUserInTable(User user) {
        return this.getTheListOfUsersFromTable().contains(user);
    }
    public void removeUserFromTable(User user) {
        By userRowLocator = By.xpath(String.format(xpathToFormatToGetUserRow, user.getEmail()));
        WebElement thisUserRow = waitUntilAndFindAnElement(userRowLocator);
        WebElement removeThisUserButton = thisUserRow.findElement(removeThisUserButtonLocator);
        removeThisUserButton.click();
    }
}

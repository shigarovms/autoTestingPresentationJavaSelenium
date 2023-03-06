package m.shigarov.baseFramework.models.pageObjects;

import m.shigarov.baseFramework.models.BaseElement;
import m.shigarov.baseFramework.models.BaseForm;
import m.shigarov.baseFramework.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

import static m.shigarov.baseFramework.config.ConfigurationManager.configuration;
import static m.shigarov.baseFramework.driver.WebDriverUtils.getDriver;

public class HomePage extends BaseForm {
    private static final String correspondingUniqueElementLocator = configuration().homePageUniqueElement();
    public static final String name = "Home-Page";
    public String URL = configuration().homePageUrl();

    public HomePage() {
        super(correspondingUniqueElementLocator, name);
    }

    public Clickable alertsFrameWindowsCard =
            new Clickable(configuration().alertsFrameWindowsCard(), "alertsFrameWindowsCard");
    public Clickable alertsItemFromList =
            new Clickable(configuration().alertsItemFromList(), "alertsListItem");
    public BaseForm alertsForm = new BaseForm(configuration().alertsForm() ,"alertsForm");
    public Clickable buttonToSeeAlert =
            new Clickable(configuration().buttonToSeeAlert(), "buttonToSeeAlert");

    public Clickable buttonToSeeConfirmationAlert =
            new Clickable(configuration().buttonToSeeConfirmationAlert(), "buttonToSeeConfirmationAlert");
    public Clickable buttonToSeePromtAlert =
            new Clickable(configuration().buttonToSeePromtAlert(), "buttonToSeePromtAlert");
    public BaseElement promptResult = new BaseElement(configuration().promptResult(), "promptResult");

    // 2
    public Clickable nestedFramesItemFromList =
            new Clickable(configuration().nestedFramesItemFromList(), "nestedFramesItemFromList");
    public BaseForm nestedFramesForm = new BaseForm(configuration().nestedFramesForm(), "nestedFramesForm");
    public IFrame parentFrame = new IFrame(configuration().parentFrame(),
                                            configuration().parentFrameUniqueElement(),
                                        "parentFrame");
    public IFrame childFrame = new IFrame(configuration().childFrame(),
                                            configuration().childFrameUniqueElement(),
                                            "childFrame");
    public Clickable framesItemFromList = new Clickable(configuration().framesItemFromList(), "framesItemFromList");
    public IFrame upperFrame = new IFrame(configuration().upperFrame(),
                                            configuration().upperFrameUniqueElement(),
                                                "upperFrame");
    public IFrame lowerFrame = new IFrame(configuration().lowerFrame(),
                                            configuration().lowerFrameUniqueElement(),
                                            "lowerFrame");
    // 3
    public Clickable elementsCard = new Clickable(configuration().elementsCard(), "elementsCard");
    public Clickable webTablesItemFromList = new Clickable(configuration().webTablesItemFromList(),
                                                "webTablesItemFromList");
    public BaseForm webTableForm = new BaseForm(configuration().webTableForm(), "webTableForm");
    public Clickable addButton = new Clickable(configuration().addButton(), "addButton");
//    public FillableForm registrationForm = new FillableForm(
//            configuration().registrationForm(), "registrationForm");
    // 4
    public Clickable browserWindowsItemFromList = new Clickable(configuration().browserWindowsItemFromList(),
                                                "browserWindowsItemFromList");
    public Clickable newTabButton = new Clickable(configuration().newTabButton(), "newTabButton");
    public Clickable elementsMenuItem = new Clickable(configuration().elementsMenuItem(), "elementsMenuItem");
    public Clickable linksItemFromList = new Clickable(configuration().linksItemFromList(), "linksItemFromList");
    public Clickable homeSimpleLink = new Clickable(configuration().homeSimpleLink(), "homeSimpleLink");


    public boolean alertWithTextIsVisible(String text) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(6));
        String alertText = wait.until(ExpectedConditions.alertIsPresent()).getText();
        return Objects.equals(alertText, text);
    }

    public boolean noAlertsAreVisible() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        try {
            wait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            return true;
        }
        return false;
    }

    public void acceptTheAlert() {
        getDriver().switchTo().alert().accept();
    }

    public void fillTheAlertWithText(String textToFillTheAlertWith) {
        getDriver().switchTo().alert().sendKeys(textToFillTheAlertWith);
    }

    public String getPromtResultText() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        return wait.until(ExpectedConditions.presenceOfElementLocated(
                promptResult.getLocator())).getText().replace("You entered ", "");
    }

    public boolean checkIfUserInTable(User user) {
        // TODO take number of seconds in waiting to general.properties
        try {
            (new WebDriverWait(getDriver(), Duration.ofSeconds(2)))
                    .until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath(String.format(configuration().xpathToFormatToGetUserEmailField(),
                                    user.getEmail()))));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void removeUserFromTable(User user) {
        WebElement thisUserRow = new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath(String.format(configuration().xpathToFormatToGetUserRow(), user.getEmail()))
                ));
        WebElement removeThisUserButton = thisUserRow.findElement(By.xpath(
                configuration().xpathToFormatToGetRemoveUserButton()));
        removeThisUserButton.click();
    }
}

package m.shigarov.baseFramework.models.pageObjects;

import m.shigarov.baseFramework.models.BaseElement;
import m.shigarov.baseFramework.models.BaseForm;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

import static m.shigarov.baseFramework.config.ConfigurationManager.conf;
import static m.shigarov.baseFramework.driver.WebDriverUtils.getDriver;
import static m.shigarov.baseFramework.driver.WebDriverUtils.waitUntilAndFindAnElement;

public class WebAppPage extends BaseForm {
    private static final String correspondingUniqueElementLocator = conf().homePageUniqueElement();
    public static final String name = "Home-Page";
    public String URL = conf().homePageUrl();

    public WebAppPage() {
        super(correspondingUniqueElementLocator, name);
    }

    public Clickable alertsFrameWindowsCard =
            new Clickable(conf().alertsFrameWindowsCard(), "alertsFrameWindowsCard");
    public Clickable alertsItemFromList =
            new Clickable(conf().alertsItemFromList(), "alertsListItem");
    public BaseForm alertsForm = new BaseForm(conf().alertsForm() ,"alertsForm");
    public Clickable buttonToSeeAlert = new Clickable(conf().buttonToSeeAlert(), "buttonToSeeAlert");

    public Clickable buttonToSeeConfirmationAlert =
            new Clickable(conf().buttonToSeeConfirmationAlert(), "buttonToSeeConfirmationAlert");
    public Clickable buttonToSeePromtAlert =
            new Clickable(conf().buttonToSeePromtAlert(), "buttonToSeePromtAlert");
    public BaseElement promptResult = new BaseElement(conf().promptResult(), "promptResult");

    // 2
    public Clickable nestedFramesItemFromList =
            new Clickable(conf().nestedFramesItemFromList(), "nestedFramesItemFromList");
    public BaseForm nestedFramesForm = new BaseForm(conf().nestedFramesForm(), "nestedFramesForm");
    public IFrame parentFrame = new IFrame(conf().parentFrame(), conf().parentFrameUniqueElement(), "parentFrame");
    public IFrame childFrame = new IFrame(conf().childFrame(), conf().childFrameUniqueElement(), "childFrame");
    public Clickable framesItemFromList = new Clickable(conf().framesItemFromList(), "framesItemFromList");
    public IFrame upperFrame = new IFrame(conf().upperFrame(), conf().upperFrameUniqueElement(), "upperFrame");
    public IFrame lowerFrame = new IFrame(conf().lowerFrame(), conf().lowerFrameUniqueElement(), "lowerFrame");
    // 3
    public Clickable elementsCard = new Clickable(conf().elementsCard(), "elementsCard");
    public Clickable webTablesItemFromList = new Clickable(conf().webTablesItemFromList(), "webTablesItemFromList");
    public WebTable webTableOfUsers =
            new WebTable(conf().webTableForm(), conf().webTableUniqueElement(), "webTableForm");
    public Clickable addButton = new Clickable(conf().addButton(), "addButton");

    // 4
    public Clickable browserWindowsItemFromList = new Clickable(conf().browserWindowsItemFromList(),
                                                "browserWindowsItemFromList");
    public Clickable newTabButton = new Clickable(conf().newTabButton(), "newTabButton");
    public Clickable elementsMenuItem = new Clickable(conf().elementsMenuItem(), "elementsMenuItem");
    public Clickable linksItemFromList = new Clickable(conf().linksItemFromList(), "linksItemFromList");
    public Clickable homeSimpleLink = new Clickable(conf().homeSimpleLink(), "homeSimpleLink");


    public boolean alertWithTextIsVisible(String text) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(conf().waitDur()));
        String alertText = wait.until(ExpectedConditions.alertIsPresent()).getText();
        return Objects.equals(alertText, text);
    }

    public boolean noAlertsAreVisible() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(conf().waitDur()));
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

    public String getPromtResultTextWithout(String textToDelete) {
        return waitUntilAndFindAnElement(promptResult.getLocator()).getText().replace(textToDelete, "");
    }
}

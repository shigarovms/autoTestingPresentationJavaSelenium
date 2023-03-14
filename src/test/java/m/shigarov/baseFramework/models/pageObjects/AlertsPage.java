package m.shigarov.baseFramework.models.pageObjects;

import m.shigarov.baseFramework.models.BaseElement;
import m.shigarov.baseFramework.models.BaseForm;
import m.shigarov.baseFramework.models.elements.Button;
import org.openqa.selenium.By;

import static m.shigarov.baseFramework.driver.WaitUtils.waitUntilAndFindAnElement;

public class AlertsPage extends BaseForm {
    private static final By uniqueElementsLocator = By.xpath("//*[@id='javascriptAlertsWrapper']");
    private static final  String name = "alertsPage";

    public AlertsPage() {
        super(uniqueElementsLocator, name);
    }
    private final By buttonToSeeAlertLocator = By.xpath("//*[@id='alertButton']");
    private final By buttonToSeeConfirmationAlertLocator = By.xpath("//*[@id=\"confirmButton\"]");
    private final By buttonToSeePromtAlertLocator = By.xpath("//*[@id=\"promtButton\"]");
    private final By promptResultLocator = By.xpath("//*[@id=\"promptResult\"]");

    private final Button buttonToSeeAlert =
            new Button(buttonToSeeAlertLocator, "buttonToSeeAlert");
    private final Button buttonToSeeConfirmationAlert =
            new Button(buttonToSeeConfirmationAlertLocator, "buttonToSeeConfirmationAlert");
    private final Button buttonToSeePromtAlert =
            new Button(buttonToSeePromtAlertLocator, "buttonToSeePromtAlert");
    private final BaseElement promptResult = new BaseElement(promptResultLocator, "promptResult");

    public void clickButtonToSeeAlert() {
        buttonToSeeAlert.clickTheElement();
    }
    public void clickButtonToSeeConfirmationAlert() {
        buttonToSeeConfirmationAlert.clickTheElement();
    }
    public void clickButtonToSeePromtAlert() {
        buttonToSeePromtAlert.clickTheElement();
    }
    public String getPromtResultTextWithout(String textToDelete) {
        return waitUntilAndFindAnElement(promptResult.getLocator()).getText().replace(textToDelete, "");
    }
}

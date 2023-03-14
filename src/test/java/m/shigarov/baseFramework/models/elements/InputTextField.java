package m.shigarov.baseFramework.models.elements;

import m.shigarov.baseFramework.models.BaseElement;
import org.openqa.selenium.By;

import static m.shigarov.baseFramework.driver.WaitUtils.waitUntilAndFindAnElement;

public class InputTextField extends BaseElement {
    public InputTextField(By locator, String name) {
        super(locator, name);
    }
    public void fillWithText(String textToSend) {
        waitUntilAndFindAnElement(this.locator).sendKeys(textToSend);
    }
}

package m.shigarov.baseFramework.models.pageObjects;

import m.shigarov.baseFramework.models.BaseForm;
import m.shigarov.baseFramework.models.elements.Button;
import org.openqa.selenium.By;

public class BrowserWindowsPage extends BaseForm {
    private static final By uniqueElementLocator = By.xpath("//button[@id='tabButton' and text()='New Tab']");
    private static final String name = "browserWindowsPage";
    public BrowserWindowsPage() {
        super(uniqueElementLocator, name);
    }
    private final By newTabButtonLocator = By.xpath("//button[@id='tabButton' and text()='New Tab']");

    private final Button newTabButton = new Button(newTabButtonLocator, "newTabButton");

    public void clickNewTabButton() {
        newTabButton.clickTheElementJS();
    }
}

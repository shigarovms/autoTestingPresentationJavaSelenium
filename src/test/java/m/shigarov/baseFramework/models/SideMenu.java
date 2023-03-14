package m.shigarov.baseFramework.models;

import m.shigarov.baseFramework.models.elements.Button;
import org.openqa.selenium.By;

public class SideMenu {
    private final By alertsItemFromListLocator = By.xpath("//span[text() = 'Alerts']//parent::li");
    private final By nestedFramesItemFromListsLocator = By.xpath("//span[text() = 'Nested Frames']//parent::li");
    private final By framesItemFromListsLocator = By.xpath("//span[text()='Frames']//parent::li");
    private final By webTablesItemFromListLocator = By.xpath("//span[text()='Web Tables']//parent::li");
    private final By browserWindowsItemFromListLocator = By.xpath("//span[text()='Browser Windows']//parent::li");
    private final By elementsMenuItemLocator = By.xpath("//div[text()='Elements']//parent::div[@class='header-wrapper']");
    private final By linksItemFromListLocator = By.xpath("//span[text()='Links']//parent::li");

    private final Button alertsItemFromList = new Button(alertsItemFromListLocator, "alertsItemFromList");
    private final Button nestedFramesItemFromList = new Button(nestedFramesItemFromListsLocator, "nestedFramesItemFromList");
    private final Button framesItemFromList = new Button(framesItemFromListsLocator, "framesItemFromList");
    private final Button webTablesItemFromList = new Button(webTablesItemFromListLocator, "webTablesItemFromList");
    private final Button browserWindowsItemFromList = new Button(browserWindowsItemFromListLocator, "browserWindowsItemFromList");
    private final Button elementsMenuItem = new Button(elementsMenuItemLocator,"elementsMenuItem");
    private final Button linksItemFromList = new Button(linksItemFromListLocator, "linksItemFromList");

    public void clickAlertsItemFromList(){
        alertsItemFromList.clickTheElementJS();
    }
    public void clickNestedFramesItemFromList(){
        nestedFramesItemFromList.clickTheElementJS();
    }
    public void clickFramesItemFromList(){
        framesItemFromList.clickTheElementJS();
    }
    public void clickWebTablesItemFromList(){
        webTablesItemFromList.clickTheElementJS();
    }
    public void clickBrowserWindowsItemFromList(){
        browserWindowsItemFromList.clickTheElementJS();
    }
    public void clickElementsMenuItem(){
        elementsMenuItem.clickTheElement();
    }
    public void clickLinksItemFromList(){
        linksItemFromList.clickTheElementJS();
    }
}

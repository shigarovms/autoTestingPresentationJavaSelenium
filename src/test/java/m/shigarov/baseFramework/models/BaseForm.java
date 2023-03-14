package m.shigarov.baseFramework.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

import static m.shigarov.baseFramework.driver.WaitUtils.waitUntilAndFindAnElement;

public class BaseForm {
    public final Logger logger = Logger.getLogger(this.getClass().getName());
    private final By uniqueElementLocator;
    private final String name;
    public SideMenu sideMenu;

    public BaseForm(By uniqueElementLocator, String name) {
        this.uniqueElementLocator = uniqueElementLocator;
        this.name = name;
        this.sideMenu = new SideMenu();
    }

    public By getUniqueElementLocator() {
        return this.uniqueElementLocator;
    }
    public boolean isVisible() {
        return waitUntilAndFindAnElement(this.uniqueElementLocator).isDisplayed();
    }

    public WebElement findTheElement(By locator) {
        return waitUntilAndFindAnElement(locator);
    }

    @Override
    public String toString() {
        return this.name;
    }
}

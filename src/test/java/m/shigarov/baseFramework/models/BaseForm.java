package m.shigarov.baseFramework.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

import static m.shigarov.baseFramework.driver.WebDriverUtils.waitUntilAndFindAnElement;

public class BaseForm {
    public final Logger logger = Logger.getLogger(this.getClass().getName());
    protected final By uniqueElementLocator;
    protected final String name;

    public BaseForm(String uniqueElementLocator, String name) {
        this.uniqueElementLocator = By.xpath(uniqueElementLocator);
        this.name = name;
    }

    public By getUniqueElementLocator() {
        return this.uniqueElementLocator;
    }
    public boolean confirmItIsShown() {
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

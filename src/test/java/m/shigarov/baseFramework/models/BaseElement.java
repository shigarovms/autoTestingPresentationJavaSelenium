package m.shigarov.baseFramework.models;

import org.openqa.selenium.By;

import static m.shigarov.baseFramework.driver.WebDriverUtils.waitUntilAndFindAnElement;

public class BaseElement {
    protected final By locator;
    protected final String name;

    public BaseElement(String strXpath, String name) {
        this.locator = By.xpath(strXpath);
        this.name = name;
    }

    public By getLocator() {
        return this.locator;
    }
    public boolean isVisible() {
        return waitUntilAndFindAnElement(this.locator).isDisplayed();
    }
    @Override
    public String toString() {
        return this.name;
    }
}

package m.shigarov.baseFramework.models;

import org.openqa.selenium.By;

import static m.shigarov.baseFramework.driver.WebDriverUtils.getDriver;

public class BaseElement {
    protected final By locator;
    protected final String name;

    public BaseElement(String xpath, String name) {
        this.locator = By.xpath(xpath);
        this.name = name;
    }

    public By getLocator() {
        return this.locator;
    }
    public boolean isVisible() {
        return getDriver().findElement(this.locator).isDisplayed();
    }
    @Override
    public String toString() {
        return this.name;
    }
}

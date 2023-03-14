package m.shigarov.baseFramework.models;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static m.shigarov.baseFramework.driver.Singleton.getDriver;
import static m.shigarov.baseFramework.driver.WaitUtils.waitForToBeClickableAndFind;
import static m.shigarov.baseFramework.driver.WaitUtils.waitUntilAndFindAnElement;

public class BaseElement {
    protected final By locator;
    protected final String name;

    public BaseElement(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    public By getLocator() {
        return this.locator;
    }
    public boolean isVisible() {
        return waitUntilAndFindAnElement(this.locator).isDisplayed();
    }
    public void clickTheElement() {
        waitForToBeClickableAndFind(this).click();
    }
    public void clickTheElementJS() {
        WebElement element = waitForToBeClickableAndFind(this);
        ((JavascriptExecutor)getDriver()).executeScript("arguments[0].click();", element);
    }
    @Override
    public String toString() {
        return this.name;
    }
}

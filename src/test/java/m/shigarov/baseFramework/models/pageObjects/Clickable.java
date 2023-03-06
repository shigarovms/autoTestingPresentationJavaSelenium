package m.shigarov.baseFramework.models.pageObjects;

import m.shigarov.baseFramework.models.BaseElement;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static m.shigarov.baseFramework.driver.WebDriverUtils.getDriver;

public class Clickable extends BaseElement {
    public Clickable(String locator, String name) {
        super(locator, name);
    }

    public void clickTheElement() {
        getDriver().switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(this.locator));
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor)getDriver()).executeScript("arguments[0].click();", element);
        }
    }
}

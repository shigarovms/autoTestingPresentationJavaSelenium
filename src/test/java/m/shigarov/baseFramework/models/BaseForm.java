package m.shigarov.baseFramework.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

import static m.shigarov.baseFramework.driver.WebDriverUtils.getDriver;

public class BaseForm {
    public final Logger logger = Logger.getLogger(this.getClass().getName());
    protected final By correspondingUniqueElementLocator;
    protected final String name;

    public BaseForm(String correspondingUniqueElementLocator, String name) {
        this.correspondingUniqueElementLocator = By.xpath(correspondingUniqueElementLocator);
        this.name = name;
    }

    public By getCorrespondingUniqueElementLocator() {
        return this.correspondingUniqueElementLocator;
    }
    public boolean confirmItIsShown() {
        return (new WebDriverWait(getDriver(), Duration.ofSeconds(5)))
                .until(ExpectedConditions.presenceOfElementLocated(
                        this.correspondingUniqueElementLocator)).isDisplayed();
    }

    public WebElement findTheElement(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    @Override
    public String toString() {
        return this.name;
    }
}

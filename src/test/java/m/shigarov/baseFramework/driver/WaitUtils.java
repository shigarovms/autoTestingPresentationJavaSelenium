package m.shigarov.baseFramework.driver;

import m.shigarov.baseFramework.models.BaseElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

import static m.shigarov.baseFramework.config.ConfigurationManager.conf;
import static m.shigarov.baseFramework.driver.Singleton.getDriver;

public class WaitUtils {
    static String className = new Object() {}.getClass().getName();
    private static final Logger logger = Logger.getLogger(className);
    private static final Duration timeoutInSeconds = Duration.ofSeconds(conf().waitDur());
    private static final Duration pollingSleepInSeconds = Duration.ofMillis(conf().pollingSleepDur());
    public static WebElement waitUntilAndFindAnElement(By locator) {
        return (new WebDriverWait(getDriver(), timeoutInSeconds, pollingSleepInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public static WebElement waitForToBeClickableAndFind(BaseElement button) {
        return (new WebDriverWait(getDriver(), timeoutInSeconds, pollingSleepInSeconds))
                .until(ExpectedConditions.elementToBeClickable(button.getLocator()));
    }
    public static Alert waitUntilAndFindAnAlert() {
        return  (new WebDriverWait(getDriver(), timeoutInSeconds, pollingSleepInSeconds))
                .until(ExpectedConditions.alertIsPresent());
    }
}

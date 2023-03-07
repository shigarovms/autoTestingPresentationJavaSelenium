package m.shigarov.baseFramework.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.logging.Logger;

import static m.shigarov.baseFramework.config.ConfigurationManager.conf;

public class WebDriverUtils {
    private static final Logger logger = Logger.getLogger("WebDriverUtils");

    private static WebDriver webdriver;
    private WebDriverUtils() {
    }

    public static WebDriver getDriver() {
        String browser = conf().browser();
        if (webdriver == null) {
            webdriver = BrowserFactory.valueOf(browser.toUpperCase()).createDriver();
            logger.warning("webdriver has been created");
        }
        return webdriver;
    }
    public static void setWebdriver(WebDriver webdriver) {
        WebDriverUtils.webdriver = webdriver;
    }
    public static void moveToLastOpenedTab() {
        ArrayList<String> wid = new ArrayList<>(webdriver.getWindowHandles());
        webdriver.switchTo().window(wid.get(wid.size()-1));
    }
    public static void closeLastOpenedTab() {
        ArrayList<String> wid = new ArrayList<>(webdriver.getWindowHandles());
        webdriver.switchTo().window(wid.get(wid.size()-1));
        webdriver.close();
        webdriver.switchTo().window(wid.get(0));
    }

    public static WebElement waitUntilAndFindAnElement(By locator) {
        return (new WebDriverWait(webdriver, Duration.ofSeconds(conf().waitDur())))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public static WebElement waitUntilAndFindAnElement(String strXpath) {
        return (new WebDriverWait(webdriver, Duration.ofSeconds(conf().waitDur())))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(strXpath)));
    }
}

package m.shigarov.baseFramework.driver;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.logging.Logger;

import static m.shigarov.baseFramework.config.ConfigurationManager.configuration;

public class WebDriverUtils {
    private static final Logger logger = Logger.getLogger("WebDriverUtils");

    private static WebDriver webdriver;
    private WebDriverUtils() {
    }

    public static WebDriver getDriver() {
        String browser = configuration().browser();
        if (webdriver == null) {
            webdriver = BrowserFactory.valueOf(browser.toUpperCase()).createDriver();
            logger.warning("webdriver has been started");
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
}

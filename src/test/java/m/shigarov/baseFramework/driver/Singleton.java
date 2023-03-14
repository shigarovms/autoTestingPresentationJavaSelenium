package m.shigarov.baseFramework.driver;

import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

import static m.shigarov.baseFramework.config.ConfigurationManager.conf;

public class Singleton {
    static String className = new Object() {}.getClass().getName();
    private static final Logger logger = Logger.getLogger(className);
    private static WebDriver webdriver;
    private Singleton() {
    }
    public static WebDriver getDriver() {
        String browser = conf().browser();
        if (webdriver == null) {
            webdriver = BrowserFactory.valueOf(browser.toUpperCase()).createDriver();
            logger.warning("webdriver has been created");
        }
        return webdriver;
    }
    public static void teardropWebdriver() {
        webdriver = null;
    }
}

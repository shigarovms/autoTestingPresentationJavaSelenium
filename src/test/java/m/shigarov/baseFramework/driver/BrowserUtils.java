package m.shigarov.baseFramework.driver;

import org.openqa.selenium.TimeoutException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Logger;

import static m.shigarov.baseFramework.config.ConfigurationManager.conf;
import static m.shigarov.baseFramework.driver.Singleton.getDriver;
import static m.shigarov.baseFramework.driver.WaitUtils.waitUntilAndFindAnAlert;

public class BrowserUtils {
    static String className = new Object() {}.getClass().getName();
    private static final Logger logger = Logger.getLogger(className);

    private static ArrayList<String> getListOfWindowHandles() {
        return new ArrayList<>(getDriver().getWindowHandles());
    }
    private static void switchToTheNthTab(int numberOfTab) {
        if (numberOfTab <= getNumberOfTabs()) {
            getDriver().switchTo().window(getListOfWindowHandles().get(numberOfTab - 1));
        } else {
            logger.warning(String.format("there's no such a tab with number %s", numberOfTab));
        }
    }
    private static void closeTheNthTab(int numberOfTab) {
        switchToTheNthTab(numberOfTab);
        getDriver().close();
    }
    public static void navigateToHomePage() {
        getDriver().navigate().to(conf().homePageUrl());
    }
    public static int getNumberOfTabs() {
        return getListOfWindowHandles().size();
    }
    public static void moveToLastOpenedTab() {
        switchToTheNthTab(getNumberOfTabs());
    }
    public static void closeLastOpenedTab() {
        closeTheNthTab(getNumberOfTabs());
        switchToTheNthTab(getNumberOfTabs());
    }
    public static boolean isAlertWithTextVisible(String text) {
        return Objects.equals(waitUntilAndFindAnAlert().getText(), text);
    }
    public static boolean areNoAlertsVisible() {
        try {
            waitUntilAndFindAnAlert();
        } catch (TimeoutException e) {
            return true;
        }
        return false;
    }
    public static void acceptTheAlert() {
        getDriver().switchTo().alert().accept();
    }
    public static void fillTheAlertWithText(String textToFillTheAlertWith) {
        getDriver().switchTo().alert().sendKeys(textToFillTheAlertWith);
    }
}

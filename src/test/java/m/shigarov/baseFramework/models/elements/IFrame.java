package m.shigarov.baseFramework.models.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

import static m.shigarov.baseFramework.driver.Singleton.getDriver;
import static m.shigarov.baseFramework.driver.WaitUtils.waitUntilAndFindAnElement;

public class IFrame {
    static String className = new Object() {}.getClass().getName();
    private static final Logger logger = Logger.getLogger(className);
    private final By locator;
    private final By uniqueElementsLocator;
    private final String name;
    public IFrame(By locator, By uniqueElementsLocator, String name) {
        this.locator = locator;
        this.uniqueElementsLocator = uniqueElementsLocator;
        this.name = name;
    }

    public By getUniqueElementsLocator() {
        return uniqueElementsLocator;
    }

    public WebElement getThisAsElement() {
        return  waitUntilAndFindAnElement(this.locator);
    }

    public boolean isUniqueElementInside(boolean escapeToDefaultContextAfter) {
        switchToThisFrame();
        logger.info("switched to another frame");
        boolean result = (waitUntilAndFindAnElement(getUniqueElementsLocator()) != null);
        if (escapeToDefaultContextAfter) {
            switchToDefaultContent();
            logger.info("switched to default content");
        }
        return result;
    }
    public void switchToThisFrame() {
        getDriver().switchTo().frame(this.getThisAsElement());
    }
    public void switchToDefaultContent() {
        getDriver().switchTo().defaultContent();
    }
    public String getMessageFromInside() {
        switchToThisFrame();
        String text = waitUntilAndFindAnElement(getUniqueElementsLocator()).getText();
        switchToDefaultContent();
        return text;
    }
}

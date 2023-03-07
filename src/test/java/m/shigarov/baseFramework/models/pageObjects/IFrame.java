package m.shigarov.baseFramework.models.pageObjects;

import m.shigarov.baseFramework.models.BaseForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static m.shigarov.baseFramework.driver.WebDriverUtils.getDriver;
import static m.shigarov.baseFramework.driver.WebDriverUtils.waitUntilAndFindAnElement;

public class IFrame extends BaseForm {
    protected By locator;
    public IFrame(String strLocator, String correspondingUniqueElementLocator, String name) {
        super(correspondingUniqueElementLocator, name);
        this.locator = By.xpath(strLocator);
    }

    public WebElement getThisAsElement() {
        return  getDriver().findElement(this.locator);
    }

    public boolean uniqueElementIsInside(boolean escapeToDefaultContextAfter) {
        getDriver().switchTo().frame(this.getThisAsElement());
        logger.info("switched to another frame");
        boolean result = (waitUntilAndFindAnElement(this.uniqueElementLocator) != null);
        if (escapeToDefaultContextAfter) {
            getDriver().switchTo().defaultContent();
            logger.info("switched to default content");
        }
        return result;
    }

    public String getMessageFromInside() {

        getDriver().switchTo().frame(this.getThisAsElement());
        String text = waitUntilAndFindAnElement(this.uniqueElementLocator).getText();
        getDriver().switchTo().defaultContent();
        return text;
    }
}

package m.shigarov.baseFramework.models.pageObjects;

import m.shigarov.baseFramework.models.BaseForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static m.shigarov.baseFramework.driver.WebDriverUtils.getDriver;

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
        logger.warning("switched to another frame");
//        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        boolean result = (new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(
                this.correspondingUniqueElementLocator)) != null);
        if (escapeToDefaultContextAfter) {
            getDriver().switchTo().defaultContent();
            logger.warning("switched to default content");
        }
        return result;
    }

    public String getMessageFromInside() {

        getDriver().switchTo().frame(this.getThisAsElement());
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        String text = wait.until(ExpectedConditions.presenceOfElementLocated(
                this.correspondingUniqueElementLocator)).getText();
        getDriver().switchTo().defaultContent();
        return text;
    }
}

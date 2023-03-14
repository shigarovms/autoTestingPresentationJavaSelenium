package m.shigarov.baseFramework.models.pageObjects;

import m.shigarov.baseFramework.models.BaseForm;
import m.shigarov.baseFramework.models.elements.Button;
import org.openqa.selenium.By;

import static m.shigarov.baseFramework.config.ConfigurationManager.conf;
import static m.shigarov.baseFramework.driver.Singleton.getDriver;

public class HomePage extends BaseForm {
    private static final By uniqueElementLocator = By.xpath("//*[@class='category-cards']");
    private static final String name = "homePage";
    private static final String URL = conf().homePageUrl();

    public HomePage() {
        super(uniqueElementLocator, name);
    }
    private final By alertsFrameWindowsCardsLocator =
            By.xpath("//div[@class='card mt-4 top-card' and .//h5[text() = 'Alerts, Frame & Windows']]");
    private final By elementsCardLocator =
            By.xpath("//div[@class='card mt-4 top-card' and .//h5[text() = 'Elements']]");

    private final Button alertsFrameWindowsCard = new Button(alertsFrameWindowsCardsLocator, "alertsFrameWindowsCard");
    private final Button elementsCard = new Button(elementsCardLocator, "elementsCard");
    public void clickTheAlertsFrameWindowsCard() {
        alertsFrameWindowsCard.clickTheElement();
    }
    public void clickTheElementsCard() {
        elementsCard.clickTheElement();
    }
}

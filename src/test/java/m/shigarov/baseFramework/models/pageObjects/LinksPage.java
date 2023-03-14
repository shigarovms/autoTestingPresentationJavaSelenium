package m.shigarov.baseFramework.models.pageObjects;

import m.shigarov.baseFramework.models.BaseForm;
import m.shigarov.baseFramework.models.elements.Button;
import org.openqa.selenium.By;

public class LinksPage extends BaseForm {
    private static final String name = "linksPage";
    public LinksPage() {
        super(homeSimpleLinkLocator, name);
    }
    private static final By homeSimpleLinkLocator = By.xpath("//a[@id='simpleLink' and text()='Home']");

    private final Button homeSimpleLinkButton = new Button(homeSimpleLinkLocator, "homeSimpleLink");

    public void clickHomeSimpleLinkButton() {
        homeSimpleLinkButton.clickTheElement();
    }
}

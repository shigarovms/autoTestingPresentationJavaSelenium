package m.shigarov.baseFramework.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:general.properties",
        "classpath:locators.properties",
        "classpath:grid.properties"})

public interface Configuration extends Config {

    @Key("browser")
    String browser();

    @Key("headless")
    Boolean headless();

    @Key("url.homePage")
    String homePageUrl();

    @Key("locator.homePageUniqueElement")
    String homePageUniqueElement();

    @Key("locator.alertsFrameWindowsCard")
    String alertsFrameWindowsCard();

    @Key("locator.alertsItemFromList")
    String alertsItemFromList();

    @Key("locator.alertsForm")
    String alertsForm();

    @Key("locator.buttonToSeeAlert")
    String buttonToSeeAlert();

    @Key("locator.buttonToSeeConfirmationAlert")
    String buttonToSeeConfirmationAlert();

    @Key("locator.buttonToSeePromtAlert")
    String buttonToSeePromtAlert();

    @Key("locator.promptResult")
    String promptResult();

    @Key("locator.nestedFramesItemFromList")
    String nestedFramesItemFromList();

    @Key("locator.nestedFramesForm")
    String nestedFramesForm();

    @Key("locator.parentFrame")
    String parentFrame();

    @Key("locator.childFrame")
    String childFrame();

    @Key("locator.parentFrameUniqueElement")
    String parentFrameUniqueElement();

    @Key("locator.childFrameUniqueElement")
    String childFrameUniqueElement();

    @Key("locator.framesItemFromList")
    String framesItemFromList();

    @Key("locator.upperFrame")
    String upperFrame();

    @Key("locator.upperFrameUniqueElement")
    String upperFrameUniqueElement();

    @Key("locator.lowerFrame")
    String lowerFrame();

    @Key("locator.lowerFrameUniqueElement")
    String lowerFrameUniqueElement();

    @Key("locator.browserWindowsItemFromList")
    String browserWindowsItemFromList();
    @Key("locator.newTabButton")
    String newTabButton();

    @Key("locator.elementsMenuItem")
    String elementsMenuItem();

    @Key("locator.linksItemFromList")
    String linksItemFromList();

    @Key("locator.homeSimpleLink")
    String homeSimpleLink();

    @Key("locator.webTablesItemFromList")
    String webTablesItemFromList();

    @Key("locator.webTableForm")
    String webTableForm();

    @Key("locator.elementsCard")
    String elementsCard();

    @Key("locator.addButton")
    String addButton();

    @Key("locator.registrationForm")
    String registrationForm();

    @Key("locator.xpathToFormatToGetUserEmailField")
    String xpathToFormatToGetUserEmailField();

    @Key("locator.xpathToFormatToGetUserRow")
    String xpathToFormatToGetUserRow();

    @Key("locator.xpathToFormatToGetRemoveUserButton")
    String xpathToFormatToGetRemoveUserButton();
}

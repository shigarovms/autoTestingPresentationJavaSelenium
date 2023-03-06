package m.shigarov;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import m.shigarov.baseFramework.models.User;
import m.shigarov.baseFramework.models.pageObjects.FillableForm;
import m.shigarov.baseFramework.models.pageObjects.HomePage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static m.shigarov.baseFramework.config.ConfigurationManager.configuration;
import static m.shigarov.baseFramework.driver.WebDriverUtils.closeLastOpenedTab;
import static m.shigarov.baseFramework.driver.WebDriverUtils.moveToLastOpenedTab;
import static m.shigarov.baseFramework.fileTools.JsonUtility.jsonToListOfObjects;
import static org.testng.Assert.*;

public class DemoqaTestCase extends BaseWebTest {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final HomePage homePage = new HomePage();
    Lorem lorem = LoremIpsum.getInstance();

    @Test
    void alertsTest() {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        logger.info("\u001B[42m" + String.format("Test %s in progress:\n", methodName) + "\u001B[0m");

        driver.navigate().to(homePage.URL);
        assertTrue(homePage.confirmItIsShown());
        logger.info("home page is opened");

        homePage.alertsFrameWindowsCard.clickTheElement();
        homePage.alertsItemFromList.clickTheElement();
        assertTrue(homePage.alertsForm.confirmItIsShown());
        logger.info("alerts form has appeared");

        homePage.buttonToSeeAlert.clickTheElement();
        assertTrue(homePage.alertWithTextIsVisible("You clicked a button"));
        logger.info("alert box with 'You clicked a button' text has appeared");

        homePage.acceptTheAlert();
        assertTrue(homePage.noAlertsAreVisible());
        logger.info("alert box with 'You clicked a button' text has disappeared");

        homePage.buttonToSeeConfirmationAlert.clickTheElement();
        assertTrue(homePage.alertWithTextIsVisible("Do you confirm action?"));
        logger.info("alert box with 'Do you confirm action?' text has appeared");

        homePage.acceptTheAlert();
        assertTrue(homePage.noAlertsAreVisible());
        logger.info("alert box with 'Do you confirm action?' text has disappeared");

        homePage.buttonToSeePromtAlert.clickTheElement();
        assertTrue(homePage.alertWithTextIsVisible("Please enter your name"));
        logger.info("alert box with 'Please enter your name' text has appeared");

        String randomText = lorem.getWords(6,9);
        homePage.fillTheAlertWithText(randomText);
        homePage.acceptTheAlert();
        assertEquals(randomText, homePage.getPromtResultText());
        logger.info(String.format("the text on the page '%s' is the same as the given one '%s'",
                                        randomText, homePage.getPromtResultText()));
    }

    @Test
    void iFrame() {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        logger.info("\u001B[42m" + String.format("Test %s in progress:\n", methodName) + "\u001B[0m");

        // Navigate to the main page
        driver.navigate().to(homePage.URL);
        assertTrue(homePage.confirmItIsShown());
        logger.info("home page is opened");

        // Click on Alerts, Frame & Windows button
        homePage.alertsFrameWindowsCard.clickTheElement();
        // In the menu click the Nested Frame button
        homePage.nestedFramesItemFromList.clickTheElement();
        assertTrue(homePage.nestedFramesForm.confirmItIsShown());
        logger.info("Page with Nested Frames forms is opened");
        assertTrue(homePage.parentFrame.uniqueElementIsInside(false)
                && homePage.childFrame.uniqueElementIsInside(true));
        logger.info("'Parent frame' & 'Child Frame' present on page");

        // Select frames option in the left menu
        homePage.framesItemFromList.clickTheElement();
        assertEquals(homePage.upperFrame.getMessageFromInside(), homePage.lowerFrame.getMessageFromInside());
        logger.info("Messages from upper and lower frames are equal");
    }

    @DataProvider(name = "testTables")
    public Object[][] createData1() {
        List<User> usersToAddInRegForm = jsonToListOfObjects("src/test/resources/usersToAdd.json");
        return new Object[][] {
                {usersToAddInRegForm.get(0)}, {usersToAddInRegForm.get(1)}
        };
    }

    @Test(dataProvider = "testTables")
    void Tables(User userFromJson) {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        logger.info("\u001B[42m" + String.format("Test %s in progress:\n", methodName) + "\u001B[0m");

        // Navigate to the main page
        driver.navigate().to(homePage.URL);
        assertTrue(homePage.confirmItIsShown());
        logger.info("home page is opened");
        // Click Elements -> Click Web Tables button
        homePage.elementsCard.clickTheElement();
        homePage.webTablesItemFromList.clickTheElement();
        assertTrue(homePage.webTableForm.confirmItIsShown());
        // Click add button
        homePage.addButton.clickTheElement();

        FillableForm registrationForm = new FillableForm(
                configuration().registrationForm(), "registrationForm");

        assertTrue(registrationForm.confirmItIsShown());
        logger.info("registration form has appeared on the page");

        registrationForm.fillWithUserDataAndSubmit(userFromJson);
        assertTrue(homePage.checkIfUserInTable(userFromJson));
        logger.info("user have been successfully added");
        homePage.removeUserFromTable(userFromJson);
        assertFalse(homePage.checkIfUserInTable(userFromJson));
        logger.info("user have been successfully deleted");

    }

    @Test
    void Handles() {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        logger.info("\u001B[42m" + String.format("Test %s in progress:\n", methodName) + "\u001B[0m");

        // Navigate to the main page
        driver.navigate().to(homePage.URL);
        assertTrue(homePage.confirmItIsShown());
        logger.info("home page is opened");

        // Click on the Alerts, Frame & Windows button
        homePage.alertsFrameWindowsCard.clickTheElement();
        homePage.browserWindowsItemFromList.clickTheElement();
        assertTrue(homePage.newTabButton.isVisible());
        logger.info("browser windows form is opened");

        // Click on new tab
        int windowsBeforeClicking = driver.getWindowHandles().size();
        homePage.newTabButton.clickTheElement();
        assertEquals(windowsBeforeClicking, driver.getWindowHandles().size()-1);
        logger.info("new tab with sample page is opened");
        // Close current tab
        closeLastOpenedTab();
        assertTrue(homePage.newTabButton.isVisible());
        logger.info("browser windows form is opened");
        // Click Elements -> Click Links
        homePage.elementsMenuItem.clickTheElement();
        homePage.linksItemFromList.clickTheElement();
        assertTrue(homePage.homeSimpleLink.isVisible());
        logger.info("links form is opened");
        // Click Home link
        homePage.homeSimpleLink.clickTheElement();
        moveToLastOpenedTab();
        assertTrue(homePage.confirmItIsShown());
        logger.info("new tab whit home page is opened");
        // Resume to previous tab
        closeLastOpenedTab();
        assertTrue(homePage.homeSimpleLink.isVisible());
        logger.info("links form is opened");
    }
}

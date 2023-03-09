package m.shigarov;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import m.shigarov.baseFramework.models.User;
import m.shigarov.baseFramework.models.pageObjects.FillableForm;
import m.shigarov.baseFramework.models.pageObjects.WebAppPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static m.shigarov.baseFramework.config.ConfigurationManager.conf;
import static m.shigarov.baseFramework.driver.WebDriverUtils.closeLastOpenedTab;
import static m.shigarov.baseFramework.driver.WebDriverUtils.moveToLastOpenedTab;
import static m.shigarov.baseFramework.fileTools.JsonUtility.jsonToListOfObjects;
import static org.testng.Assert.*;

public class DemoqaTestCase extends BaseWebTest {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final WebAppPage webAppPage = new WebAppPage();
    // Lorem generates loremipsum random text
    Lorem lorem = LoremIpsum.getInstance();

    @Test
    void alertsTest() {
        //Let's make logger title with name of the test method on green font
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("\u001B[42m" + String.format("Test %s in progress:\n", methodName) + "\u001B[0m");

        driver.navigate().to(webAppPage.URL);
        assertTrue(webAppPage.confirmItIsShown());
        logger.info("home page is opened");

        webAppPage.alertsFrameWindowsCard.clickTheElement();
        webAppPage.alertsItemFromList.clickTheElement();
        assertTrue(webAppPage.alertsForm.confirmItIsShown());
        logger.info("alerts form has appeared");

        webAppPage.buttonToSeeAlert.clickTheElement();
        assertTrue(webAppPage.alertWithTextIsVisible("You clicked a button"));
        logger.info("alert box with 'You clicked a button' text has appeared");

        webAppPage.acceptTheAlert();
        assertTrue(webAppPage.noAlertsAreVisible());
        logger.info("alert box with 'You clicked a button' text has disappeared");

        webAppPage.buttonToSeeConfirmationAlert.clickTheElement();
        assertTrue(webAppPage.alertWithTextIsVisible("Do you confirm action?"));
        logger.info("alert box with 'Do you confirm action?' text has appeared");

        webAppPage.acceptTheAlert();
        assertTrue(webAppPage.noAlertsAreVisible());
        logger.info("alert box with 'Do you confirm action?' text has disappeared");

        webAppPage.buttonToSeePromtAlert.clickTheElement();
        assertTrue(webAppPage.alertWithTextIsVisible("Please enter your name"));
        logger.info("alert box with 'Please enter your name' text has appeared");

        String randomText = lorem.getWords(6, 9);
        webAppPage.fillTheAlertWithText(randomText);
        webAppPage.acceptTheAlert();
        String textFromPromt = webAppPage.getPromtResultTextWithout("You entered ");
        assertEquals(randomText, textFromPromt);
        logger.info(String.format(
                "the text on the page '%s' is the same as the given one '%s'", randomText, textFromPromt));
    }

    @Test
    void iFrame() {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("\u001B[42m" + String.format("Test %s in progress:\n", methodName) + "\u001B[0m");

        // Navigate to the main page
        driver.navigate().to(webAppPage.URL);
        assertTrue(webAppPage.confirmItIsShown());
        logger.info("home page is opened");

        // Click on Alerts, Frame & Windows button
        webAppPage.alertsFrameWindowsCard.clickTheElement();
        // In the menu click the Nested Frame button
        webAppPage.nestedFramesItemFromList.clickTheElement();
        assertTrue(webAppPage.nestedFramesForm.confirmItIsShown());
        logger.info("Page with Nested Frames forms is opened");
        assertTrue(webAppPage.parentFrame.uniqueElementIsInside(false)
                && webAppPage.childFrame.uniqueElementIsInside(true));
        logger.info("'Parent frame' & 'Child Frame' present on page");

        // Select frames option in the left menu
        webAppPage.framesItemFromList.clickTheElement();
        assertEquals(webAppPage.upperFrame.getMessageFromInside(), webAppPage.lowerFrame.getMessageFromInside());
        logger.info("Messages from upper and lower frames are equal");
    }

    @DataProvider(name = "testTables")
    public Object[][] createDataForTableTest() {
        List<User> usersToAddInRegForm = jsonToListOfObjects("src/test/resources/usersToAddTestData.json");

        Object[][] usersForDP = new Object[usersToAddInRegForm.size()][1];
        for (int i = 0; i < usersToAddInRegForm.size(); i++) {
            usersForDP[i][0] = usersToAddInRegForm.get(i);
        }
        return usersForDP;
    }

    @Test(dataProvider = "testTables")
    void Tables(User userFromDP) {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("\u001B[42m" + String.format("Test %s in progress:\n", methodName) + "\u001B[0m");

        // Navigate to the main page
        driver.navigate().to(webAppPage.URL);
        assertTrue(webAppPage.confirmItIsShown());
        logger.info("home page is opened");
        // Click Elements -> Click Web Tables button
        webAppPage.elementsCard.clickTheElement();
        webAppPage.webTablesItemFromList.clickTheElement();
        assertTrue(webAppPage.webTableOfUsers.confirmItIsShown());
        // Click add button
        webAppPage.addButton.clickTheElement();

        FillableForm registrationForm = new FillableForm(
                conf().registrationForm(), "registrationForm");

        assertTrue(registrationForm.confirmItIsShown());
        logger.info("registration form has appeared on the page");

        registrationForm.fillWithUserDataAndSubmit(userFromDP);
        assertTrue(webAppPage.webTableOfUsers.checkIfUserInTable(userFromDP));
        logger.info("user have been successfully added");
        webAppPage.webTableOfUsers.removeUserFromTable(userFromDP);
        assertFalse(webAppPage.webTableOfUsers.checkIfUserInTable(userFromDP));
        logger.info("user have been successfully deleted");

    }

    @Test
    void Handles() {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("\u001B[42m" + String.format("Test %s in progress:\n", methodName) + "\u001B[0m");

        // Navigate to the main page
        driver.navigate().to(webAppPage.URL);
        assertTrue(webAppPage.confirmItIsShown());
        logger.info("home page is opened");
        // Click on the Alerts, Frame & Windows button
        webAppPage.alertsFrameWindowsCard.clickTheElement();
        webAppPage.browserWindowsItemFromList.clickTheElement();
        assertTrue(webAppPage.newTabButton.isVisible());
        logger.info("browser windows form is opened");
        // Click on new tab
        int windowsBeforeClicking = driver.getWindowHandles().size();
        webAppPage.newTabButton.clickTheElement();
        assertEquals(windowsBeforeClicking, driver.getWindowHandles().size() - 1);
        logger.info("new tab with sample page is opened");
        // Close current tab
        closeLastOpenedTab();
        assertTrue(webAppPage.newTabButton.isVisible());
        logger.info("browser windows form is opened");
        // Click Elements -> Click Links
        webAppPage.elementsMenuItem.clickTheElement();
        webAppPage.linksItemFromList.clickTheElement();
        assertTrue(webAppPage.homeSimpleLink.isVisible());
        logger.info("links form is opened");
        // Click Home link
        webAppPage.homeSimpleLink.clickTheElement();
        moveToLastOpenedTab();
        assertTrue(webAppPage.confirmItIsShown());
        logger.info("new tab whit home page is opened");
        // Resume to previous tab
        closeLastOpenedTab();
        assertTrue(webAppPage.homeSimpleLink.isVisible());
        logger.info("links form is opened");
    }
}

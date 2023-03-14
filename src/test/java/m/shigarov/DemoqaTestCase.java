package m.shigarov;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import m.shigarov.baseFramework.models.User;
import m.shigarov.baseFramework.models.pageObjects.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static m.shigarov.baseFramework.config.ConfigurationManager.conf;
import static m.shigarov.baseFramework.driver.BrowserUtils.*;
import static m.shigarov.baseFramework.fileTools.JsonUtility.jsonToListOfObjects;
import static org.testng.Assert.*;

public class DemoqaTestCase extends BaseWebTest {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final HomePage homePage = new HomePage();
    // Lorem generates loremipsum random text
    Lorem lorem = LoremIpsum.getInstance();

    @Test
    void alertsTest() {
        //Let's make logger title with name of the test method on green font
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        logger.info("\u001B[42m" + String.format("Test %s in progress:\n", methodName) + "\u001B[0m");

        navigateToHomePage();
        assertTrue(homePage.isVisible());
        logger.info("home page is opened");

        homePage.clickTheAlertsFrameWindowsCard();
        homePage.sideMenu.clickAlertsItemFromList();
        AlertsPage alertsPage = new AlertsPage();
        assertTrue(alertsPage.isVisible());
        logger.info("alerts form has appeared");

        alertsPage.clickButtonToSeeAlert();
        assertTrue(isAlertWithTextVisible("You clicked a button"));
        logger.info("alert box with 'You clicked a button' text has appeared");

        acceptTheAlert();
        assertTrue(areNoAlertsVisible());
        logger.info("alert box with 'You clicked a button' text has disappeared");

        alertsPage.clickButtonToSeeConfirmationAlert();
        assertTrue(isAlertWithTextVisible("Do you confirm action?"));
        logger.info("alert box with 'Do you confirm action?' text has appeared");

        acceptTheAlert();
        assertTrue(areNoAlertsVisible());
        logger.info("alert box with 'Do you confirm action?' text has disappeared");

        alertsPage.clickButtonToSeePromtAlert();
        assertTrue(isAlertWithTextVisible("Please enter your name"));
        logger.info("alert box with 'Please enter your name' text has appeared");

        String randomText = lorem.getWords(6, 9);
        fillTheAlertWithText(randomText);
        acceptTheAlert();
        String textFromPromt = alertsPage.getPromtResultTextWithout("You entered ");
        assertEquals(randomText, textFromPromt);
        logger.info("the text on the page is the same as the putted in alert");
    }

    @Test
    void iFrame() {
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        logger.info("\u001B[42m" + String.format("Test %s in progress:\n", methodName) + "\u001B[0m");

        navigateToHomePage();
        assertTrue(homePage.isVisible());
        logger.info("home page is opened");

        homePage.clickTheAlertsFrameWindowsCard();
        homePage.sideMenu.clickNestedFramesItemFromList();
        IFramePage iFramePage = new IFramePage();
        assertTrue(iFramePage.isVisible());
        logger.info("Page with Nested Frames forms is opened");

        assertTrue(iFramePage.isParentFrameVisible(false));
        assertTrue(iFramePage.isChildFrameVisible(true));
        logger.info("'Parent frame' & 'Child Frame' present on page");

        iFramePage.sideMenu.clickFramesItemFromList();
        assertEquals(iFramePage.getMessageFromUpperFrame(), iFramePage.getMessageFromLowerFrame());
        logger.info("Messages from upper and lower frames are equal");
    }
    @DataProvider(name = "testTables")
    public Object[][] createDataForTableTest() {
        List<User> usersToAddInRegForm = jsonToListOfObjects(conf().usersTestDataPath());
        Object[][] usersForDP = new Object[usersToAddInRegForm.size()][1];
        for (int i = 0; i < usersToAddInRegForm.size(); i++) {
            usersForDP[i][0] = usersToAddInRegForm.get(i);
        }
        return usersForDP;
    }
    @Test(dataProvider = "testTables")
    void Tables(User userFromDP) {
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        logger.info("\u001B[42m" + String.format("Test %s in progress:\n", methodName) + "\u001B[0m");

        navigateToHomePage();
        assertTrue(homePage.isVisible());
        logger.info("home page is opened");

        homePage.clickTheElementsCard();
        homePage.sideMenu.clickWebTablesItemFromList();
        WebTablesPage webTablesPage = new WebTablesPage();
        assertTrue(webTablesPage.isVisible());
        logger.info("web tables page is opened");

        webTablesPage.clickAddButton();
        assertTrue(webTablesPage.isRegistrationFormVisible());
        logger.info("registration form has appeared on the page");

        webTablesPage.addUserToTheWebTable(userFromDP);
        assertTrue(webTablesPage.isTheUserInTable(userFromDP));
        logger.info("user have been successfully added");

        webTablesPage.removeUserFromTable(userFromDP);
        assertFalse(webTablesPage.isTheUserInTable(userFromDP));
        logger.info("user have been successfully deleted");

    }

    @Test
    void Handles() {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("\u001B[42m" + String.format("Test %s in progress:\n", methodName) + "\u001B[0m");

        navigateToHomePage();
        assertTrue(homePage.isVisible());
        logger.info("home page is opened");

        homePage.clickTheAlertsFrameWindowsCard();
        homePage.sideMenu.clickBrowserWindowsItemFromList();

        BrowserWindowsPage browserWindowsPage = new BrowserWindowsPage();
        assertTrue(browserWindowsPage.isVisible());
        logger.info("browser windows form is opened");

        int numberOfTabsBeforeClicking = getNumberOfTabs();
        browserWindowsPage.clickNewTabButton();
        assertEquals(numberOfTabsBeforeClicking, getNumberOfTabs() - 1);
        logger.info("new tab with sample page is opened");

        closeLastOpenedTab();
        assertTrue(browserWindowsPage.isVisible());
        logger.info("browser windows page is opened");

        browserWindowsPage.sideMenu.clickElementsMenuItem();
        browserWindowsPage.sideMenu.clickLinksItemFromList();
        LinksPage linksPage = new LinksPage();
        assertTrue(linksPage.isVisible());
        logger.info("links page is opened");

        linksPage.clickHomeSimpleLinkButton();
        moveToLastOpenedTab();
        assertTrue(homePage.isVisible());
        logger.info("new tab whit home page is opened");

        closeLastOpenedTab();
        assertTrue(linksPage.isVisible());
        logger.info("links form is opened");
    }
}

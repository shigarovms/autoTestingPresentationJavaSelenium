package m.shigarov;

import m.shigarov.baseFramework.config.Configuration;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.logging.Logger;

import static m.shigarov.baseFramework.config.ConfigurationManager.conf;
import static m.shigarov.baseFramework.driver.WebDriverUtils.getDriver;
import static m.shigarov.baseFramework.driver.WebDriverUtils.setWebdriver;

public class BaseWebTest {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    protected WebDriver driver;
    protected Configuration configuration;

    @BeforeMethod
    public void preCondition() {
        configuration = conf();
        driver = getDriver();

    }

    @AfterMethod
    public void postCondition() {
        driver.quit();
        logger.warning("webdriver has been finished");
        setWebdriver(null);
    }
}

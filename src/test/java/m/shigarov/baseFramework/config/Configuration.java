package m.shigarov.baseFramework.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:general.properties",
        "classpath:grid.properties"})

public interface Configuration extends Config {

    @Key("browser")
    String browser();

    @Key("waitDurSec")
    int waitDur();

    @Key("pollingSleepDurMilSec")
    int pollingSleepDur();

    @Key("url.homePage")
    String homePageUrl();

    @Key("firefox.options")
    String[] firefoxOptions();

    @Key("chrome.options")
    String[] chromeOptions();

    @Key("usersTestDataPath")
    String usersTestDataPath();
}

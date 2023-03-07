package m.shigarov.baseFramework.config;

import org.aeonbits.owner.ConfigCache;

public class ConfigurationManager {

    private ConfigurationManager() {
    }

    public static Configuration conf() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}

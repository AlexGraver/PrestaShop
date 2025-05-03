package core.configs;
import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:default.properties",
        "classpath:${env}.properties"
})


public interface Configs extends Config{

    @Key("system.browser")
    String browser();

    @Key("explicit.wait")
    int explicitWait();

    @Key("implicit.wait")
    int implicitWait();

    @Key("polling.fluent")
    int fluentPolling();

    @Key("timeout.fluent")
    int fluentTimeout();

}


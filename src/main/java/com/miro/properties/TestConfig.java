package com.miro.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources({"classpath:test.properties"})
public interface TestConfig extends Config {

        TestConfig BASE_CONFIG = ConfigFactory.create(TestConfig.class, System.getenv(), System.getProperties());

        @Key("timeout")
        long timeout();

        @Key("base.url")
        @DefaultValue("https://miro.com/signup/")
        String baseUrl();

        @Key("pageTimeout")
        long pageTimeout();

        @Key("remote.driver")
        boolean remoteDriver();

        @Key("grid.url")
        boolean gridUrl();

        @Key("grid.port")
        boolean gridPort();
}

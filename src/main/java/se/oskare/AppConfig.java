package se.oskare;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

import java.net.URI;

@ConfigMapping(prefix = "app")
public interface AppConfig {
    @WithName("space-bots.defaultFleetId")
    String defaultFleetId();
}
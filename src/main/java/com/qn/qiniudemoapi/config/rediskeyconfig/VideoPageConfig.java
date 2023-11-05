package com.qn.qiniudemoapi.config.rediskeyconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "video-page")
@Getter
@Setter
public class VideoPageConfig {
    private int expirationTimeMinutes;
    private String keyPrefix;
}
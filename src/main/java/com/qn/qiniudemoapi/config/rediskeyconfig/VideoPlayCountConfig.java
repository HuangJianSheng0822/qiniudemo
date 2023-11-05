package com.qn.qiniudemoapi.config.rediskeyconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "video-play-count")
@Getter
@Setter
public class VideoPlayCountConfig {
    private int expirationTimeMinutes;
    private String keyPrefix;
}
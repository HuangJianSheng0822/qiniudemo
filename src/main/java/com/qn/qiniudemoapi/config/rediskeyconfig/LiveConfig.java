package com.qn.qiniudemoapi.config.rediskeyconfig;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "live")
@Getter
public class LiveConfig {
    public static String peopleListKeyPrefix;
    public static String peopleCountKey;

    public static String liveBarrageKeyPrefix;
    public static String liveBarrageCountKey;
    public static int expirationTimeMinutes;

    public  void setPeopleListKeyPrefix(String peopleListKeyPrefix) {
        LiveConfig.peopleListKeyPrefix = peopleListKeyPrefix;
    }

    public void setPeopleCountKey(String peopleCountKey) {
        LiveConfig.peopleCountKey = peopleCountKey;
    }

    public void setLiveBarrageKeyPrefix(String liveBarrageKeyPrefix) {
        LiveConfig.liveBarrageKeyPrefix = liveBarrageKeyPrefix;
    }

    public void setLiveBarrageCountKey(String liveBarrageCountKey) {
        LiveConfig.liveBarrageCountKey = liveBarrageCountKey;
    }

    public void setExpirationTimeMinutes(int expirationTimeMinutes) {
        LiveConfig.expirationTimeMinutes = expirationTimeMinutes;
    }
}

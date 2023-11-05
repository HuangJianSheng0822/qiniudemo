package com.qn.qiniudemoapi.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CollectVideoVo {
    private String id;
    private String collectId;
    private String videoId;
    private String title;
    private String userId;
    private String coverUrl;
    private Date created;
    private long playCount;
}

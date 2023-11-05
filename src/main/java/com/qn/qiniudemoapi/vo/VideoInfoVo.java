package com.qn.qiniudemoapi.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * 视频信息
 */
@Getter
@Setter
@NoArgsConstructor
public class VideoInfoVo {
    private String id;
    private String title;
    private String playUrl;
    private String type;
    private String tags;
    private String description;
    private Date created;
    private long playback;
}

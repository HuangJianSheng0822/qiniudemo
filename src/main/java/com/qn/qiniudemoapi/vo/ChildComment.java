package com.qn.qiniudemoapi.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ChildComment {
    private String id;
    private String userId;
    private String headImg;
    private String userName;
    private String content;
    private Date created;
    private String toUserId;
    private String toUserName;
    private int likeCount;
}

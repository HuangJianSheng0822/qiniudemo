package com.qn.qiniudemoapi.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class RootComment {
    private String id;
    private String userId;
    private String headImg;
    private String userName;
    private String content;
    private Date created;
    private int childCommentCount;
    private List<ChildComment> childComments;
    private int likeCount;
}

package com.qn.qiniudemoapi.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CoverVo {
    private String id;
    private String title;
    private String userId;
    private String userName;
    private String headImg;
    private String userDesc;
    private String coverUrl;
    @JsonFormat(pattern = "yy-MM-dd")
    private Date created;
}

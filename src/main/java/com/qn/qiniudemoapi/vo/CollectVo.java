package com.qn.qiniudemoapi.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CollectVo {
    private String id;
    private String name;
    private String cover;
    private String desc;
    private Date created;
}

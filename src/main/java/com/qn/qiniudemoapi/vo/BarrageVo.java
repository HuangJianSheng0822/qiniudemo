package com.qn.qiniudemoapi.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BarrageVo {
    private String schedule;
    private String content;
    private Date created;
}

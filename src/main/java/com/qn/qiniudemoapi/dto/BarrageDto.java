package com.qn.qiniudemoapi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BarrageDto {
    private String userId;
    private String videoId;
    private String content;
    private String schedule;
}

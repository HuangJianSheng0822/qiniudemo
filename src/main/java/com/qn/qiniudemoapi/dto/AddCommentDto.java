package com.qn.qiniudemoapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AddCommentDto {
    private String content;
    private String videoId;
    private String rootId;
    private String toId;
    private String toName;
}

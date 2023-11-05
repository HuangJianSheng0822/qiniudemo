package com.qn.qiniudemoapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ContentDto {
    private String videoId;
    private String coverUrl;
    private String title;
    private String typeId;
    private String description;
    private List<String> tags;
}

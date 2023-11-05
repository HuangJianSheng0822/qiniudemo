package com.qn.qiniudemoapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AddCollect {
    private String name;
    private String cover;
    private String desc;
    private boolean open;
}

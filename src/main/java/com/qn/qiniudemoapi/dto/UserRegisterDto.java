package com.qn.qiniudemoapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户注册接受
 */
@Data
@NoArgsConstructor
public class UserRegisterDto {
    private String name;
    private String email;
    private String pwd;
    private String code;
}

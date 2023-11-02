package com.qn.qiniudemoapi.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装响应数据结构
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDataStructure {
    private int code;
    private String msg;
    private Object data;

    //ok
    public static final int Code_OK=200;
    public static final String Msg_OK="OK";
    //权限不足
    public static final int Code_Auth=401;
    public static final String Msg_Auth="权限异常";


}

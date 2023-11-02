package com.qn.qiniudemoapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qn.qiniudemoapi.dto.UpdateUserInfoDto;
import com.qn.qiniudemoapi.dto.UserLoginDto;
import com.qn.qiniudemoapi.dto.UserRegisterDto;
import com.qn.qiniudemoapi.pojo.User;
import com.qn.qiniudemoapi.util.ResponseDataStructure;


public interface UserService extends IService<User> {
    /**
     * 登录
     * @param userLoginDto 用户
     * @return 结果
     */
    ResponseDataStructure login(UserLoginDto userLoginDto);

    /**
     * 注册
     * @param userRegisterDto 用户
     * @return 结果
     */
    ResponseDataStructure register(UserRegisterDto userRegisterDto);

    /**
     * 获取用户信息
     * @param token token
     * @return 信息
     */
    ResponseDataStructure getUserInfoByToken(String token);

    /**
     * 获取用户信息
     * @param id id
     * @return 结果
     */
    ResponseDataStructure getUserInfoById(String id);


    /**
     * 更新用户昵称，简介
     * @param userInfo 用户信息
     * @return r
     */
    ResponseDataStructure updateUserInfo(String token, UpdateUserInfoDto userInfo);


}

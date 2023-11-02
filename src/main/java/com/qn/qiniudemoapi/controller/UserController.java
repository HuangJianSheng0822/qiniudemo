package com.qn.qiniudemoapi.controller;

import com.qn.qiniudemoapi.dto.UpdateUserInfoDto;
import com.qn.qiniudemoapi.dto.UserLoginDto;
import com.qn.qiniudemoapi.dto.UserRegisterDto;
import com.qn.qiniudemoapi.service.UserService;
import com.qn.qiniudemoapi.util.ResponseDataStructure;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = "用户服务")
@RequestMapping("/user")
public class UserController {


    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录
     * @param userLoginDto 用户
     * @return 结果
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseDataStructure login(@RequestBody UserLoginDto userLoginDto){
        return userService.login(userLoginDto);
    }


    /**
     * 用户注册
     * @param userRegisterDto 用户
     * @return 结果
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResponseDataStructure register(@RequestBody UserRegisterDto userRegisterDto){
        return userService.register(userRegisterDto);
    }

    /**
     * 通过token获取用户信息
     * @param token token
     * @return 信息
     */
    @ApiOperation(value = "通过token获取用户信息")
    @GetMapping("/")
    public ResponseDataStructure getUserInfoByToken(@RequestHeader("Authorization") String token) {
        return userService.getUserInfoByToken(token);
    }

    /**
     * 通过id获取用户信息
     * @param id id
     * @return 用户
     */
    @ApiOperation(value = "通过id获取用户信息")
    @GetMapping("/{id}")
    public ResponseDataStructure getUserInfoById(@PathVariable String id) {
        return userService.getUserInfoById(id);
    }


    /**
     * 更新用户昵称和简介
     * @param token 用户身份令牌，用于验证用户身份
     * @param userInfo 包含要更新的昵称和简介的DTO对象
     * @return ResponseDataStructure
     */
    @ApiOperation("更新用户昵称，简介")
    @PostMapping("/update")
    public ResponseDataStructure updateUserInfo(@RequestHeader("Authorization") String token,
                                                @RequestBody UpdateUserInfoDto userInfo) {
        return userService.updateUserInfo(token,userInfo);
    }
}

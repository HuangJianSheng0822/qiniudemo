package com.qn.qiniudemoapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qn.qiniudemoapi.dto.UpdateUserInfoDto;
import com.qn.qiniudemoapi.dto.UserLoginDto;
import com.qn.qiniudemoapi.dto.UserRegisterDto;
import com.qn.qiniudemoapi.mapper.UserMapper;
import com.qn.qiniudemoapi.pojo.User;
import com.qn.qiniudemoapi.service.UserService;
import com.qn.qiniudemoapi.util.JwtUtil;
import com.qn.qiniudemoapi.util.ResponseDataStructure;
import com.qn.qiniudemoapi.vo.UserInfoVO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String ,Object> redisTemplate;


    /**
     * 登录
     * @param userLoginDto 用户
     * @return 结果
     */
    @Override
    public ResponseDataStructure login(UserLoginDto userLoginDto) {
        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 先查询用户是否存在
        QueryWrapper<User> email = queryWrapper.eq("email", userLoginDto.getEmail());
        User user = userMapper.selectOne(queryWrapper);
        if (user==null){
            map.put("is",false);
            map.put("token",null);
            return new ResponseDataStructure(200,"登录失败,用户不存在",map);
        }
        // 判断密码
        if (BCrypt.checkpw(userLoginDto.getPwd(), user.getPwd())){
            map.put("is",false);
            map.put("token",null);
            return new ResponseDataStructure(200,"登录失败，密码错误",map);
        }

        map.put("is",true);
        map.put("token",JwtUtil.encryption(user.getId()));
        return new ResponseDataStructure(200,"登录成功",map);
    }

    /**
     * 注册
     * @param userRegisterDto 用户
     * @return 结果
     */
    @Override
    public ResponseDataStructure register(UserRegisterDto userRegisterDto) {
        String code = userRegisterDto.getCode();
        String email = userRegisterDto.getEmail();
        HashMap<String, Object> map = new HashMap<>();
        //验证码
        String redisCode = (String) redisTemplate.opsForValue().get(email);
        if (!code.equals(redisCode)){
            map.put("is",false);
            map.put("token",null);
            return new ResponseDataStructure(200,"验证码错误",map);
        }

        //是否已注册,邮箱唯一
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("email", email);
        User user = userMapper.selectOne(userQueryWrapper);
        if (user!=null){
            map.put("is",false);
            map.put("token",null);
            return new ResponseDataStructure(200,"邮箱已注册",map);
        }

        //添加用户
        User registerUser = new User();
        registerUser.setName(userRegisterDto.getName());
        registerUser.setPwd(BCrypt.hashpw(registerUser.getPwd(),BCrypt.gensalt()));
        registerUser.setEmail(email);
        int insert = userMapper.insert(registerUser);

        //获取已添加用户id
        QueryWrapper<User> hasUserWrap = userQueryWrapper.eq("email", email);
        User hasUser = userMapper.selectOne(hasUserWrap);
        map.put("is",insert>0);
        map.put("token",insert>0?JwtUtil.encryption(hasUser.getId()):null);
        return new ResponseDataStructure(200,insert>0?"注册成功":"注册失败",map);
    }

    /**
     * 获取用户信息
     * @param token token
     * @return 信息
     */
    @Override
    public ResponseDataStructure getUserInfoByToken(String token) {
        String id = JwtUtil.decrypt(token);
        UserInfoVO userInfo = getUserInfo(id);
        return new ResponseDataStructure(200,"",userInfo);
    }

    /**
     * 获取用户信息
     * @param id id
     * @return 结果
     */
    @Override
    public ResponseDataStructure getUserInfoById(String id) {
        UserInfoVO userInfo = getUserInfo(id);
        return new ResponseDataStructure(200,"",userInfo);
    }

    /**
     * 更新用户昵称，简介
     * @param userInfo 用户信息
     * @return r
     */
    @Override
    public ResponseDataStructure updateUserInfo(String token,UpdateUserInfoDto userInfo) {
        String userId = JwtUtil.decrypt(token);
        User user = new User();
        user.setId(userId);
        if (userInfo.getName()!=null){
            user.setName(userInfo.getName());
        }
        if (userInfo.getDesc()!=null){
            user.setDesc(userInfo.getDesc());
        }
        if (userInfo.getHeadImg()!=null){
            user.setHeadImg(userInfo.getHeadImg());
        }

        int i = userMapper.updateById(user);
        return new ResponseDataStructure(200,i>0?"更新成功":"更新失败",i>0);
    }

    /**
     * 通过id获取用户信息
     * @param id id
     * @return 信息
     */
    public UserInfoVO getUserInfo(String id) {
        User user = userMapper.selectById(id);
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setId(id);
        userInfoVO.setName(user.getName());
        userInfoVO.setHeadImg(user.getHeadImg());
        userInfoVO.setDesc(user.getDesc());
        return userInfoVO;
    }
}

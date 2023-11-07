package com.qn.qiniudemoapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qn.qiniudemoapi.dto.ApplyLiveDto;
import com.qn.qiniudemoapi.mapper.LiveUserMapper;
import com.qn.qiniudemoapi.mapper.UserMapper;
import com.qn.qiniudemoapi.pojo.LiveUser;
import com.qn.qiniudemoapi.pojo.User;
import com.qn.qiniudemoapi.service.EmailService;
import com.qn.qiniudemoapi.service.LiveUserService;
import com.qn.qiniudemoapi.util.JwtUtil;
import com.qn.qiniudemoapi.util.ResponseDataStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LiveUserServiceImpl extends ServiceImpl<LiveUserMapper, LiveUser> implements LiveUserService {

    @Autowired
    private LiveUserMapper liveUserMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmailService emailService;
    @Override
    public int liveAuth(String userId,String auth) {
        QueryWrapper<LiveUser> qw = new QueryWrapper<>();
        qw.eq("user_id",userId).eq("auth",auth).eq("state",1);
        boolean exists = liveUserMapper.exists(qw);
        return exists?200:301;
    }

    @Override
    public ResponseDataStructure applyLive(String token, ApplyLiveDto applyLiveDto) {
        LiveUser liveUser = new LiveUser();
        String userId = JwtUtil.decrypt(token);
        liveUser.setUserId(userId);
        liveUser.setTypeId(applyLiveDto.getTypeId());
        liveUser.setState(0);
        liveUser.setApplyDesc(applyLiveDto.getApplyDesc());
        int insert = liveUserMapper.insert(liveUser);
        return new ResponseDataStructure(200,insert>0?"申请成功":"申请失败",insert>0);
    }

    @Override
    public ResponseDataStructure liveEmail(String token) {
        String userId = JwtUtil.decrypt(token);
        User user = userMapper.selectById(userId);
        boolean b = emailService.sendSimpleCode(user.getEmail());
        return new ResponseDataStructure(200,b?"发送成功":"发送失败",b);
    }

    @Override
    public ResponseDataStructure canLive(String token) {
        QueryWrapper<LiveUser> qw = new QueryWrapper<>();
        String userId = JwtUtil.decrypt(token);
        qw.eq("user_id",userId).eq("state",1);
        boolean exists = liveUserMapper.exists(qw);
        return new ResponseDataStructure(200,exists?"ok":"无法直播",exists);
    }
}

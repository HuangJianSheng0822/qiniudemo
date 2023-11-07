package com.qn.qiniudemoapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qn.qiniudemoapi.dto.ApplyLiveDto;
import com.qn.qiniudemoapi.pojo.LiveUser;
import com.qn.qiniudemoapi.util.ResponseDataStructure;

public interface LiveUserService extends IService<LiveUser> {
    int liveAuth(String userId,String auth);

    ResponseDataStructure applyLive(String token, ApplyLiveDto applyLiveDto);

    ResponseDataStructure liveEmail(String token);

    ResponseDataStructure canLive(String token);
}

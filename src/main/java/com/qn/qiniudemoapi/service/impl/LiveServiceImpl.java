package com.qn.qiniudemoapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qn.qiniudemoapi.config.rediskeyconfig.LiveConfig;
import com.qn.qiniudemoapi.mapper.LiveMapper;
import com.qn.qiniudemoapi.pojo.Live;
import com.qn.qiniudemoapi.service.LiveService;
import com.qn.qiniudemoapi.util.ResponseDataStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class LiveServiceImpl extends ServiceImpl<LiveMapper, Live> implements LiveService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    /**
     * 只查10个
     * @return r
     */
    @Override
    public ResponseDataStructure getLiveActiveList() {
        // 从redis获取当前直播弹幕数前20个与在线人数前20
        //重复

        return null;
    }

    /**
     * 有新用户加入直播间
     *
     * @param liveId 房间号
     * @param ip     ip
     * @return r
     */
    @Override
    public ResponseDataStructure hasNewPeople(String liveId, String ip) {
        //将用户ip加入
        Long add = redisTemplate.opsForSet().add(LiveConfig.peopleListKeyPrefix + liveId, ip);
        //更新在线用户数据
        redisTemplate.opsForZSet().add(LiveConfig.peopleCountKey,liveId,
                redisTemplate.opsForSet().size(LiveConfig.peopleListKeyPrefix + liveId));

        return new ResponseDataStructure(200,"",add>0);
    }

    /**
     * 获取在线用户数量
     *
     * @param liveId id
     * @return r
     */
    @Override
    public ResponseDataStructure getPeopleCount(String liveId) {
        Long size = redisTemplate.opsForZSet().size(LiveConfig.peopleListKeyPrefix + liveId);
        return new ResponseDataStructure(200,"",size);
    }

    /**
     * 用户退出直播间
     *
     * @param liveId liveid
     * @param ip     ip
     * @return r
     */
    @Override
    public ResponseDataStructure exitLive(String liveId, String ip) {
        Long remove = redisTemplate.opsForSet().remove(LiveConfig.peopleListKeyPrefix + liveId, ip);
        //更新在线用户数据
        redisTemplate.opsForZSet().add(LiveConfig.peopleCountKey,liveId,
                redisTemplate.opsForSet().size(LiveConfig.peopleListKeyPrefix + liveId));
        return new ResponseDataStructure(200,"",remove>0);
    }



}

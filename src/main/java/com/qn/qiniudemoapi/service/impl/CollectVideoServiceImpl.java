package com.qn.qiniudemoapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qn.qiniudemoapi.mapper.CollectVideoMapper;
import com.qn.qiniudemoapi.pojo.CollectVideo;
import com.qn.qiniudemoapi.service.CollectVideoService;
import com.qn.qiniudemoapi.util.JwtUtil;
import com.qn.qiniudemoapi.util.ResponseDataStructure;
import com.qn.qiniudemoapi.vo.CollectVideoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectVideoServiceImpl extends ServiceImpl<CollectVideoMapper, CollectVideo>
        implements CollectVideoService {

    @Autowired
    private CollectVideoMapper collectVideoMapper;
    @Override
    public ResponseDataStructure addCollectVideo(String token, String collectId, String videoId) {
        String userId = JwtUtil.decrypt(token);
        CollectVideo collectVideo = new CollectVideo();
        collectVideo.setUserId(userId);
        collectVideo.setCollectId(collectId);
        collectVideo.setVideoId(videoId);
        int insert = collectVideoMapper.insert(collectVideo);
        return new ResponseDataStructure(200,"",insert>0);
    }

    @Override
    public ResponseDataStructure getCollectVideo(String collectId,int page,int size) {
        List<CollectVideoVo> list=collectVideoMapper.getCollectVideo(collectId,page,size);
        return new ResponseDataStructure(200,"",list);
    }

    @Override
    public ResponseDataStructure hasCollectVideo(String token, String videoId) {
        String userId = JwtUtil.decrypt(token);
        QueryWrapper<CollectVideo> qw = new QueryWrapper<>();
        qw.eq("user_id",userId)
                        .eq("video_id",videoId);
        boolean exists = collectVideoMapper.exists(qw);
        return new ResponseDataStructure(200,exists?"已收藏":"未收藏",exists);
    }
}

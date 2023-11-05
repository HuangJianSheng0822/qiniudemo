package com.qn.qiniudemoapi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qn.qiniudemoapi.config.rediskeyconfig.VideoPageConfig;
import com.qn.qiniudemoapi.config.rediskeyconfig.VideoPlayCountConfig;
import com.qn.qiniudemoapi.dto.ContentDto;
import com.qn.qiniudemoapi.mapper.VideoMapper;
import com.qn.qiniudemoapi.pojo.Video;
import com.qn.qiniudemoapi.service.VideoService;
import com.qn.qiniudemoapi.util.JwtUtil;
import com.qn.qiniudemoapi.vo.CoverVo;
import com.qn.qiniudemoapi.vo.VideoInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.qn.qiniudemoapi.util.ResponseDataStructure;
/**
 * @author PC
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private VideoPageConfig videoPageConfig;
    @Autowired
    private VideoPlayCountConfig videoPlayCountConfig;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    /**
     * 获取视频信息
     * @param id id
     * @return 信息
     */
    @Override
    public ResponseDataStructure getVideoInfo(String id) {
        VideoInfoVo videoInfo = videoMapper.getVideoInfo(id);
        Long size = redisTemplate.opsForSet().size(videoPlayCountConfig.getKeyPrefix() + id);
        videoInfo.setPlayback(size);
        return new ResponseDataStructure(200,"",videoInfo);
    }



    /**
     * 视频列表
     * @param page 页
     * @param limit 条
     * @return 结果
     */
    @Override
    public ResponseDataStructure getVideoCoverList(int page, int limit) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("limit", limit);
        List<CoverVo> mappderList = videoMapper.getVideoCoverList(map);
        return new ResponseDataStructure(200, "", mappderList);
    }


    /**
     * 添加视频
     * @param contentDto 内容
     * @param userId id
     * @return 结果
     */
    @Override
    public boolean addVideo(ContentDto contentDto, String userId) {
        Video video = new Video();
        video.setUserId(userId);
        video.setVideoId(contentDto.getVideoId());
        video.setCoverUrl(contentDto.getCoverUrl());
        video.setTitle(contentDto.getTitle());
        video.setTypeId(contentDto.getTypeId());
        video.setDescription(contentDto.getDescription());
        video.setTags(contentDto.getTags().toString());
        int i = videoMapper.insert(video);
        return i>0;
    }

    /**
     * 用户个人获取视频创作列表
     *
     * @param token id
     * @param page  page
     * @param size  size
     * @return list
     */
    @Override
    public ResponseDataStructure getVideoCoverListByUser(String token, int page, int size) {
        String userId = JwtUtil.decrypt(token);
        Page<Video> coverList = getCoverList(userId, page, size);
        long total = coverList.getTotal();
        List<Video> records = coverList.getRecords();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("list",records);
        return new ResponseDataStructure(200,"",map);
    }

    /**
     * 更新视频播放次数
     *
     * @param videoId 视频id
     * @param ip  用户ip
     * @return r
     */
    @Override
    public ResponseDataStructure updateVideoPlayCount(String videoId, String ip) {
        redisTemplate.opsForSet().add(videoPlayCountConfig.getKeyPrefix()+videoId,ip);
        return new ResponseDataStructure(200,"",redisTemplate.opsForSet().size(videoPlayCountConfig.getKeyPrefix()+videoId));
    }

    public Page<Video> getCoverList(String userId,int page,int size){
        QueryWrapper<Video> qw=new QueryWrapper<>();
        qw.select("id","title","cover_url","description","playback","created")
                .eq("user_id",userId)
                .orderByDesc("created");
        Page<Video> page1 = new Page<>(page,size);
        return videoMapper.selectPage(page1, qw);
    }

}

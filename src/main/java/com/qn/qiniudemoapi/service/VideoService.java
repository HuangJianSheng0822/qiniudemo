package com.qn.qiniudemoapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qn.qiniudemoapi.dto.ContentDto;
import com.qn.qiniudemoapi.pojo.Video;
import com.qn.qiniudemoapi.util.ResponseDataStructure;


public interface VideoService extends IService<Video> {


    /**
     * 获取视频信息
     * @param id id
     * @return 信息
     */
    ResponseDataStructure getVideoInfo(String id);


    /**
     * 视频列表
     * @param page 页
     * @param limit 条
     * @return 结果
     */
    ResponseDataStructure getVideoCoverList(int page, int limit);

    /**
     * 添加视频
     * @param contentDto 内容
     * @param userId id
     * @return 结果
     */
    boolean addVideo(ContentDto contentDto, String userId);


    /**
     * 用户个人获取视频创作列表
     * @param token id
     * @param page page
     * @param size size
     * @return list
     */
    ResponseDataStructure getVideoCoverListByUser(String token,int page,int size);


    /**
     * 更新视频播放次数
     * @param videoId 视频id
     * @param ip 用户ip
     * @return r
     */
    ResponseDataStructure updateVideoPlayCount(String videoId,String ip);
}

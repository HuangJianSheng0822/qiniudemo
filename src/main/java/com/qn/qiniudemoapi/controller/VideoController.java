package com.qn.qiniudemoapi.controller;

import com.qn.qiniudemoapi.dto.ContentDto;
import com.qn.qiniudemoapi.service.VideoService;
import com.qn.qiniudemoapi.util.IPUtils;
import com.qn.qiniudemoapi.util.JwtUtil;
import com.qn.qiniudemoapi.util.ResponseDataStructure;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author PC
 */
@RestController
@RequestMapping("video")
@Api(tags = "视频服务")
public class VideoController {


    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    /**
     * 根据id获取视频信息
     * @param id 视频id
     * @return 信息
     */
    @PostMapping ("/{id}")
    public ResponseDataStructure getVideoInfo(@PathVariable String id) {
        return videoService.getVideoInfo(id);
    }



    /**
     * 根据日期请求视频列表
     * @return 列表
     */
    @ApiOperation("根据日期请求视频列表")
    @GetMapping("/{page}/{size}")
    public ResponseDataStructure getVideoCoverList(@PathVariable("page") int page,@PathVariable("size") int size){
        return videoService.getVideoCoverList(page,size);
    }

    /**
     * 视频上传
     * @param contentDto 内容
     * @param authorization 用户认证
     * @return 结果
     */
    @ApiOperation("添加视频")
    @PostMapping("/add")
    public ResponseDataStructure addVideo(@RequestBody ContentDto contentDto, @RequestHeader("Authorization") String authorization) {
        String userId = JwtUtil.decrypt(authorization);
        boolean b = videoService.addVideo(contentDto, userId);
        return new ResponseDataStructure(ResponseDataStructure.Code_OK,ResponseDataStructure.Msg_OK,b);
    }

    /**
     * 用户个人获取上传视频
     * @param authorization token
     * @param page page
     * @param size size
     * @return list
     */
    @ApiOperation("用户个人获取创作视频")
    @PostMapping("/{page}/{size}")
    public ResponseDataStructure getCoverList(@RequestHeader("Authorization") String authorization
            ,@PathVariable("page") int page,@PathVariable("size") int size){
        return videoService.getVideoCoverListByUser(authorization,page,size);
    }

    /**
     * 更新视频播放次数
     * 需要获取用户ip
     * @param videoId 视频id
     * @return r
     */
    @ApiOperation(value = "更新视频播放次数")
    @GetMapping("/count/{videoId}")
    public ResponseDataStructure updateVideoPlayCount(@PathVariable("videoId") String videoId, HttpServletRequest req){
        String clientIP = IPUtils.getClientIP(req);
        return videoService.updateVideoPlayCount(videoId,clientIP);
    }

}

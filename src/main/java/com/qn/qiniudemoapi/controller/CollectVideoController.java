package com.qn.qiniudemoapi.controller;

import com.qn.qiniudemoapi.service.CollectVideoService;
import com.qn.qiniudemoapi.util.ResponseDataStructure;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("收藏视频")
@RestController
@RequestMapping("cv")
public class CollectVideoController {
    private final CollectVideoService collectVideoService;

    @Autowired
    public CollectVideoController(CollectVideoService collectVideoService) {
        this.collectVideoService = collectVideoService;
    }

    @ApiOperation(value = "添加收藏视频")
    @PostMapping("/{collectId}/{videoId}")
    public ResponseDataStructure addCollectVideo(@RequestHeader("Authorization") String token,
                                                 @PathVariable("collectId") String collectId,
                                                 @PathVariable("videoId") String videoId) {
        return collectVideoService.addCollectVideo(token,collectId,videoId);
    }

    @ApiOperation(value = "通过收藏夹id获取视频列表")
    @PostMapping("/{collectId}/{page}/{size}")
    public ResponseDataStructure getCollectVideo(@RequestHeader("Authorization") String token,
                                                 @PathVariable("collectId") String collectId,
                                                 @PathVariable("page") int page,
                                                 @PathVariable("size") int size) {
        // 在这里调用 CollectService 的 addCollect 方法，并返回结果
        return collectVideoService.getCollectVideo(collectId,page,size);
    }

    @ApiOperation(value = "视频是否已被收藏")
    @PostMapping("/{videoId}")
    public ResponseDataStructure hasCollectVideo(@RequestHeader("Authorization") String token,
                                                 @PathVariable("videoId") String videoId) {
        // 在这里调用 CollectService 的 addCollect 方法，并返回结果
        return collectVideoService.hasCollectVideo(token,videoId);
    }

}

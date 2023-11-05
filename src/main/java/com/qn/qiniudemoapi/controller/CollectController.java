package com.qn.qiniudemoapi.controller;


import com.qn.qiniudemoapi.dto.AddCollect;
import com.qn.qiniudemoapi.service.CollectService;
import com.qn.qiniudemoapi.util.ResponseDataStructure;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "收藏相关")
@RestController
@RequestMapping("/collect")
public class CollectController {

    private final CollectService collectService;


    @Autowired
    public CollectController(CollectService collectService) {
        this.collectService = collectService;
    }

    @ApiOperation(value = "查询用户创建的收藏夹")
    @GetMapping("/")
    public ResponseDataStructure getCollectList(@RequestHeader("Authorization") String token) {
        // 在这里调用 CollectService 的 getCollectList 方法，并返回结果
        return collectService.getCollectList(token);
    }

    @ApiOperation(value = "添加收藏夹")
    @PostMapping("/")
    public ResponseDataStructure addCollect(@RequestHeader("Authorization") String token,
                                            @RequestBody AddCollect addCollect) {
        // 在这里调用 CollectService 的 addCollect 方法，并返回结果
        return collectService.addCollect(token, addCollect);
    }



}
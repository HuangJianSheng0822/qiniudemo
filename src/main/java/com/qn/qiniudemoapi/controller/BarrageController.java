package com.qn.qiniudemoapi.controller;

import com.qn.qiniudemoapi.service.BarrageService;
import com.qn.qiniudemoapi.util.ResponseDataStructure;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barrage")
@Api(tags = "弹幕相关")
public class BarrageController {
    private final BarrageService barrageService;

    @Autowired
    public BarrageController(BarrageService barrageService) {
        this.barrageService = barrageService;
    }

    /**
     * 根据视频id查询全部弹幕
     * @param id 视频id
     * @return 列表
     */
    @ApiOperation(value = "根据视频id查询全部弹幕")
    @GetMapping("/{id}")
    public ResponseDataStructure getBarrageList(@PathVariable("id") String id){
        return barrageService.getBarrageList(id);
    }

}

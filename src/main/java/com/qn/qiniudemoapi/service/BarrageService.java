package com.qn.qiniudemoapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qn.qiniudemoapi.pojo.Barrage;
import com.qn.qiniudemoapi.util.ResponseDataStructure;

public interface BarrageService extends IService<Barrage> {

    /**
     * 根据视频id查询弹幕
     * @param videoId 视频id
     * @return result
     */

    ResponseDataStructure getBarrageList(String videoId);
}

package com.qn.qiniudemoapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qn.qiniudemoapi.mapper.BarrageMapper;
import com.qn.qiniudemoapi.pojo.Barrage;
import com.qn.qiniudemoapi.service.BarrageService;
import com.qn.qiniudemoapi.util.ResponseDataStructure;
import com.qn.qiniudemoapi.vo.BarrageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarrageServiceImpl extends ServiceImpl<BarrageMapper, Barrage> implements BarrageService {

    private BarrageMapper barrageMapper;

    @Autowired
    public BarrageServiceImpl(BarrageMapper barrageMapper) {
        this.barrageMapper = barrageMapper;
    }

    /**
     * 根据视频id查询弹幕
     * @param videoId 视频id
     * @return result
     */
    @Override
    public ResponseDataStructure getBarrageList(String videoId) {
        List<BarrageVo> barrageList = barrageMapper.getBarrageList(videoId);
        return new ResponseDataStructure(200,"",barrageList);
    }
}

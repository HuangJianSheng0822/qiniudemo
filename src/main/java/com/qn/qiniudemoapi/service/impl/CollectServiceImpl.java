package com.qn.qiniudemoapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qn.qiniudemoapi.dto.AddCollect;
import com.qn.qiniudemoapi.mapper.CollectMapper;
import com.qn.qiniudemoapi.pojo.Collect;
import com.qn.qiniudemoapi.service.CollectService;
import com.qn.qiniudemoapi.util.JwtUtil;
import com.qn.qiniudemoapi.util.ResponseDataStructure;
import com.qn.qiniudemoapi.vo.CollectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {

    @Autowired
    private CollectMapper collectMapper;
    @Override
    public ResponseDataStructure getCollectList(String token) {
        String userId = JwtUtil.decrypt(token);
        List<CollectVo> lists=collectMapper.getCollectList(userId);
        return new ResponseDataStructure(200,"",lists);
    }

    @Override
    public ResponseDataStructure addCollect(String token, AddCollect addCollect) {
        String userId = JwtUtil.decrypt(token);
        Collect collect = new Collect();
        collect.setName(addCollect.getName());
        collect.setCover(addCollect.getCover());
        collect.setDesc(addCollect.getDesc());
        collect.setUserId(userId);
        collect.setOpen(addCollect.isOpen()?1:0);
        int insert = collectMapper.insert(collect);
        return new ResponseDataStructure(200,"",insert>0);
    }


}

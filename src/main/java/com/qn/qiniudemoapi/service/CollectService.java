package com.qn.qiniudemoapi.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qn.qiniudemoapi.dto.AddCollect;
import com.qn.qiniudemoapi.pojo.Collect;
import com.qn.qiniudemoapi.util.ResponseDataStructure;

public interface CollectService extends IService<Collect> {
    ResponseDataStructure getCollectList(String token);

    ResponseDataStructure addCollect(String token, AddCollect addCollect);

}

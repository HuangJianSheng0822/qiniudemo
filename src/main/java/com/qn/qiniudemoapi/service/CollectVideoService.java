package com.qn.qiniudemoapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qn.qiniudemoapi.pojo.CollectVideo;
import com.qn.qiniudemoapi.util.ResponseDataStructure;


public interface CollectVideoService extends IService<CollectVideo> {
    ResponseDataStructure addCollectVideo(String token, String collectId, String videoId);

    ResponseDataStructure getCollectVideo(String collectId,int page,int size);

    ResponseDataStructure hasCollectVideo(String token,String videoId);
}

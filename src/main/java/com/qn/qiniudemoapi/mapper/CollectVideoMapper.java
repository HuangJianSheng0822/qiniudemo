package com.qn.qiniudemoapi.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qn.qiniudemoapi.pojo.CollectVideo;
import com.qn.qiniudemoapi.vo.CollectVideoVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CollectVideoMapper extends BaseMapper<CollectVideo> {

    List<CollectVideoVo> getCollectVideo(String collectId, int page, int size);
}

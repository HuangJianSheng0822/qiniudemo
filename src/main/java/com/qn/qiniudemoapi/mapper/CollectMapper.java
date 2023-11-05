package com.qn.qiniudemoapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.qn.qiniudemoapi.pojo.Collect;
import com.qn.qiniudemoapi.vo.CollectVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CollectMapper extends BaseMapper<Collect> {
    List<CollectVo> getCollectList(@Param("userId") String userId);
}

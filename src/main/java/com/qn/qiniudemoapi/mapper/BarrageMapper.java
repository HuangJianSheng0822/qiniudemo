package com.qn.qiniudemoapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qn.qiniudemoapi.pojo.Barrage;
import com.qn.qiniudemoapi.vo.BarrageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BarrageMapper extends BaseMapper<Barrage> {

    List<BarrageVo> getBarrageList(@Param("videoId") String videoId);
}

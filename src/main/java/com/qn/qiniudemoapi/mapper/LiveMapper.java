package com.qn.qiniudemoapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qn.qiniudemoapi.pojo.Live;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LiveMapper extends BaseMapper<Live> {
}

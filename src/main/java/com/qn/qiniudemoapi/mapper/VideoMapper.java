package com.qn.qiniudemoapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qn.qiniudemoapi.pojo.Video;
import com.qn.qiniudemoapi.vo.CoverVo;
import com.qn.qiniudemoapi.vo.VideoInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface VideoMapper extends BaseMapper<Video> {

    /**
     * 封面列表
     * @param map 集合
     * @return 结果
     */
    List<CoverVo> getVideoCoverList(@Param("map") HashMap<String,Object> map);


    /**
     * 根据id获取阿里云视频id
     * @param id id
     * @return id
     */
    String getVideoId(@Param("id") String id);

    /**
     * 根据id获取视频信息
     * @param id id
     * @return 结果
     */
    VideoInfoVo getVideoInfo(@Param("id") String id);

}

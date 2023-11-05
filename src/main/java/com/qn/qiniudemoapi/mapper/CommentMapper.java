package com.qn.qiniudemoapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qn.qiniudemoapi.pojo.Comment;
import com.qn.qiniudemoapi.vo.ChildComment;
import com.qn.qiniudemoapi.vo.RootComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {


    /**
     * 获取根评论，默认根评论的前3条评论
     * @param id 视频id
     * @param page 页
     * @param size 条
     * @return 列表
     */
    List<RootComment> getRootComment(String id,int page,int size);

    /**
     * 获取子评论
     * @param rootId 根评论id
     * @param page 页
     * @param size 条
     * @return 结果
     */
    List<ChildComment> getChildComment(@Param("rootId") String rootId,@Param("page") int page,@Param("size") int size);

}

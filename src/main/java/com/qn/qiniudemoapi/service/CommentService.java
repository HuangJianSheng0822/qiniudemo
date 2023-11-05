package com.qn.qiniudemoapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qn.qiniudemoapi.dto.AddCommentDto;
import com.qn.qiniudemoapi.pojo.Comment;
import com.qn.qiniudemoapi.util.ResponseDataStructure;

public interface CommentService extends IService<Comment> {


    /**
     * 获取根评论
     * @param id 视频id
     * @param page 页
     * @param size 条
     * @return 列表
     */
    ResponseDataStructure getRootComment(String id,int page,int size);

    /**
     * 评论添加
     * @param addCommentDto 评论
     * @return 结果
     */
    ResponseDataStructure addComment(String token,AddCommentDto addCommentDto);

    /**
     * 根据根评论获取子评论
     * @param id 根评论id
     * @param page 页
     * @param size 条
     * @return 结果
     */
    ResponseDataStructure getChildComment(String id,int page,int size);
}

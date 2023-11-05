package com.qn.qiniudemoapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qn.qiniudemoapi.dto.AddCommentDto;
import com.qn.qiniudemoapi.mapper.CommentMapper;
import com.qn.qiniudemoapi.pojo.Comment;
import com.qn.qiniudemoapi.service.CommentService;
import com.qn.qiniudemoapi.util.JwtUtil;
import com.qn.qiniudemoapi.util.ResponseDataStructure;
import com.qn.qiniudemoapi.vo.ChildComment;
import com.qn.qiniudemoapi.vo.RootComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 获取根评论
     * @param id 视频id
     * @param page 页
     * @param size 条
     * @return 列表
     */
    @Override
    public ResponseDataStructure getRootComment(String id,int page,int size) {
        List<RootComment> rootComments = commentMapper.getRootComment(id, page*size, size);
        return new ResponseDataStructure(ResponseDataStructure.Code_OK,ResponseDataStructure.Msg_OK,rootComments);
    }

    /**
     * 评论添加
     * @param addCommentDto 评论
     * @return 结果
     */
    @Override
    public ResponseDataStructure addComment(String token,AddCommentDto addCommentDto) {
        String userId = JwtUtil.decrypt(token);
        Comment comment = new Comment();
        System.out.println(addCommentDto.getContent());
        comment.setContent(addCommentDto.getContent());
        comment.setUserId(userId);
        comment.setVideoId(addCommentDto.getVideoId());
        comment.setRootId(addCommentDto.getRootId());
        comment.setToId(addCommentDto.getToId());
        comment.setToName(addCommentDto.getToName());
        int insert = commentMapper.insert(comment);
        return new ResponseDataStructure(200,insert>0?"评论成功":"评论失败",insert>0);
    }

    /**
     * 根据根评论获取子评论
     * @param id 根评论id
     * @param page 页
     * @param size 条
     * @return 结果
     */
    @Override
    public ResponseDataStructure getChildComment(String id, int page, int size) {
        List<ChildComment> childComment = commentMapper.getChildComment(id, page, size);
        return new ResponseDataStructure(200,"",childComment);
    }


}

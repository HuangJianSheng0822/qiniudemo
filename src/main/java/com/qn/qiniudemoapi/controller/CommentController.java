package com.qn.qiniudemoapi.controller;

import com.qn.qiniudemoapi.dto.AddCommentDto;
import com.qn.qiniudemoapi.service.CommentService;
import com.qn.qiniudemoapi.util.ResponseDataStructure;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "评论模块")
@RestController
@RequestMapping("comment")
public class CommentController {


    private final CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 根据id获取根评论，默认前3条
     * @param id 视频id
     * @return 列表
     */
    @ApiOperation("根据id获取根评论")
    @GetMapping("/r/{id}/{page}/{size}")
    public ResponseDataStructure getRootComment(@PathVariable("id") String id
            ,@PathVariable("page") int page
            ,@PathVariable("size") int size){
        return commentService.getRootComment(id, page, size);
    }

    /**
     * 根据根评论分页获取子评论
     * @param id 根评论id
     * @param page 页
     * @param size 条数
     * @return 列表
     */
    @GetMapping("/{id}/{page}/{size}")
    public ResponseDataStructure getChildComment(@PathVariable("id") String id
            ,@PathVariable("page") int page
            ,@PathVariable("size") int size){
        return commentService.getChildComment(id,page,size);
    }

    /**
     * 添加评论
     * @param addCommentDto 评论
     * @return 评论
     */
    @ApiOperation(value = "添加评论")
    @PostMapping("/")
    public ResponseDataStructure addComment(@RequestHeader("Authorization") String token,@RequestBody AddCommentDto addCommentDto){
        return commentService.addComment(token,addCommentDto);
    }
}

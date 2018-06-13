package cn.edu.nju.nowcode.controller;

import cn.edu.nju.nowcode.service.CommentService;
import cn.edu.nju.nowcode.vo.CommentVO;
import cn.edu.nju.nowcode.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cong on 2018-06-12.
 */
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment/{commentId}/more/{offset}/{limit}",method = RequestMethod.GET)
    public ResponseVO queryCommentByComment(@PathVariable Long commentId,@PathVariable Long offset,@PathVariable Integer limit){
        return commentService.queryCommentByComment(commentId,offset,limit);
    }

    @RequestMapping(value = "/comment/question/{questionId}/more/{offset}/{limit}",method = RequestMethod.GET)
    public ResponseVO queryCommentByQuestion(@PathVariable Long questionId,@PathVariable Long offset,@PathVariable Integer limit){
        return commentService.queryCommentByQuestion(questionId,offset,limit);
    }

    @RequestMapping(value = "/comment/add",method = RequestMethod.POST)
    public ResponseVO addComment(@RequestBody CommentVO commentVO){
        return commentService.addComment(commentVO);
    }

    @RequestMapping(value = "/comment/delete/{commentId}",method = RequestMethod.POST)
    public ResponseVO deleteComment(@PathVariable Long commentId){
        return commentService.deleteComment(commentId);
    }

    @RequestMapping(value = "/comment/count/{entityType}/{entityId}",method = RequestMethod.GET)
    public ResponseVO queryCommentCount(@PathVariable String entityType,@PathVariable Long entityId){
        return ResponseVO.buildSuccess(commentService.commentCount(entityId,entityType));
    }



}

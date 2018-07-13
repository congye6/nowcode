package cn.edu.nju.nowcode.controller;

import cn.edu.nju.nowcode.service.FollowQuestionService;
import cn.edu.nju.nowcode.service.FollowUserService;
import cn.edu.nju.nowcode.service.impl.UserContext;
import cn.edu.nju.nowcode.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cong on 2018-06-28.
 */
@RestController
public class FollowQuestionController {

    @Autowired
    private FollowQuestionService followQuestionService;

    @Autowired
    private UserContext userContext;

    @RequestMapping(value = "/follow/question/{questionId}",method = RequestMethod.POST)
    public ResponseVO follow(@PathVariable Long questionId){
        String userId=userContext.getUser().getUsername();
        return followQuestionService.follow(userId,questionId);
    }

    @RequestMapping(value = "/follow/cancel/question/{questionId}",method = RequestMethod.POST)
    public ResponseVO unfollow(@PathVariable Long questionId){
        String userId=userContext.getUser().getUsername();
        return followQuestionService.unfollow(userId,questionId);
    }

    @RequestMapping(value = "/follow/question/fans/list/{questionId}/{start}/{end}",method = RequestMethod.GET)
    public ResponseVO getFans(@PathVariable Long questionId,@PathVariable Long start,@PathVariable Long end){
        return followQuestionService.getFans(questionId,start,end);
    }

    @RequestMapping(value = "/follow/question/fans/count/{questionId}",method = RequestMethod.GET)
    public Integer getFansCount(@PathVariable Long questionId){
        return followQuestionService.getFansCount(questionId);
    }

    @RequestMapping(value = "/follow/question/user/{userId}/{start}/{end}")
    public ResponseVO getFollowQuestions(@PathVariable String userId,@PathVariable Long start,@PathVariable Long end){
        return followQuestionService.getFollowQuestions(userId,start,end);
    }


}

package cn.edu.nju.nowcode.controller;

import cn.edu.nju.nowcode.service.FollowUserService;
import cn.edu.nju.nowcode.service.impl.UserContext;
import cn.edu.nju.nowcode.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowUserController {


    @Autowired
    private FollowUserService followUserService;

    @Autowired
    private UserContext userContext;

    @RequestMapping(value = "/follow/user/{followerId}",method = RequestMethod.POST)
    public ResponseVO follow(@PathVariable String followerId){
        String userId=userContext.getUser().getUsername();
        return followUserService.follow(userId,followerId);
    }

    @RequestMapping(value = "/follow/cancel/user/{followerId}",method = RequestMethod.POST)
    public ResponseVO unfollow(@PathVariable String followerId){
        String userId=userContext.getUser().getUsername();
        return followUserService.unfollow(userId,followerId);
    }

    @RequestMapping(value = "/follow/list/{userId}/{start}/{end}",method = RequestMethod.GET)
    public ResponseVO getFollowers(@PathVariable String userId,@PathVariable Long start,@PathVariable Long end){
        return followUserService.getFollowers(userId,start,end);
    }

    @RequestMapping(value = "/follow/fans/list/{userId}/{start}/{end}",method = RequestMethod.GET)
    public ResponseVO getFans(@PathVariable String userId,@PathVariable Long start,@PathVariable Long end){
        return followUserService.getFans(userId,start,end);
    }

    @RequestMapping(value = "/follow/fans/count/{userId}",method = RequestMethod.GET)
    public Integer getFansCount(@PathVariable String userId){
        return followUserService.getFansCount(userId);
    }

    @RequestMapping(value = "/follow/is/{followerId}")
    public Boolean isFollower(@PathVariable String followerId){
        String userId=userContext.getUser().getUsername();
        return followUserService.isFollower(userId,followerId);
    }

}

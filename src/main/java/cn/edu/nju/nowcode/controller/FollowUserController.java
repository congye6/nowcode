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

}

package cn.edu.nju.nowcode.controller;

import cn.edu.nju.nowcode.service.LikeService;
import cn.edu.nju.nowcode.service.impl.UserContext;
import cn.edu.nju.nowcode.vo.LikeVO;
import cn.edu.nju.nowcode.vo.ResponseVO;
import cn.edu.nju.nowcode.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private UserContext userContext;

    @RequestMapping(value = "/like/add",method = RequestMethod.POST)
    public ResponseVO like(@RequestBody LikeVO likeVO){
        String username=userContext.getUser().getUsername();
        likeVO.setUserId(username);
        return likeService.like(likeVO);
    }

    @RequestMapping(value = "/like/delete",method = RequestMethod.POST)
    public ResponseVO dislike(@RequestBody LikeVO likeVO){
        String username=userContext.getUser().getUsername();
        likeVO.setUserId(username);
        return likeService.dislike(likeVO);
    }

    @RequestMapping(value = "/like/count/{entityType}/{entityId}")
    public Long count(@PathVariable String entityType,@PathVariable Long entityId){
        return likeService.count(entityType,entityId);
    }

}

package cn.edu.nju.nowcode.service.impl;

import cn.edu.nju.nowcode.service.UserService;
import cn.edu.nju.nowcode.util.RedisUtil;
import cn.edu.nju.nowcode.vo.ResponseVO;
import cn.edu.nju.nowcode.vo.UserInfoVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by cong on 2018-06-28.
 * 关注问题或用户的工具类
 */
@Service
public class FollowHelper {

    private static final Logger LOGGER=Logger.getLogger(FollowHelper.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    /**
     * 关注用户或问题
     * @param userId
     * @param followerId
     * @param followKey
     * @param fansKey
     * @return
     */
    public ResponseVO follow(String userId, String followerId,String followKey,String fansKey) {
        try{
            redisUtil.startTransaction();
            redisUtil.zsadd(followKey,followerId,new Date().getTime()+0.0);
            redisUtil.zsadd(fansKey,userId,new Date().getTime()+0.0);
            boolean result=redisUtil.commitTransaction();
            if(!result)
                return ResponseVO.buildFailure("关注失败，请确认是否已经关注");
        }catch (Exception e){
            LOGGER.error("关注失败",e);
            return ResponseVO.buildFailure("关注失败，请重试");
        }
        return ResponseVO.buildSuccess();
    }

    /**
     * 取消关注
     * @param userId
     * @param followerId
     * @param followKey
     * @param fansKey
     * @return
     */
    public ResponseVO unfollow(String userId, String followerId,String followKey,String fansKey) {
        try{
            redisUtil.startTransaction();
            redisUtil.zremove(followKey,followerId);
            redisUtil.zremove(fansKey,userId);
            boolean result=redisUtil.commitTransaction();
            if(!result)
                return ResponseVO.buildFailure("取消关注失败，请重试");
        }catch (Exception e){
            LOGGER.error("取消关注失败",e);
            return ResponseVO.buildFailure("取消关注失败，请重试");
        }
        return ResponseVO.buildSuccess();
    }

    /**
     * 获取用户概要信息
     * @param userList
     * @return
     */
    public List<UserInfoVO> getUserInfos(Set<String> userList){
        List<UserInfoVO> userInfos=new ArrayList<>();
        for(String userId:userList){
            userInfos.add(userService.getUserInfo(userId));
        }
        return userInfos;
    }



}

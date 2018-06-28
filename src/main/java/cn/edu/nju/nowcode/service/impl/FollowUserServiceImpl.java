package cn.edu.nju.nowcode.service.impl;

import cn.edu.nju.nowcode.service.FollowUserService;
import cn.edu.nju.nowcode.service.LikeService;
import cn.edu.nju.nowcode.service.QuestionService;
import cn.edu.nju.nowcode.util.RedisUtil;
import cn.edu.nju.nowcode.vo.FollowVO;
import cn.edu.nju.nowcode.vo.ResponseVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class FollowUserServiceImpl implements FollowUserService {

    private static final String FOLLOW_USER_KEY_PREDIX="follow_user_";

    private static final String FANS_USER_KEY_PREDIX="fans_user_";

    private static final Logger LOGGER=Logger.getLogger(FollowUserServiceImpl.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private LikeService likeService;

    @Autowired
    private QuestionService questionService;

    @Override
    public ResponseVO follow(String userId, String followerId) {
        try{
            redisUtil.startTransaction();
            redisUtil.zsadd(getFollowKey(userId),followerId,new Date().getTime()+0.0);
            redisUtil.zsadd(getFansKey(followerId),userId,new Date().getTime()+0.0);
            boolean result=redisUtil.commitTransaction();
            if(!result)
                return ResponseVO.buildFailure("关注失败，请确认是否已经关注");
        }catch (Exception e){
            LOGGER.error("关注失败",e);
            return ResponseVO.buildFailure("关注失败，请重试");
        }
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO unfollow(String userId, String followerId) {
        try{
            redisUtil.startTransaction();
            redisUtil.zremove(getFollowKey(userId),followerId);
            redisUtil.zremove(getFansKey(followerId),userId);
            boolean result=redisUtil.commitTransaction();
            if(!result)
                return ResponseVO.buildFailure("关注失败，请确认是否已经关注");
        }catch (Exception e){
            LOGGER.error("关注失败",e);
            return ResponseVO.buildFailure("关注失败，请重试");
        }
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO getFollowers(String userId, Long start, Long end) {
        Set<String> users=redisUtil.zRevRange(getFollowKey(userId),start,end);
        return ResponseVO.buildSuccess(getUserInfos(users));
    }

    @Override
    public ResponseVO getFans(String userId, Long start, Long end) {
        Set<String> users=redisUtil.zRevRange(getFansKey(userId),start,end);
        return ResponseVO.buildSuccess(getUserInfos(users));
    }

    @Override
    public Integer getFansCount(String userId) {
        return redisUtil.zcount(getFansKey(userId)).intValue();
    }

    @Override
    public Boolean isFollower(String userId, String followerId) {
        return redisUtil.zIsMember(getFollowKey(userId),followerId);
    }

    private Integer getFollowerCount(String userId){
        return redisUtil.zcount(getFansKey(userId)).intValue();
    }


    private List<FollowVO> getUserInfos(Set<String> userList){
        List<FollowVO> userInfos=new ArrayList<>();
        for(String userId:userList){
            userInfos.add(getUserInfo(userId));
        }
        return userInfos;
    }

    private FollowVO getUserInfo(String userId){
        FollowVO followVO=new FollowVO();
        followVO.setFansCount(getFansCount(userId));
        followVO.setFollowerCount(getFollowerCount(userId));
        followVO.setLikeCount(likeService.userLikeCount(userId));
        followVO.setQuestionCount(questionService.getQuestionCount(userId));
        return followVO;
    }

    private String getFollowKey(String userId){
        return FOLLOW_USER_KEY_PREDIX+userId;
    }

    private String getFansKey(String userId){
        return FANS_USER_KEY_PREDIX+userId;
    }
}

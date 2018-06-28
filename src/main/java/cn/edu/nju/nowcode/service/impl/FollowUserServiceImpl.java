package cn.edu.nju.nowcode.service.impl;

import cn.edu.nju.nowcode.service.FollowUserService;
import cn.edu.nju.nowcode.service.LikeService;
import cn.edu.nju.nowcode.service.QuestionService;
import cn.edu.nju.nowcode.service.UserService;
import cn.edu.nju.nowcode.util.RedisUtil;
import cn.edu.nju.nowcode.vo.UserInfoVO;
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
    private FollowHelper followHelper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ResponseVO follow(String userId, String followerId) {
        return followHelper.follow(userId,followerId,getFollowKey(userId),getFansKey(followerId));
    }

    @Override
    public ResponseVO unfollow(String userId, String followerId) {
        return followHelper.unfollow(userId,followerId,getFollowKey(userId),getFansKey(followerId));
    }

    @Override
    public ResponseVO getFollowers(String userId, Long start, Long end) {
        Set<String> users=redisUtil.zRevRange(getFollowKey(userId),start,end);
        return ResponseVO.buildSuccess(followHelper.getUserInfos(users));
    }

    @Override
    public ResponseVO getFans(String userId, Long start, Long end) {
        Set<String> users=redisUtil.zRevRange(getFansKey(userId),start,end);
        return ResponseVO.buildSuccess(followHelper.getUserInfos(users));
    }

    @Override
    public Integer getFansCount(String userId) {
        return redisUtil.zcount(getFansKey(userId)).intValue();
    }

    @Override
    public Boolean isFollower(String userId, String followerId) {
        return redisUtil.zIsMember(getFollowKey(userId),followerId);
    }

    public Integer getFollowerCount(String userId){
        return redisUtil.zcount(getFansKey(userId)).intValue();
    }



    private String getFollowKey(String userId){
        return FOLLOW_USER_KEY_PREDIX+userId;
    }

    private String getFansKey(String userId){
        return FANS_USER_KEY_PREDIX+userId;
    }
}

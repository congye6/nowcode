package cn.edu.nju.nowcode.service.impl;

import cn.edu.nju.nowcode.service.FollowQuestionService;
import cn.edu.nju.nowcode.service.QuestionService;
import cn.edu.nju.nowcode.util.RedisUtil;
import cn.edu.nju.nowcode.vo.QuestionVO;
import cn.edu.nju.nowcode.vo.ResponseVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by cong on 2018-06-28.
 */
@Service
public class FollowQuestionServiceImpl implements FollowQuestionService{

    private static final Logger LOGGER=Logger.getLogger(FollowQuestionServiceImpl.class);

    private static final String FANS_QUESTION_KEY_PREDIX="fans_question_";

    private static final String FOLLOW_QUESTION_KEY_PREDIX="follow_question_";

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private FollowHelper followHelper;

    @Autowired
    private QuestionService questionService;

    @Override
    public ResponseVO follow(String userId, Long questionId) {
        return followHelper.follow(userId,questionId+"",getFollowKey(userId),getFansKey(questionId));
    }

    @Override
    public ResponseVO unfollow(String userId, Long questionId) {
        return followHelper.unfollow(userId,questionId+"",getFollowKey(userId),getFansKey(questionId));
    }

    @Override
    public ResponseVO getFans(Long questionId, Long start, Long end) {
        Set<String> users=redisUtil.zRevRange(getFansKey(questionId),start,end);
        return ResponseVO.buildSuccess(followHelper.getUserInfos(users));
    }

    @Override
    public ResponseVO getFollowQuestions(String userId, Long start, Long end) {
        Set<String> questions=redisUtil.zRevRange(getFollowKey(userId),start,end);
        List<QuestionVO> results=new ArrayList<>();
        for(String questionId:questions){
            results.add(questionService.getQuestionById(Long.parseLong(questionId)));
        }
        return ResponseVO.buildSuccess(results);
    }

    @Override
    public Integer getFansCount(Long questionId) {
        return redisUtil.zcount(getFansKey(questionId)).intValue();
    }

    private String getFollowKey(String userId){
        return FOLLOW_QUESTION_KEY_PREDIX+userId;
    }

    private String getFansKey(Long userId){
        return FANS_QUESTION_KEY_PREDIX+userId;
    }
}

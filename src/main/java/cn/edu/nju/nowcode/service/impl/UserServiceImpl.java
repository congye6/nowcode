package cn.edu.nju.nowcode.service.impl;

import cn.edu.nju.nowcode.mapper.UserMapper;
import cn.edu.nju.nowcode.service.FollowUserService;
import cn.edu.nju.nowcode.service.LikeService;
import cn.edu.nju.nowcode.service.QuestionService;
import cn.edu.nju.nowcode.service.UserService;
import cn.edu.nju.nowcode.vo.UserInfoVO;
import cn.edu.nju.nowcode.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by cong on 2018-05-18.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper mapper;

    @Autowired
    private LikeService likeService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private FollowUserService followUserService;

    @Override
    public UserVO getUserByName(String username) {
        if(StringUtils.isEmpty(username))
            return null;
        return mapper.selectByName(username);
    }

    @Override
    public UserVO getUserById(Long id) {
        if(id==null||id<=0)
            return null;
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void addUser(UserVO userVO) {
        mapper.insertSelective(userVO);
    }

    @Override
    public boolean isExist(String userId) {
        return mapper.selectByName(userId)!=null;
    }

    @Override
    public UserInfoVO getUserInfo(String userId) {
        UserInfoVO followVO=new UserInfoVO();
        followVO.setUserId(userId);
        followVO.setFansCount(followUserService.getFansCount(userId));
        followVO.setFollowerCount(followUserService.getFollowerCount(userId));
        followVO.setLikeCount(likeService.userLikeCount(userId));
        followVO.setQuestionCount(questionService.getQuestionCount(userId));
        followVO.setUserId(userId);
        return followVO;
    }
}

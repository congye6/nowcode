package cn.edu.nju.nowcode.service.impl;

import cn.edu.nju.nowcode.mapper.NewsMapper;
import cn.edu.nju.nowcode.service.FollowUserService;
import cn.edu.nju.nowcode.service.NewsService;
import cn.edu.nju.nowcode.vo.NewsVO;
import cn.edu.nju.nowcode.vo.ResponseVO;
import cn.edu.nju.nowcode.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by cong on 2018-07-13.
 */
@Service
public class NewsServiceImpl implements NewsService{

    @Autowired
    private FollowUserService followService;

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public ResponseVO addNews(NewsVO news) {
        newsMapper.insertNews(news);
        return ResponseVO.buildSuccess(news.getId());
    }

    @Override
    public ResponseVO getNewsOfUser(String userId, long maxId, int limit) {
        ResponseVO responseVO=followService.getFollowers(userId,0l,Long.MAX_VALUE);
        if(!responseVO.getSuccess())
            return responseVO;
        List<UserInfoVO> followers=(List<UserInfoVO>)responseVO.getContent();
        List<String> followerIds=new ArrayList<>();
        for(UserInfoVO follower:followers){
            followerIds.add(follower.getUserId());
        }
        List<NewsVO> news=newsMapper.getNewsByUser(followerIds,maxId,limit);
        return ResponseVO.buildSuccess(news);
    }
}

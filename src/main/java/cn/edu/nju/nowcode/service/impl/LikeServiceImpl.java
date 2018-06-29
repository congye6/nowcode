package cn.edu.nju.nowcode.service.impl;

import cn.edu.nju.nowcode.async_queue.EventProducer;
import cn.edu.nju.nowcode.enumeration.EventType;
import cn.edu.nju.nowcode.service.LikeService;
import cn.edu.nju.nowcode.util.RedisUtil;
import cn.edu.nju.nowcode.vo.EventVO;
import cn.edu.nju.nowcode.vo.LikeVO;
import cn.edu.nju.nowcode.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LikeServiceImpl implements LikeService {

    private static final String COMMENT_LIKE_KEY="comment_like";

    private static final String SPLITER="_";

    private static final String USER_LIKE_COUNT_KEY="user_like_count_";

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private EventProducer eventProducer;

    @Override
    public ResponseVO like(LikeVO likeVO) {
        String key=getKey(likeVO);
        if(redisUtil.isMember(key,likeVO.getUserId()))
            return ResponseVO.buildFailure("已经点赞成功");
        redisUtil.sadd(key,likeVO.getUserId());
        eventProducer.produce(new EventVO(EventType.LIKE,likeVO.getUserId(),likeVO.getEntityType(),likeVO.getEntityId(),null));
        return ResponseVO.buildSuccess(redisUtil.scount(key));
    }


    @Override
    public ResponseVO dislike(LikeVO likeVO) {
        String key=getKey(likeVO);
        if(!redisUtil.isMember(key,likeVO.getUserId()))
            return ResponseVO.buildFailure("已经取消点赞");
        redisUtil.sremove(key,likeVO.getUserId());
        eventProducer.produce(new EventVO(EventType.DISLIKE,likeVO.getUserId(),likeVO.getEntityType(),likeVO.getEntityId(),null));
        return ResponseVO.buildSuccess(redisUtil.scount(key));
    }

    @Override
    public Long count(String entityType, Long entityId) {
        LikeVO likeVO=new LikeVO();
        likeVO.setEntityId(entityId);
        likeVO.setEntityType(entityType);
        return redisUtil.scount(getKey(likeVO));
    }

    @Override
    public Integer userLikeCount(String userId) {
        String count=redisUtil.get(USER_LIKE_COUNT_KEY+userId);
        if(count==null)
            return 0;
        return Integer.valueOf(count);
    }

    private String getKey(LikeVO likeVO){
        StringBuilder builder=new StringBuilder(COMMENT_LIKE_KEY);
        builder.append(SPLITER);
        builder.append(likeVO.getEntityType());
        builder.append(SPLITER);
        builder.append(likeVO.getEntityId());
        return builder.toString();

    }
}

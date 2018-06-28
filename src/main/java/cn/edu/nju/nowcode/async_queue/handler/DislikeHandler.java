package cn.edu.nju.nowcode.async_queue.handler;

import cn.edu.nju.nowcode.async_queue.EventHandler;
import cn.edu.nju.nowcode.enumeration.EventType;
import cn.edu.nju.nowcode.util.RedisUtil;
import cn.edu.nju.nowcode.vo.EventVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DislikeHandler implements EventHandler {

    private static final List<EventType> SUPPORT_TYPE= Arrays.asList(EventType.DISLIKE);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private LikeHandler likeHandler;

    @Override
    public void handle(EventVO event) {
        String ownerId=likeHandler.getOwnerId(event);
        redisUtil.desc(likeHandler.getUserLikeKey(ownerId));
    }

    @Override
    public boolean isSupport(EventType eventType) {
        return SUPPORT_TYPE.contains(eventType);
    }

    @Override
    public List<EventType> supportTypes() {
        return new ArrayList<>(SUPPORT_TYPE);
    }

}

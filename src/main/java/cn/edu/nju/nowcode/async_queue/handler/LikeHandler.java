package cn.edu.nju.nowcode.async_queue.handler;

import cn.edu.nju.nowcode.async_queue.EventHandler;
import cn.edu.nju.nowcode.enumeration.EventType;
import cn.edu.nju.nowcode.service.CommentService;
import cn.edu.nju.nowcode.service.MessageService;
import cn.edu.nju.nowcode.service.QuestionService;
import cn.edu.nju.nowcode.util.RedisUtil;
import cn.edu.nju.nowcode.vo.CommentVO;
import cn.edu.nju.nowcode.vo.EventVO;
import cn.edu.nju.nowcode.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LikeHandler implements EventHandler {

    private static final List<EventType> SUPPORT_TYPE= Arrays.asList(EventType.LIKE);

    private static final String USER_LIKE_COUNT_KEY="user_like_count_";

    @Autowired
    private MessageService messageService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void handle(EventVO event) {
        if(!isSupport(event.getEventType()))
            return;
        String ownerId=getOwnerId(event);
        sendMessage(event,ownerId);
        incrementUserLike(ownerId);
    }

    private void sendMessage(EventVO eventVO,String ownerId){
        MessageVO messageVO=new MessageVO();
        messageVO.setFromId(eventVO.getActorId());
        messageVO.setToId(ownerId);
        messageVO.setContent(messageVO.getFromId()+" 赞了你");
        messageService.sendMessage(messageVO);
    }

    String getOwnerId(EventVO eventVO){
        String type=eventVO.getEntityType();
        if("Question".equalsIgnoreCase(type)){
            return questionService.getQuestionById(eventVO.getEntityId()).getUserId();
        }else if ("Comment".equalsIgnoreCase(type)){
            return commentService.queryCommentById(eventVO.getEntityId()).getUserId();
        }
        return "";
    }

    @Override
    public boolean isSupport(EventType eventType) {
        return SUPPORT_TYPE.contains(eventType);
    }

    @Override
    public List<EventType> supportTypes() {
        return new ArrayList<>(SUPPORT_TYPE);
    }

    private void incrementUserLike(String userId){
        redisUtil.increment(getUserLikeKey(userId));
    }

    String getUserLikeKey(String userId){
        return USER_LIKE_COUNT_KEY+userId;
    }
}

package cn.edu.nju.nowcode.async_queue.handler;

import cn.edu.nju.nowcode.async_queue.EventHandler;
import cn.edu.nju.nowcode.enumeration.EventType;
import cn.edu.nju.nowcode.service.CommentService;
import cn.edu.nju.nowcode.service.MessageService;
import cn.edu.nju.nowcode.service.QuestionService;
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

    @Autowired
    private MessageService messageService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionService questionService;

    @Override
    public void handle(EventVO event) {
        if(!isSupport(event.getEventType()))
            return;
        sendMessage(event);
    }

    private void sendMessage(EventVO eventVO){
        MessageVO messageVO=new MessageVO();
        messageVO.setFromId(eventVO.getActorId());
        messageVO.setToId(getOwnerId(eventVO));
        messageVO.setContent(messageVO.getFromId()+" 赞了你");
        messageService.sendMessage(messageVO);
    }

    private String getOwnerId(EventVO eventVO){
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
}

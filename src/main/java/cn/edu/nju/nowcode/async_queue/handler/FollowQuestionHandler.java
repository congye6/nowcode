package cn.edu.nju.nowcode.async_queue.handler;

import cn.edu.nju.nowcode.async_queue.EventHandler;
import cn.edu.nju.nowcode.enumeration.EventType;
import cn.edu.nju.nowcode.service.MessageService;
import cn.edu.nju.nowcode.service.QuestionService;
import cn.edu.nju.nowcode.vo.EventVO;
import cn.edu.nju.nowcode.vo.MessageVO;
import cn.edu.nju.nowcode.vo.QuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FollowQuestionHandler implements EventHandler {

    private static final List<EventType> SUPPORT_TYPES= Arrays.asList(EventType.FOLLOW_QUESTION);

    @Autowired
    private QuestionService questionService;

    @Autowired
    private MessageService messageService;

    @Override
    public void handle(EventVO event) {
        QuestionVO questionVO=questionService.getQuestionById(event.getEntityId());
        MessageVO messageVO=new MessageVO(event.getActorId(),questionVO.getUserId(),event.getActorId()+"关注了你的问题:"+questionVO.getTitle());
        messageService.sendMessage(messageVO);
    }

    @Override
    public boolean isSupport(EventType eventType) {
        return SUPPORT_TYPES.contains(eventType);
    }

    @Override
    public List<EventType> supportTypes() {
        return new ArrayList<>(SUPPORT_TYPES);
    }
}

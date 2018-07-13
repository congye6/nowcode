package cn.edu.nju.nowcode.async_queue.handler;

import cn.edu.nju.nowcode.async_queue.EventHandler;
import cn.edu.nju.nowcode.enumeration.EventType;
import cn.edu.nju.nowcode.service.MessageService;
import cn.edu.nju.nowcode.vo.EventVO;
import cn.edu.nju.nowcode.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class FollowHandler implements EventHandler {

    private static final List<EventType> SUPPORT_TYPES= Arrays.asList(EventType.FOLLOW);

    @Autowired
    private MessageService messageService;

    @Override
    public void handle(EventVO event) {
        MessageVO messageVO=new MessageVO(event.getActorId(),event.getEntityOwnerId(),event.getActorId()+"关注了你");
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

package cn.edu.nju.nowcode.async_queue.handler;

import cn.edu.nju.nowcode.async_queue.EventHandler;
import cn.edu.nju.nowcode.enumeration.EventType;
import cn.edu.nju.nowcode.vo.EventVO;
import cn.edu.nju.nowcode.vo.NewsVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cong on 2018-07-13.
 */
@Service
public class NewsHandler implements EventHandler{

    private static final List<EventType> SUPPORT_TYPE= Arrays.asList(EventType.LIKE,EventType.QUESTION,EventType.COMMENT);

    @Override
    public void handle(EventVO event) {
        NewsVO newsVO=new NewsVO();
        newsVO.setType(event.getEventType());
        newsVO.setUserId(event.getActorId());
        newsVO.setContent(event.getEntityId()+"");
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

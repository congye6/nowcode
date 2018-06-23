package cn.edu.nju.nowcode.async_queue.handler;

import cn.edu.nju.nowcode.async_queue.EventHandler;
import cn.edu.nju.nowcode.enumeration.EventType;
import cn.edu.nju.nowcode.vo.EventVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LikeHandler implements EventHandler {

    private static final List<EventType> SUPPORT_TYPE= Arrays.asList(EventType.LIKE);

    @Override
    public void handle(EventVO event) {
        if(!isSupport(event.getEventType()))
            return;



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

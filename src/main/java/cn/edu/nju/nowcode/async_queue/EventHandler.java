package cn.edu.nju.nowcode.async_queue;

import cn.edu.nju.nowcode.enumeration.EventType;
import cn.edu.nju.nowcode.vo.EventVO;

import java.util.List;

/**
 * 事件处理器
 */
public interface EventHandler {

    /**
     * 处理相应的事件
     * @param event
     */
    public void handle(EventVO event);

    /**
     * 判断是否支持
     * @param eventType
     * @return
     */
    public boolean isSupport(EventType eventType);


    /**
     * 获取支持处理的事件
     * @return
     */
    public List<EventType> supportTypes();
}

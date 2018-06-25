package cn.edu.nju.nowcode.async_queue;

import cn.edu.nju.nowcode.enumeration.EventType;
import cn.edu.nju.nowcode.util.RedisUtil;
import cn.edu.nju.nowcode.vo.EventVO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class EventComsumer implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 执行异步任务的线程池
     */
    private static final Executor COMSUMER_THREAD_POOL= Executors.newFixedThreadPool(20);

    /**
     * 事件和相应处理handler的映射
     */
    private Map<EventType,List<EventHandler>> handlerMap=new HashMap<>();

    @PostConstruct
    public void comsumer(){
        Map<String,EventHandler> beans=applicationContext.getBeansOfType(EventHandler.class);
        if(beans==null)
            return;
        initHandlerMap(beans);
        new Thread(new EventComsumerTask()).start();
    }

    /**
     * 初始化
     * @param beans
     */
    private void initHandlerMap(Map<String,EventHandler> beans){
        for(EventHandler eventHandler:beans.values()){
            for(EventType type:eventHandler.supportTypes()){
                List<EventHandler> handlers=handlerMap.get(type);
                if(handlers==null){
                    handlers=new ArrayList<>();
                    handlerMap.put(type,handlers);
                }
                handlers.add(eventHandler);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 不断获取event进行处理
     */
    private class EventComsumerTask implements Runnable{

        @Override
        public void run() {
            while(true){
                String eventJson=redisUtil.lget(EventProducer.EVENT_QUEUE_KEY);
                EventVO event=JSONObject.parseObject(eventJson,EventVO.class);
                if(event==null||event.getEventType()==null)//无效的event
                    continue;
                List<EventHandler> matchHandlers=handlerMap.get(event.getEventType());
                for(EventHandler handler:matchHandlers){
                    EventHandleTask task=new EventHandleTask(event,handler);
                    COMSUMER_THREAD_POOL.execute(task);
                }
            }
        }
    }

    /**
     * 执行处理
     */
    private class EventHandleTask implements Runnable{

        private EventVO event;

        private EventHandler eventHandler;

        public EventHandleTask(EventVO event, EventHandler eventHandler) {
            this.event = event;
            this.eventHandler = eventHandler;
        }

        @Override
        public void run() {
            eventHandler.handle(event);
        }
    }
}

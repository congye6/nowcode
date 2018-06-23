package cn.edu.nju.nowcode.async_queue;

import cn.edu.nju.nowcode.util.RedisUtil;
import cn.edu.nju.nowcode.vo.EventVO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventProducer {


    static final String EVENT_QUEUE_KEY="nowcode_event_queue";

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 发送事件消息
     * @param eventVO
     */
    public void produce(EventVO eventVO){
        if(eventVO==null||eventVO.getEntityType()==null)
            return;
        String json=JSONObject.toJSONString(eventVO);
        redisUtil.ladd(EVENT_QUEUE_KEY,json);
    }

}

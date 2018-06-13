package cn.edu.nju.nowcode.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * Created by cong on 2018-01-31.
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 将key对应的long值递增，如果不存在则置为0
     * @param key
     * @return
     */
    public Long increment(String key){
        Long result = redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) {
                Jedis jedis = (Jedis) connection.getNativeConnection();
                return jedis.incr(key);
            }
        }, true);
        return result;
    }

    /**
     * 添加一个string类型的value
     * @param key
     * @param value
     */
    public void set(String key,Object value){
        redisTemplate.opsForValue().set(key, JSONObject.toJSONString(value));
    }

    /**
     * 添加一个string类型的value
     * @param key
     * @param value
     */
    public void set(String key,Object value,long expireSencond){
        redisTemplate.opsForValue().set(key, JSONObject.toJSONString(value),expireSencond, TimeUnit.SECONDS);
    }

    /**
     * 获取值
     * @param key
     * @return
     */
    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }

}
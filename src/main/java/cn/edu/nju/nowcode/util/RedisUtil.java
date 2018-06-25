package cn.edu.nju.nowcode.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Set;
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


    /**
     * 集合操作
     * @param key
     * @return
     */
    public Set<String> sget(String key){
        return redisTemplate.opsForSet().members(key);
    }

    public void sadd(String key,Object value){
        redisTemplate.opsForSet().add(key,JSONObject.toJSONString(value));
    }

    public boolean isMember(String key,Object value){
        return redisTemplate.opsForSet().isMember(key,JSONObject.toJSONString(value));
    }

    public Long scount(String key){
        return redisTemplate.opsForSet().size(key);
    }

    public void sremove(String key,String value){
        redisTemplate.opsForSet().remove(key,JSONObject.toJSONString(value));
    }

    /**
     * 有序集合
     */
    public void zsadd(String key,Object value,Double score){
        redisTemplate.opsForZSet().add(key,JSONObject.toJSONString(value),score);
    }


    /**
     * list操作
     */
    public void ladd(String key,Object value){
        String json=null;
        if(!(value instanceof String))
            json=JSONObject.toJSONString(value);
        else
            json=(String)value;
        redisTemplate.opsForList().leftPush(key,json);
    }

    public String lget(String key){
        String result = redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) {
                Jedis jedis = (Jedis) connection.getNativeConnection();

                return jedis.blpop(0,key).get(1);
            }
        }, true);
        return result;
    }

}
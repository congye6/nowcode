package cn.edu.nju.nowcode.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by cong on 2018-01-31.
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public void persist(String key){
        redisTemplate.persist(key);
    }

    public void expire(String key,Long time){
        redisTemplate.expire(key,time,TimeUnit.SECONDS);
    }

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

    public Long desc(String key){
        Long result = redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) {
                Jedis jedis = (Jedis) connection.getNativeConnection();

                return jedis.decr(key);
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
        redisTemplate.opsForValue().set(key, toJsonString(value));
    }

    /**
     * 添加一个string类型的value
     * @param key
     * @param value
     */
    public void set(String key,Object value,long expireSencond){
        redisTemplate.opsForValue().set(key, toJsonString(value),expireSencond, TimeUnit.SECONDS);
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
        redisTemplate.opsForSet().add(key,toJsonString(value));
    }

    public boolean isMember(String key,Object value){
        return redisTemplate.opsForSet().isMember(key,toJsonString(value));
    }

    public Long scount(String key){
        return redisTemplate.opsForSet().size(key);
    }

    public void sremove(String key,String value){
        redisTemplate.opsForSet().remove(key,value);
    }

    /**
     * 有序集合
     */
    public void zsadd(String key,Object value,Double score){
        redisTemplate.opsForZSet().add(key,toJsonString(value),score);
    }

    public Set<String> zRevRange(String key,Long start,Long end){
        return redisTemplate.opsForZSet().reverseRange(key,start,end);
    }

    public void zremove(String key,Object value){
        redisTemplate.opsForZSet().remove(key,toJsonString(value));
    }

    public Long zcount(String key){
        return redisTemplate.opsForZSet().zCard(key);
    }

    public boolean zIsMember(String key,Object value){
        return redisTemplate.opsForZSet().rank(key,toJsonString(value))!=null;
    }


    /**
     * list操作
     */
    public void ladd(String key,Object value){
        redisTemplate.opsForList().leftPush(key,toJsonString(value));
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

    /**
     * map
     */
    public void madd(String mapKey,String key,Object value){
        redisTemplate.opsForHash().put(mapKey,key,toJsonString(value));
    }

    public Object mget(String mapKey,String key){
        return redisTemplate.opsForHash().get(mapKey,key);
    }

    /**
     * 事务
     */
    public void startTransaction(){
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.multi();
    }

    public List<Object> commitTransaction(){
        return redisTemplate.exec();
    }

    private String toJsonString(Object o){
        if(o instanceof String)
            return (String)o;
        return JSONObject.toJSONString(o);
    }

}
package cn.edu.nju.nowcode.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * Created by cong on 2018-06-13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTester {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testConnection(){
        redisUtil.set("congye6","666");
        System.out.println(redisUtil.get("congye6"));
    }

    @Test
    public void testSet(){
        redisUtil.sadd("set","niupi");
        System.out.println(redisUtil.sget("set"));
        System.out.println(redisUtil.scount("set"));
        System.out.println(redisUtil.isMember("set","niupi"));
    }

    @Test
    public void testList(){
        redisUtil.ladd("list","niupi");
        redisUtil.ladd("list","niupi2");
        redisUtil.ladd("list","niupi3");
        System.out.println(redisUtil.lget("list"));
        System.out.println(redisUtil.lget("list"));
        System.out.println(redisUtil.lget("list"));
    }

    @Test
    public void testZset(){
        Set<String> set=redisUtil.zRevRange("zset",0L,10L);
        System.out.println(set);
        System.out.println(redisUtil.zcount("zset"));
        System.out.println(redisUtil.zIsMember("zset","niupi"));
        System.out.println(redisUtil.zIsMember("zset","niupi2"));
    }


    @Test
    public void testMap(){
        String key="map";
        redisUtil.madd(key,"niupi","666");
        redisUtil.madd(key,"niupi2",777);
        Long result=Long.valueOf((String)redisUtil.mget(key,"niupi2"));
        System.out.println(result-1);
    }

    @Test
    public void testIncr(){
        String key="increment";
        redisUtil.increment(key);
        System.out.println(redisUtil.get(key));
        redisUtil.increment(key);
        System.out.println(redisUtil.get(key));
        redisUtil.desc(key);
        System.out.println(redisUtil.get(key));
    }


}

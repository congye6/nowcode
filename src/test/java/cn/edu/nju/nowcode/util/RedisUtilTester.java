package cn.edu.nju.nowcode.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void testZSet(){
        redisUtil.ladd("list","niupi");
        redisUtil.ladd("list","niupi2");
        redisUtil.ladd("list","niupi3");
        System.out.println(redisUtil.lget("list"));
        System.out.println(redisUtil.lget("list"));
        System.out.println(redisUtil.lget("list"));
    }


}

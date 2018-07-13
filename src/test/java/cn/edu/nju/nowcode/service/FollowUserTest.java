package cn.edu.nju.nowcode.service;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by cong on 2018-07-13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FollowUserTest {

    @Autowired
    private FollowUserService followUserService;

    @Test
    public void getFollowers(){
        System.out.println(JSONObject.toJSONString(followUserService.getFollowers("test",0l,10l)));
    }


}

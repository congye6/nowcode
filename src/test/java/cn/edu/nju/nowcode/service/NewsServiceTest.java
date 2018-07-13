package cn.edu.nju.nowcode.service;

import cn.edu.nju.nowcode.enumeration.EventType;
import cn.edu.nju.nowcode.vo.NewsVO;
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
public class NewsServiceTest {

    @Autowired
    private NewsService newsService;

    @Test
    public void addNews(){
        NewsVO newsVO=new NewsVO();
        newsVO.setContent("congye20发表了一个问题");
        newsVO.setType(EventType.QUESTION);
        newsVO.setUserId("congye20");
        newsService.addNews(newsVO);
        System.out.println(newsVO.getId());
    }

    @Test
    public void getUserNews(){
        System.out.println(JSONObject.toJSONString(newsService.getNewsOfUser("test",100,10)));
    }


}

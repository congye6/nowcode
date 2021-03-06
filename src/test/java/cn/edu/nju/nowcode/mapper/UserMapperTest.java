package cn.edu.nju.nowcode.mapper;

import cn.edu.nju.nowcode.vo.CommentShowVO;
import cn.edu.nju.nowcode.vo.TicketVO;
import cn.edu.nju.nowcode.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by cong on 2018-05-16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private LoginTicketMapper ticketMapper;

    @Autowired
    private SensitiveMapper sensitiveMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void addUser(){
        UserVO userVO=new UserVO();
        userVO.setUsername("congye6");
        userVO.setPassword("666");
        userVO.setHeadUrl("http://local");
        userVO.setSalt("hhh");
        mapper.insertSelective(userVO);
        System.out.println(userVO.getId());
    }

    @Test
    public void latestQuestion(){
        questionMapper.getLatestQuestions(0,0,3);
    }

    @Test
    public void addTicket(){
        TicketVO ticketVO=new TicketVO();
        ticketVO.setUserId(2l);
        ticketVO.setIsValid(true);
        ticketVO.setTicket("2333");
        ticketMapper.insertSelective(ticketVO);
    }

    @Test
    public void getAllSensitive(){
        System.out.println(sensitiveMapper.selectAllSensitive());
    }

    @Test
    public void updateHasRead(){
        messageMapper.updateHasRead(true,1l);
    }

    @Test
    public void selectCount(){
        System.out.println(commentMapper.selectCount(1l,"Question"));
    }

}

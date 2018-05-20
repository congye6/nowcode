package cn.edu.nju.nowcode.service.impl;

import cn.edu.nju.nowcode.mapper.LoginTicketMapper;
import cn.edu.nju.nowcode.service.LoginTicketService;
import cn.edu.nju.nowcode.vo.TicketVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cong on 2018-05-19.
 */
@Service
public class LoginTicketServiceImpl implements LoginTicketService{

    @Autowired
    private LoginTicketMapper mapper;

    @Override
    public TicketVO getTicket(Long userId) {
        if(userId==null||userId<=0)
            return null;
        return mapper.selectByUserId(userId);
    }

    @Override
    public TicketVO addTicket(Long userId) {
        return null;
    }

    @Override
    public void invalidTicket(String ticket) {
        mapper.updateByTicket();
    }
}

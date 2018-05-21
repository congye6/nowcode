package cn.edu.nju.nowcode.service.impl;

import cn.edu.nju.nowcode.mapper.LoginTicketMapper;
import cn.edu.nju.nowcode.service.LoginTicketService;
import cn.edu.nju.nowcode.util.DateUtil;
import cn.edu.nju.nowcode.util.UUIDUtil;
import cn.edu.nju.nowcode.vo.TicketVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

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
    public TicketVO getTicket(String ticket) {
        if(StringUtils.isEmpty(ticket))
            return null;
        return mapper.selectByTicketId(ticket);
    }

    @Override
    public TicketVO addTicket(Long userId,Date expired) {
        TicketVO ticketVO=new TicketVO();
        ticketVO.setIsValid(true);
        ticketVO.setExpired(expired);
        ticketVO.setUserId(userId);
        ticketVO.setTicket(UUIDUtil.uuid().replace("-",""));

        mapper.insertSelective(ticketVO);

        return ticketVO;
    }

    @Override
    public void invalidTicket(String ticket) {
        TicketVO ticketVO=new TicketVO();
        ticketVO.setTicket(ticket);
        ticketVO.setIsValid(false);
        mapper.updateByTicket(ticketVO);
    }

    @Override
    public boolean isValidTicket(TicketVO ticketVO) {
        if(ticketVO==null)
            return false;
        if(StringUtils.isEmpty(ticketVO.getTicket()))
            return false;
        if(DateUtil.isBefore(ticketVO.getExpired(),new Date()))
            return false;
        return ticketVO.getIsValid();
    }
}

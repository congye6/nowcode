package cn.edu.nju.nowcode.mapper;

import cn.edu.nju.nowcode.vo.TicketVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginTicketMapper {

    int insertSelective(TicketVO record);

    TicketVO selectByUserId(Long userId);

    TicketVO selectByTicketId(String ticketId);

    TicketVO selectByPrimaryKey(Long id);

    int updateByTicket(TicketVO record);

}
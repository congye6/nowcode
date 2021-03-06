package cn.edu.nju.nowcode.service;

import cn.edu.nju.nowcode.vo.TicketVO;

import java.util.Date;

/**
 * Created by cong on 2018-05-19.
 * 用于标识用户登录的token
 */
public interface LoginTicketService {


    /**
     * 根据userid查询ticket信息
     * @param userId
     * @return
     */
    public TicketVO getTicket(Long userId);

    /**
     * 根据userid查询ticket信息
     * @param userId
     * @return
     */
    public TicketVO getTicket(String ticket);

    /**
     * 新增ticket
     * @param userId
     * @return 新增的ticket
     */
    public TicketVO addTicket(Long userId,Date expired);

    /**
     * 将ticket失效
     * @param ticket
     */
    public void invalidTicket(String ticket);

    /**
     * ticket是否有效
     * @param ticket
     * @return
     */
    public boolean isValidTicket(TicketVO ticketVO);

}

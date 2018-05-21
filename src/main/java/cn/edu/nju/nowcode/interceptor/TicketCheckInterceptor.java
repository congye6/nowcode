package cn.edu.nju.nowcode.interceptor;

import cn.edu.nju.nowcode.service.LoginTicketService;
import cn.edu.nju.nowcode.service.UserContext;
import cn.edu.nju.nowcode.service.UserService;
import cn.edu.nju.nowcode.util.CookieUtil;
import cn.edu.nju.nowcode.vo.TicketVO;
import cn.edu.nju.nowcode.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cong on 2018-05-20.
 * 检查cookie中是否含有有效ticket
 */
@Component
public class TicketCheckInterceptor implements HandlerInterceptor{

    @Autowired
    private LoginTicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserContext userContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket= CookieUtil.getCookie(request,CookieUtil.LOGIN_TICKET);
        String redirect="/login/page?next="+request.getRequestURL();

        TicketVO ticketVO=ticketService.getTicket(ticket);
        if(!ticketService.isValidTicket(ticketVO)){
            response.sendRedirect(redirect);
            return false;
        }

        UserVO userVO=userService.getUserById(ticketVO.getUserId());
        if(userVO==null){
            response.sendRedirect(redirect);
            return false;
        }

        userContext.setUser(userVO);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        if(modelAndView!=null)
            modelAndView.addObject("user",userContext.getUser());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        userContext.clear();
    }


}

package cn.edu.nju.nowcode.controller;

import cn.edu.nju.nowcode.service.LoginService;
import cn.edu.nju.nowcode.service.LoginTicketService;
import cn.edu.nju.nowcode.util.CookieUtil;
import cn.edu.nju.nowcode.util.DateUtil;
import cn.edu.nju.nowcode.vo.LoginVO;
import cn.edu.nju.nowcode.vo.ResponseVO;
import cn.edu.nju.nowcode.vo.TicketVO;
import cn.edu.nju.nowcode.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by cong on 2018-05-18.
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private LoginTicketService ticketService;

    @RequestMapping(value="/login/page",method = RequestMethod.GET)
    public ModelAndView loginPage(String next){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("next",next);
        return modelAndView;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO login(@RequestBody LoginVO loginVO,HttpServletResponse response, HttpServletRequest request){
        ResponseVO responseVO=loginService.login(loginVO.getUserVO());
        addTicketCookie(response, request, responseVO);
        return responseVO;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO register(@RequestBody UserVO userVO, HttpServletResponse response, HttpServletRequest request){
        ResponseVO responseVO=loginService.register(userVO);
        addTicketCookie(response, request, responseVO);
        return responseVO;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ResponseVO logout(HttpServletRequest request){
        String ticket=CookieUtil.getCookie(request,CookieUtil.LOGIN_TICKET);
        ticketService.invalidTicket(ticket);
        return ResponseVO.buildSuccess();
    }

    /**
     * 添加ticket到cookie
     * @param response
     * @param request
     * @param responseVO
     */
    private void addTicketCookie( HttpServletResponse response, HttpServletRequest request, ResponseVO responseVO) {
        if(responseVO.getSuccess()){//注册成功，保持登录状态
            UserVO userVO=(UserVO)responseVO.getContent();
            TicketVO ticketVO=ticketService.addTicket(userVO.getId(), DateUtil.addMonth(new Date(),3));
            CookieUtil.addCookie(CookieUtil.LOGIN_TICKET,ticketVO.getTicket(),90,response,request);
        }
    }


}

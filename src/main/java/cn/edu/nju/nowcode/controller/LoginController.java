package cn.edu.nju.nowcode.controller;

import cn.edu.nju.nowcode.service.LoginService;
import cn.edu.nju.nowcode.vo.LoginVO;
import cn.edu.nju.nowcode.vo.ResponseVO;
import cn.edu.nju.nowcode.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cong on 2018-05-18.
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value="/login/page",method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO login(@RequestBody LoginVO loginVO){
        return loginService.login(loginVO.getUserVO());
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO register(@RequestBody UserVO userVO){
        return loginService.register(userVO);
    }


}

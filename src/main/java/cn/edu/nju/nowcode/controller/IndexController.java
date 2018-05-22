package cn.edu.nju.nowcode.controller;

import cn.edu.nju.nowcode.mapper.UserMapper;
import cn.edu.nju.nowcode.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cong on 2018-05-12.
 */
@Controller
public class IndexController {


    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/")
    public ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("questions",questionService.getLatestQuestions(0,3));
        return modelAndView;
    }



}

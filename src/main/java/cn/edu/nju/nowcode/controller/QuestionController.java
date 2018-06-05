package cn.edu.nju.nowcode.controller;

import cn.edu.nju.nowcode.service.QuestionService;
import cn.edu.nju.nowcode.service.impl.UserContext;
import cn.edu.nju.nowcode.vo.QuestionVO;
import cn.edu.nju.nowcode.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cong on 2018-05-22.
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserContext userContext;

    @RequestMapping(value = "/question/add",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO addQuestion(@RequestParam String title,@RequestParam String content){
        QuestionVO questionVO=new QuestionVO();
        questionVO.setContent(content);
        questionVO.setTitle(title);
        questionVO.setUserId(userContext.getUser().getUsername());
        return questionService.addQuestion(questionVO);
    }

}

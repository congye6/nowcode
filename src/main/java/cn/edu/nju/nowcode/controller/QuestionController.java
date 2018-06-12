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
@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserContext userContext;

    @RequestMapping(value = "/question/add",method = RequestMethod.POST)
    public ResponseVO addQuestion(@RequestParam String title,@RequestParam String content){
        QuestionVO questionVO=new QuestionVO();
        questionVO.setContent(content);
        questionVO.setTitle(title);
        questionVO.setUserId(userContext.getUser().getUsername());
        return questionService.addQuestion(questionVO);
    }

    @RequestMapping(value = "/question/latest/{offset}/{nums}",method = RequestMethod.GET)
    public ResponseVO queryLatestQuestion(@PathVariable Integer offset,@PathVariable Integer nums){
        return ResponseVO.buildSuccess(questionService.getLatestQuestions(offset,nums));
    }

    @RequestMapping(value = "/question/delete/{questionId}",method = RequestMethod.POST)
    public ResponseVO deleteQuestion(@PathVariable Long questionId){
        return questionService.deleteQuestion(questionId);
    }

    @RequestMapping(value = "/question/detail/{questionId}",method = RequestMethod.GET)
    public ResponseVO queryQuestionDetail(@PathVariable Long questionId){
        return questionService.getQuestionDetail(questionId);
    }
}

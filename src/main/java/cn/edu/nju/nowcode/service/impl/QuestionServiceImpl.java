package cn.edu.nju.nowcode.service.impl;

import cn.edu.nju.nowcode.mapper.QuestionMapper;
import cn.edu.nju.nowcode.service.QuestionService;
import cn.edu.nju.nowcode.vo.QuestionVO;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cong on 2018-05-17.
 */
@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<QuestionVO> getLatestQuestions(Long id, int offset, int nums) {
        if(id==null||id<=0)
            return new ArrayList<>();
        List<QuestionVO> questions=questionMapper.getLatestQuestions(id,offset,nums);
        return questions;
    }

    @Override
    public List<QuestionVO> getLatestQuestions(int offset, int nums) {
        return questionMapper.getLatestQuestions(0,offset,nums);
    }
}

package cn.edu.nju.nowcode.service;

import cn.edu.nju.nowcode.vo.QuestionVO;

import java.util.List;

/**
 * Created by cong on 2018-05-17.
 */
public interface QuestionService {


    /**
     * 查询某个用户最新的几个问题
     * @param id
     * @param offset
     * @param nums
     * @return
     */
    public List<QuestionVO> getLatestQuestions(Long id,int offset,int nums);

}

package cn.edu.nju.nowcode.service;

import cn.edu.nju.nowcode.vo.QuestionVO;
import cn.edu.nju.nowcode.vo.ResponseVO;

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


    /**
     * 查询全站最新的几个问题
     * @param offset
     * @param nums
     * @return
     */
    public List<QuestionVO> getLatestQuestions(int offset,int nums);

    /**
     * 添加问题
     * 敏感词过滤
     * @param questionVO
     */
    public ResponseVO addQuestion(QuestionVO questionVO);

    /**
     * 删除问题
     * @param questionId
     * @return
     */
    public ResponseVO deleteQuestion(Long questionId);

    /**
     * 更新评论数量
     * @param questionId
     * @param commentCount
     * @return
     */
    public ResponseVO updateCommentCount(Long questionId,int commentCount);

    /**
     * 获取问题的详细信息，包括所有评论
     * @param questionId
     * @return
     */
    public ResponseVO getQuestionDetail(Long questionId);



}

package cn.edu.nju.nowcode.vo;

import java.util.List;

/**
 * Created by cong on 2018-06-12.
 */
public class QuestionDetailVO{


    private QuestionVO questionVO;

    private List<CommentShowVO> comments;


    public QuestionVO getQuestionVO() {
        return questionVO;
    }

    public void setQuestionVO(QuestionVO questionVO) {
        this.questionVO = questionVO;
    }

    public List<CommentShowVO> getComments() {
        return comments;
    }

    public void setComments(List<CommentShowVO> comments) {
        this.comments = comments;
    }
}

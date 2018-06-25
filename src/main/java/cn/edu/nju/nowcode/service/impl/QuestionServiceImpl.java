package cn.edu.nju.nowcode.service.impl;

import cn.edu.nju.nowcode.mapper.QuestionMapper;
import cn.edu.nju.nowcode.service.CommentService;
import cn.edu.nju.nowcode.service.LikeService;
import cn.edu.nju.nowcode.service.QuestionService;
import cn.edu.nju.nowcode.service.SensitiveService;
import cn.edu.nju.nowcode.vo.CommentShowVO;
import cn.edu.nju.nowcode.vo.QuestionDetailVO;
import cn.edu.nju.nowcode.vo.QuestionVO;
import cn.edu.nju.nowcode.vo.ResponseVO;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cong on 2018-05-17.
 */
@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private SensitiveService sensitiveService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LikeService likeService;

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

    @Override
    public QuestionVO getQuestionById(Long questionId) {
        return questionMapper.selectByPrimaryKey(questionId);
    }

    @Override
    public ResponseVO addQuestion(QuestionVO questionVO) {

        if(StringUtils.isEmpty(questionVO.getUserId()))
            return ResponseVO.buildFailure("用户登录失效，请重新登录");
        if(StringUtils.isEmpty(questionVO.getTitle()))
            return ResponseVO.buildFailure("问题标题不能为空");

        //去除网页敏感词
        questionVO.setTitle(sensitiveService.replaceSensitive(questionVO.getTitle()));
        questionVO.setContent(sensitiveService.replaceSensitive(questionVO.getContent()));

        questionMapper.insertSelective(questionVO);
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO deleteQuestion(Long questionId) {
        questionMapper.updateDelFlag(questionId,true);
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO updateCommentCount(Long questionId, int commentCount) {
        questionMapper.updateCommentCount(questionId,commentCount);
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO getQuestionDetail(Long questionId) {
        QuestionDetailVO questionDetail=new QuestionDetailVO();
        questionDetail.setLikeCount(likeService.count("Question",questionId));
        QuestionVO question=questionMapper.selectByPrimaryKey(questionId);
        if(question==null)
            return ResponseVO.buildFailure("问题不存在");
        questionDetail.setQuestionVO(question);
        List<CommentShowVO> comments=(List<CommentShowVO>)commentService.queryCommentByQuestion(questionId,0l,20).getContent();
        questionDetail.setComments(comments);
        return ResponseVO.buildSuccess(questionDetail);
    }
}

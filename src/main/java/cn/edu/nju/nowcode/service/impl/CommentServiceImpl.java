package cn.edu.nju.nowcode.service.impl;

import cn.edu.nju.nowcode.mapper.CommentMapper;
import cn.edu.nju.nowcode.service.CommentService;
import cn.edu.nju.nowcode.service.QuestionService;
import cn.edu.nju.nowcode.service.SensitiveService;
import cn.edu.nju.nowcode.vo.CommentShowVO;
import cn.edu.nju.nowcode.vo.CommentVO;
import cn.edu.nju.nowcode.vo.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cong on 2018-06-07.
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private SensitiveService sensitiveService;

    @Autowired
    private QuestionService questionService;

    @Override
    public ResponseVO addComment(CommentVO commentVO) {

        if(commentVO==null||!commentVO.isValid())
            return ResponseVO.buildFailure("参数错误");

        commentVO.setContent(sensitiveService.replaceSensitive(commentVO.getContent()));
        commentMapper.insertSelective(commentVO);

        if("Question".equals(commentVO.getEntityType())){//更新评论数量
            int commentCount=this.commentCount(commentVO.getEntityId(),"Question");
            questionService.updateCommentCount(commentVO.getEntityId(),commentCount);
        }

        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO queryCommentByQuestion(Long questionId,Long offset,int limit) {

        if(questionId==null||questionId<=0)
            return ResponseVO.buildFailure("找不到相关问题");

        List<CommentVO> comments=commentMapper.selectByEntityId(questionId,offset,limit,"Question");
        List<CommentShowVO> commentShowVOS=new ArrayList<>();
        for(CommentVO commentVO:comments){
            CommentShowVO comment=new CommentShowVO();
            BeanUtils.copyProperties(commentVO,comment);
            if(comment.getId()==null||comment.getId()<=0)
                return ResponseVO.buildFailure("系统错误");
            List<CommentVO> commentsOfComment=commentMapper.selectByEntityId(comment.getId(),0l,5,"Comment");
            comment.setComments(commentsOfComment);
            commentShowVOS.add(comment);
        }

        return ResponseVO.buildSuccess(commentShowVOS);
    }

    @Override
    public ResponseVO queryCommentByComment(Long commentId, Long offset, int limit) {
        List<CommentVO> commentsOfComment=commentMapper.selectByEntityId(commentId,offset,limit,"Comment");
        return ResponseVO.buildSuccess(commentsOfComment);
    }

    @Override
    public int commentCount(Long entityId,String entityType) {
        return commentMapper.selectCount(entityId,entityType);
    }

    @Override
    public ResponseVO deleteComment(Long commentId) {
        if(commentId==null||commentId<=0)
            return ResponseVO.buildFailure("该评论不存在");
        commentMapper.updateDelFlag(commentId,true);
        return ResponseVO.buildSuccess();
    }
}

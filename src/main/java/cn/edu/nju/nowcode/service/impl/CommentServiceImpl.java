package cn.edu.nju.nowcode.service.impl;

import cn.edu.nju.nowcode.mapper.CommentMapper;
import cn.edu.nju.nowcode.service.CommentService;
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

    @Override
    public ResponseVO addComment(CommentVO commentVO) {

        if(commentVO==null||!commentVO.isValid())
            return ResponseVO.buildFailure("参数错误");

        commentVO.setContent(sensitiveService.replaceSensitive(commentVO.getContent()));
        commentMapper.insertSelective(commentVO);

        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO queryCommentByQuestion(Long questionId) {

        if(questionId==null||questionId<=0)
            return ResponseVO.buildFailure("找不到相关问题");

        List<CommentVO> comments=commentMapper.selectByEntityId(questionId);
        List<CommentShowVO> commentShowVOS=new ArrayList<>();
        for(CommentVO commentVO:comments){
            CommentShowVO comment=new CommentShowVO();
            BeanUtils.copyProperties(commentVO,comment);
            if(comment.getId()==null||comment.getId()<=0)
                return ResponseVO.buildFailure("系统错误");
            List<CommentVO> commentsOfComment=commentMapper.selectByEntityId(comment.getId());
            comment.setComments(commentsOfComment);
        }

        return ResponseVO.buildSuccess(commentShowVOS);
    }

    @Override
    public int commentCount(Long entityId) {
        return commentMapper.selectCount(entityId);
    }

    @Override
    public ResponseVO deleteComment(Long commentId) {
        if(commentId==null||commentId<=0)
            return ResponseVO.buildFailure("该评论不存在");
        commentMapper.updateDelFlag(commentId,true);
        return ResponseVO.buildSuccess();
    }
}

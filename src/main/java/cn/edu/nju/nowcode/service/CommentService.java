package cn.edu.nju.nowcode.service;

import cn.edu.nju.nowcode.vo.CommentVO;
import cn.edu.nju.nowcode.vo.ResponseVO;

/**
 * Created by cong on 2018-06-07.
 * 评论
 */
public interface CommentService {

    /**
     * 添加评论
     * @param commentVO
     * @return
     */
    public ResponseVO addComment(CommentVO commentVO);

    /**
     * 查询某个问题的所有评论，包括评论的评论
     * @param questionId
     * @return
     */
    public ResponseVO queryCommentByQuestion(Long questionId,Long offset,int limit);

    /**
     * 查询评论的评论
     * @param commentId
     * @param offset
     * @param limit
     * @return
     */
    public ResponseVO queryCommentByComment(Long commentId,Long offset,int limit);

    /**
     * 查询评论数量
     * @param entityId
     * @return
     */
    public int commentCount(Long entityId,String entityType);

    /**
     * 删除评论，软删除
     * @param commentId
     * @return
     */
    public ResponseVO deleteComment(Long commentId);


}

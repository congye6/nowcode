package cn.edu.nju.nowcode.service;

import cn.edu.nju.nowcode.vo.LikeVO;
import cn.edu.nju.nowcode.vo.ResponseVO;

/**
 * 点赞
 */
public interface LikeService {


    /**
     * 点赞
     * @param likeVO
     * @return
     */
    public ResponseVO like(LikeVO likeVO);

    /**
     * 取消点赞
     * @param likeVO
     * @return
     */
    public ResponseVO dislike(LikeVO likeVO);

    /**
     * 点赞数量
     * @param entityType
     * @param entityId
     * @return
     */
    public Long count(String entityType,Long entityId);

    /**
     * 获取用户被赞次数
     * @param userId
     * @return
     */
    public Integer userLikeCount(String userId);


}

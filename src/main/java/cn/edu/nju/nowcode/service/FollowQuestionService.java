package cn.edu.nju.nowcode.service;

import cn.edu.nju.nowcode.vo.ResponseVO;

/**
 * 关注问题
 */
public interface FollowQuestionService {

    /**
     * 关注问题
     * @param userId
     * @param questionId
     * @return
     */
    public ResponseVO follow(String userId,Long questionId);

    /**
     * 取消关注
     * @param userId
     * @param questionId
     * @return
     */
    public ResponseVO unfollow(String userId,Long questionId);

    /**
     * 获取所有粉丝基本信息
     * @param questionId
     * @return
     */
    public ResponseVO getFans(Long questionId,Long start,Long end);

    /**
     * 获取粉丝数量
     * @param questionId
     * @return
     */
    public Integer getFansCount(Long questionId);

}

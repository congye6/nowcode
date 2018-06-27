package cn.edu.nju.nowcode.service;

import cn.edu.nju.nowcode.vo.ResponseVO;

/**
 * 关注用户
 */
public interface FollowUserService {

    /**
     * 关注某人
     * @param userId
     * @param followerId
     * @return
     */
    public ResponseVO follow(String userId,String followerId);

    /**
     * 取消关注
     * @param userId
     * @param followerId
     * @return
     */
    public ResponseVO unfollow(String userId,String followerId);

    /**
     * 获取所有关注的人的基本信息
     * @param userId
     * @return
     */
    public ResponseVO getFollowers(String userId,Long start,Long end);

    /**
     * 获取所有粉丝的基本信息
     * @param userId
     * @return
     */
    public ResponseVO getFans(String userId,Long start,Long end);

    /**
     * 获取粉丝数量
     * @param userId
     * @return
     */
    public Integer getFansCount(String userId);

    /**
     * 是否已关注
     * @param userId
     * @param followerId
     * @return
     */
    public Boolean isFollower(String userId,String followerId);
}

package cn.edu.nju.nowcode.vo;

/**
 * 关注的人或粉丝的基本信息
 */
public class UserInfoVO {

    /**
     * 用户名称
     */
    private String userId;

    /**
     * 粉丝数量
     */
    private Integer fansCount;

    /**
     * 关注的人数量
     */
    private Integer followerCount;

    /**
     * 发布的问题数量
     */
    private Integer questionCount;

    /**
     * 被赞数量
     */
    private Integer likeCount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
}

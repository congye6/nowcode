package cn.edu.nju.nowcode.vo;

import java.util.List;

/**
 * Created by cong on 2018-06-07.
 */
public class CommentShowVO extends CommentVO{


    private List<CommentVO> comments;

    /**
     * 点赞数量
     */
    private Long likeCount;

    public List<CommentVO> getComments() {
        return comments;
    }

    public void setComments(List<CommentVO> comments) {
        this.comments = comments;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }
}

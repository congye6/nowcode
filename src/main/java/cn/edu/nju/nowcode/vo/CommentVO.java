package cn.edu.nju.nowcode.vo;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

public class CommentVO implements Serializable {
    private Long id;

    private Long entityId;

    private String userId;

    private Date createTime;

    private Date updateTime;

    private String entityType;

    private String content;

    private static final long serialVersionUID = 1L;

    public boolean isValid(){
        return entityId!=null&&entityId>0&& !StringUtils.isEmpty(userId)&&!StringUtils.isEmpty(content);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType == null ? null : entityType.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
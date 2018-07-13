package cn.edu.nju.nowcode.vo;

import cn.edu.nju.nowcode.enumeration.EventType;

import java.util.Date;

/**
 * Created by cong on 2018-07-09.
 * 新鲜事
 */
public class NewsVO {

    private Long id;

    /**
     * 发生新鲜事的人
     */
    private String userId;

    /**
     * 新鲜事类型
     */
    private EventType type;

    /**
     * 添加时间
     */
    private Date addTime;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

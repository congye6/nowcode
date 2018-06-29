package cn.edu.nju.nowcode.vo;

import java.io.Serializable;
import java.util.Date;

public class MessageVO implements Serializable {
    private Long id;

    private String fromId;

    private String toId;

    private Date createTime;

    private String conversationId;

    private String content;

    private boolean hasRead;

    private static final long serialVersionUID = 1L;

    public MessageVO() {
    }

    public MessageVO(String fromId, String toId, String content) {
        this.fromId = fromId;
        this.toId = toId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public boolean getHasRead() {
        return hasRead;
    }

    public void setHasRead(boolean hasRead) {
        this.hasRead = hasRead;
    }
}
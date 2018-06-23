package cn.edu.nju.nowcode.vo;

import cn.edu.nju.nowcode.enumeration.EventType;

import java.util.Map;

public class EventVO {

    /**
     * 事件类型
     */
    private EventType eventType;

    /**
     * 触发事件的用户
     */
    private String actorId;

    /**
     * 涉及的entity，问题或者评论
     */
    private String entityType;

    private Long entityId;

    private String entityOwnerId;

    private Map<String,Object> extData;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getActorId() {
        return actorId;
    }

    public void setActorId(String actorId) {
        this.actorId = actorId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityOwnerId() {
        return entityOwnerId;
    }

    public void setEntityOwnerId(String entityOwnerId) {
        this.entityOwnerId = entityOwnerId;
    }

    public Map<String, Object> getExtData() {
        return extData;
    }

    public void setExtData(Map<String, Object> extData) {
        this.extData = extData;
    }
}

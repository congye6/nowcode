package cn.edu.nju.nowcode.service;

import cn.edu.nju.nowcode.vo.MessageVO;
import cn.edu.nju.nowcode.vo.ResponseVO;

/**
 * 站内信管理
 */
public interface MessageService {

    /**
     * 发送消息
     * @param message
     * @return
     */
    public ResponseVO sendMessage(MessageVO message);

    /**
     * 获取用户最新消息，相同conversationId的消息只展现最新的一条
     * 需要将消息按时间排序
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    public ResponseVO getLatestMessage(String userId,int offset,int limit);

    /**
     * 获取某个conversation的所有消息
     * @param conversationId
     * @param offset
     * @param limit
     * @return
     */
    public ResponseVO getConversationDetail(String conversationId,int offset,int limit);

}

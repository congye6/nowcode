package cn.edu.nju.nowcode.service.impl;

import cn.edu.nju.nowcode.mapper.MessageMapper;
import cn.edu.nju.nowcode.service.MessageService;
import cn.edu.nju.nowcode.service.UserService;
import cn.edu.nju.nowcode.vo.MessageVO;
import cn.edu.nju.nowcode.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserService userService;

    @Override
    public ResponseVO sendMessage(MessageVO message) {
        if(StringUtils.isEmpty(message.getContent()))
            return ResponseVO.buildFailure("消息内容不能为空");
        if(!userService.isExist(message.getToId()))
            return ResponseVO.buildFailure("收件人不存在");
        message.setConversationId(generateConversationId(message));
        messageMapper.insertSelective(message);
        return ResponseVO.buildSuccess();
    }

    private String generateConversationId(MessageVO messageVO){
        String fromId=messageVO.getFromId();
        String toId=messageVO.getToId();
        String conversationId="";
        if(fromId.compareTo(toId)<0){
            conversationId=fromId+"_"+toId;
        }else{
            conversationId=toId+"_"+fromId;
        }
        return conversationId;
    }

    @Override
    public ResponseVO getLatestMessage(String userId, int offset, int limit) {
        return ResponseVO.buildSuccess(messageMapper.selectByUser(userId,offset,limit));
    }

    @Override
    public ResponseVO getConversationDetail(String conversationId, int offset, int limit) {
        return ResponseVO.buildSuccess(messageMapper.selectByConversation(conversationId,offset,limit));
    }
}

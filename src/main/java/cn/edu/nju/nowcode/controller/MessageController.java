package cn.edu.nju.nowcode.controller;

import cn.edu.nju.nowcode.service.MessageService;
import cn.edu.nju.nowcode.service.impl.UserContext;
import cn.edu.nju.nowcode.vo.MessageVO;
import cn.edu.nju.nowcode.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {


    @Autowired
    private MessageService messageService;

    @Autowired
    private UserContext userContext;

    @RequestMapping(value = "/message/add",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO sendMessage(@RequestBody MessageVO message){
        String fromId=userContext.getUser().getUsername();
        message.setFromId(fromId);
        return messageService.sendMessage(message);
    }

    @RequestMapping(value = "/message/user/{userId}/{offset}/{limit}",method = RequestMethod.GET)
    public ResponseVO getLatestMessage(@PathVariable String userId,
                                       @PathVariable int offset,@PathVariable int limit){
        return messageService.getLatestMessage(userId,offset,limit);
    }

    @RequestMapping("/message/detail/{conversationId}/{offset}/{limit}")
    public ResponseVO getConversationDetail(@PathVariable String conversationId,@PathVariable int offset,
                                            @PathVariable int limit){
        return messageService.getConversationDetail(conversationId,offset,limit);
    }


}

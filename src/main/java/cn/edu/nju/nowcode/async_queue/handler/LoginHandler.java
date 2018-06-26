package cn.edu.nju.nowcode.async_queue.handler;

import cn.edu.nju.nowcode.async_queue.EventHandler;
import cn.edu.nju.nowcode.enumeration.EventType;
import cn.edu.nju.nowcode.service.EmailService;
import cn.edu.nju.nowcode.vo.EmailVO;
import cn.edu.nju.nowcode.vo.EventVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 登录事件处理
 */
@Service
public class LoginHandler implements EventHandler {

    private static final List<EventType> SUPPORT_TYPES= Arrays.asList(EventType.LOGIN);

    private EmailService emailService;

    @Override
    public void handle(EventVO event) {
        String email=(String)event.getExtData().get("email");
        EmailVO emailVO=new EmailVO().createNewEmail(email,"welcome to nowcode","welcome to nowcode");
        emailService.sendEmail(emailVO);
    }

    @Override
    public boolean isSupport(EventType eventType) {
        return SUPPORT_TYPES.contains(eventType);
    }

    @Override
    public List<EventType> supportTypes() {
        return new ArrayList<>(SUPPORT_TYPES);
    }
}

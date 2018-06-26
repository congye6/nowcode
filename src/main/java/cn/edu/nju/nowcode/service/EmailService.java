package cn.edu.nju.nowcode.service;

import cn.edu.nju.nowcode.vo.EmailVO;

/**
 * Created by cong on 2017-11-13.
 */
public interface EmailService {

    /**
     * 发送邮件
     * @param email
     * @return
     */
    public void sendEmail(EmailVO email);

    /**
     * 异步发送邮件
     * @param email
     * @return
     */
    public void sendEmailAsync(EmailVO email);

}

package cn.edu.nju.nowcode.vo;

/**
 * Created by cong on 2017-11-13.
 */
public class EmailVO {

    private String receiveMail;        //接收人邮箱
    private String subject;            //标题
    private String content;            //内容

    public EmailVO createNewEmail(String receiveMail, String subject, String content) {
        EmailVO e = new EmailVO();
        e.receiveMail = receiveMail;
        e.subject = subject;
        e.content = content;
        return e;
    }

    public String getReceiveMail() {
        return receiveMail;
    }

    public void setReceiveMail(String receiveMail) {
        this.receiveMail = receiveMail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
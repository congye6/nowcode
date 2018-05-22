package cn.edu.nju.nowcode.vo;

/**
 * Created by cong on 2017-11-13.
 * rest调用的返回值
 */
public class ResponseVO {

    private int code;

    /**
     * 调用是否成功
     */
    private boolean success;

    /**
     * 返回的提示信息
     */
    private String message;

    /**
     * 内容
     */
    private Object content;



    public static ResponseVO buildSuccess(){
        return buildSuccess(null);
    }

    public static ResponseVO buildSuccess(Object content){
        ResponseVO response=new ResponseVO();
        response.setContent(content);
        response.setSuccess(true);
        response.setCode(999);
        return response;
    }

    public static ResponseVO buildFailure(String message){
        ResponseVO response=new ResponseVO();
        response.setSuccess(false);
        response.setMessage(message);
        System.out.println(message);
        return response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
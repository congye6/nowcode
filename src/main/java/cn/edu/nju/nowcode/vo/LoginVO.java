package cn.edu.nju.nowcode.vo;

import org.springframework.beans.BeanUtils;

/**
 * Created by cong on 2018-05-19.
 */
public class LoginVO {

    private String username;

    private String password;

    public UserVO getUserVO(){
        UserVO userVO=new UserVO();
        BeanUtils.copyProperties(this,userVO);
        return userVO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

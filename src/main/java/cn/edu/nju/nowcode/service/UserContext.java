package cn.edu.nju.nowcode.service;

import cn.edu.nju.nowcode.vo.UserVO;
import org.springframework.stereotype.Service;

/**
 * Created by cong on 2018-05-21.
 * 保存用户信息的上下文
 */
@Service
public class UserContext {

    private ThreadLocal<UserVO> threadLocal=new ThreadLocal<>();

    public UserVO getUser(){
        return threadLocal.get();
    }

    public void setUser(UserVO user){
        threadLocal.set(user);
    }

    public void clear(){
        threadLocal.remove();
    }


}

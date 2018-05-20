package cn.edu.nju.nowcode.service;

import cn.edu.nju.nowcode.vo.ResponseVO;
import cn.edu.nju.nowcode.vo.UserVO;

/**
 * Created by cong on 2018-05-18.
 */
public interface LoginService {

    /**
     * 注册用户
     * 检查用户名和密码是否符合要求
     * 密码salt加密
     * @param userVO
     * @return
     */
    public ResponseVO register(UserVO userVO);


    /**
     * 用户登录
     * @param userVO
     * @return
     */
    public ResponseVO login(UserVO userVO);

}

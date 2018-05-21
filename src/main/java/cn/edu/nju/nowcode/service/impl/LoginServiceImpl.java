package cn.edu.nju.nowcode.service.impl;

import cn.edu.nju.nowcode.service.LoginService;
import cn.edu.nju.nowcode.service.UserService;
import cn.edu.nju.nowcode.util.SecurityUtil;
import cn.edu.nju.nowcode.util.UUIDUtil;
import cn.edu.nju.nowcode.vo.ResponseVO;
import cn.edu.nju.nowcode.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * Created by cong on 2018-05-18.
 */
@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private UserService userService;

    @Override
    public ResponseVO register(UserVO userVO) {
        ResponseVO responseVO=isValid(userVO);
        if(!responseVO.getSuccess())
            return responseVO;
        UserVO userVOInDb=userService.getUserByName(userVO.getUsername());
        if(userVOInDb!=null)
            return ResponseVO.buildFailure("用户名已存在");
        userVO.setSalt(UUIDUtil.uuid());
        userVO.setPassword(SecurityUtil.md5(userVO.getPassword(),userVO.getSalt()));
        userService.addUser(userVO);
        return ResponseVO.buildSuccess(userVO);
    }

    @Override
    public ResponseVO login(UserVO userVO) {
        ResponseVO responseVO=isValid(userVO);
        if(!responseVO.getSuccess())
            return responseVO;
        UserVO userVOInDb=userService.getUserByName(userVO.getUsername());
        if(userVOInDb==null)
            return ResponseVO.buildFailure("用户不存在");
        String password=SecurityUtil.md5(userVO.getPassword(),userVOInDb.getSalt());
        if(!password.equals(userVOInDb.getPassword()))
            return ResponseVO.buildFailure("密码错误");
        return ResponseVO.buildSuccess(userVOInDb);
    }


    /**
     * 校验用户名密码的格式
     * @param userVO
     * @return
     */
    private ResponseVO isValid(UserVO userVO){
        if(userVO==null)
            return ResponseVO.buildFailure("系统错误，请重新输入");
        if(StringUtils.isEmpty(userVO.getUsername()))
            return ResponseVO.buildFailure("用户名不能为空");
        if(StringUtils.isEmpty(userVO.getPassword()))
            return ResponseVO.buildFailure("密码不能为空");
        return ResponseVO.buildSuccess();
    }
}

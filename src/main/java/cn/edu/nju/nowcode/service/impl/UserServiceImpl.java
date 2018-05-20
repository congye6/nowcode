package cn.edu.nju.nowcode.service.impl;

import cn.edu.nju.nowcode.mapper.UserMapper;
import cn.edu.nju.nowcode.service.UserService;
import cn.edu.nju.nowcode.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by cong on 2018-05-18.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper mapper;

    @Override
    public UserVO getUserByName(String username) {
        if(StringUtils.isEmpty(username))
            return null;
        return mapper.selectByName(username);
    }

    @Override
    public UserVO getUserById(Long id) {
        if(id==null||id<=0)
            return null;
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void addUser(UserVO userVO) {
        mapper.insertSelective(userVO);
    }
}
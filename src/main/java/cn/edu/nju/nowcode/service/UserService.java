package cn.edu.nju.nowcode.service;

import cn.edu.nju.nowcode.vo.UserInfoVO;
import cn.edu.nju.nowcode.vo.UserVO;

/**
 * Created by cong on 2018-05-18.
 */
public interface UserService {


    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    public UserVO getUserByName(String username);


    /**
     * 根据id查找
     * @param id
     * @return
     */
    public UserVO getUserById(Long id);

    /**
     * 保存user
     * @param userVO
     */
    public void addUser(UserVO userVO);

    /**
     * 用户是否存在
     * @param userId
     * @return
     */
    public boolean isExist(String userId);

    /**
     * 获取用户的概要信息
     * @param userId
     * @return
     */
    public UserInfoVO getUserInfo(String userId);

}

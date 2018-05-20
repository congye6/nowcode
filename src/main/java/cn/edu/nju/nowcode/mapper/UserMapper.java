package cn.edu.nju.nowcode.mapper;

import cn.edu.nju.nowcode.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {


    int insertSelective(UserVO record);

    UserVO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserVO record);

    UserVO selectByName(String username);

}
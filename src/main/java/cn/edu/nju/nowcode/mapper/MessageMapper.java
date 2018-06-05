package cn.edu.nju.nowcode.mapper;

import cn.edu.nju.nowcode.vo.MessageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MessageMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(MessageVO record);

    MessageVO selectByPrimaryKey(Long id);

    void updateHasRead(@Param("hasRead") boolean hasRead,@Param("id") Long id);

}
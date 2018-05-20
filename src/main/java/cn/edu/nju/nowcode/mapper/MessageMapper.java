package cn.edu.nju.nowcode.mapper;

import cn.edu.nju.nowcode.vo.MessageVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessageVO record);

    int insertSelective(MessageVO record);

    MessageVO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageVO record);

    int updateByPrimaryKeyWithBLOBs(MessageVO record);

    int updateByPrimaryKey(MessageVO record);
}
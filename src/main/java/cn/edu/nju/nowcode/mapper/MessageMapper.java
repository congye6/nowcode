package cn.edu.nju.nowcode.mapper;

import cn.edu.nju.nowcode.vo.MessageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {
    int insertSelective(MessageVO record);

    MessageVO selectByPrimaryKey(Long id);

    void updateHasRead(@Param("hasRead") boolean hasRead,@Param("id") Long id);

    List<MessageVO> selectByUser(@Param("toId") String toId,@Param("offset") int offset,@Param("limit") int limit);

    List<MessageVO> selectByConversation(@Param("conversationId") String conversationId,
                                         @Param("offset") int offset,@Param("limit") int limit);
}
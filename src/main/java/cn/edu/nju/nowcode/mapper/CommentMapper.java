package cn.edu.nju.nowcode.mapper;

import cn.edu.nju.nowcode.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    int insertSelective(CommentVO record);

    CommentVO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommentVO record);

    List<CommentVO> selectByEntityId(Long entityId);

    void updateDelFlag(@Param("id")Long id, @Param("delFlag")boolean delFlag);

    int selectCount(Long entityId);
}
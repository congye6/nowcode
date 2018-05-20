package cn.edu.nju.nowcode.mapper;

import cn.edu.nju.nowcode.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommentVO record);

    int insertSelective(CommentVO record);

    CommentVO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommentVO record);

    int updateByPrimaryKeyWithBLOBs(CommentVO record);

    int updateByPrimaryKey(CommentVO record);
}
package cn.edu.nju.nowcode.mapper;

import cn.edu.nju.nowcode.vo.QuestionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(QuestionVO record);

    QuestionVO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QuestionVO record);

    int updateByPrimaryKeyWithBLOBs(QuestionVO record);

    int updateByPrimaryKey(QuestionVO record);

    List<QuestionVO> getLatestQuestions(@Param("userId") long userId,
                                        @Param("offset") int offset,@Param("limit") int limit);
}
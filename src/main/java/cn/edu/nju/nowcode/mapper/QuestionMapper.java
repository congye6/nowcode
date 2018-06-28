package cn.edu.nju.nowcode.mapper;

import cn.edu.nju.nowcode.vo.QuestionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {

    int insertSelective(QuestionVO record);

    QuestionVO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QuestionVO record);


    List<QuestionVO> getLatestQuestions(@Param("userId") long userId,
                                        @Param("offset") int offset,@Param("limit") int limit);

    void updateDelFlag(@Param("id") Long id,@Param("delFlag") boolean delFlag);

    void updateCommentCount(@Param("id") Long id,@Param("count") Integer count);

    Integer getQuestionCountByUser(String userId);
}
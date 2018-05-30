package cn.edu.nju.nowcode.mapper;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SensitiveMapper {
    int insert(String word);

    List<String> selectAllSensitive();
}
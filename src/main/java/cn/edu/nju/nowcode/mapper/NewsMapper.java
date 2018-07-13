package cn.edu.nju.nowcode.mapper;

import cn.edu.nju.nowcode.vo.NewsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

/**
 * Created by cong on 2018-07-10.
 */
@Mapper
public interface NewsMapper {

    public void insertNews(NewsVO news);

    public List<NewsVO> getNewsByUser(@Param("userIds") List<String> userIds,
                                      @Param("maxId") Long maxId,@Param("limit") int limit);

    public void deleteNews(int id);

}

package cn.edu.nju.nowcode.service;

import cn.edu.nju.nowcode.vo.NewsVO;
import cn.edu.nju.nowcode.vo.ResponseVO;

/**
 * Created by cong on 2018-07-09.
 * 新鲜事
 */
public interface NewsService {

    /**
     * 添加新鲜事
     * @param news
     * @return
     */
    public ResponseVO addNews(NewsVO news);

    /**
     * 获取某位用户的好友新鲜事
     * @param userId
     * @return
     */
    public ResponseVO getNewsOfUser(String userId,long maxId,int limit);

}

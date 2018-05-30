package cn.edu.nju.nowcode.service;

import java.util.List;

/**
 * Created by cong on 2018-05-23.
 */
public interface SensitiveService {

    /**
     * 添加一个敏感词
     * @param word
     */
    public void addSensitive(String word);


    /**
     * 获取所有敏感词
     * @return
     */
    public List<String> getAllSensitive();

    /**
     * 刷新缓存
     */
    public void refresh();

    /**
     * 将text中的敏感词替换
     * @param text
     * @return
     */
    public String replaceSensitive(String text);
}

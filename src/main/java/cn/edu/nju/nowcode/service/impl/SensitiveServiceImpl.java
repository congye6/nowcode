package cn.edu.nju.nowcode.service.impl;

import cn.edu.nju.nowcode.mapper.SensitiveMapper;
import cn.edu.nju.nowcode.service.SensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by cong on 2018-05-23.
 */
@Service
public class SensitiveServiceImpl implements SensitiveService{

    private static final String REPLACEMENT="***";

    @Autowired
    private SensitiveMapper sensitiveMapper;

    private static final TrieNode ROOT=new TrieNode();

    @Override
    public void addSensitive(String word) {
        if(StringUtils.isEmpty(word))
            return;
        try{
            sensitiveMapper.insert(word);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<String> getAllSensitive() {
        return sensitiveMapper.selectAllSensitive();
    }

    @Override
    public void refresh() {
        ROOT.clear();
        initSensitiveTrie();
    }

    @Override
    public String replaceSensitive(String text) {
        if(StringUtils.isEmpty(text)||ROOT.isEnd())
            return text;
        StringBuilder result=new StringBuilder();

        for(int i=0;i<text.length();i++){
            int textPointer=i;//遍历每个字符为开始
            TrieNode currentNode=ROOT;
            while(true){
                char c=text.charAt(textPointer);
                if(!currentNode.contains(c)){
                    result.append(text.charAt(i));
                    break;
                }
                currentNode=currentNode.getChild(c);
                textPointer++;

                if(currentNode.isEnd()){//已经检查到敏感词
                    result.append(REPLACEMENT);
                    i=textPointer-1;
                    break;
                }

                if(textPointer==text.length()){//已经检查到末尾
                    result.append(text.charAt(i));
                    break;
                }
            }

        }
        return result.toString();
    }



    @PostConstruct
    public void initSensitiveTrie(){
        List<String> sensitiveWords=sensitiveMapper.selectAllSensitive();
        for(String word:sensitiveWords){
            addWord(word);
        }
    }

    private void addWord(String word){
        if(StringUtils.isEmpty(word))
            return;
        TrieNode current=ROOT;
        for(int i=0;i<word.length();i++){
            Character c=word.charAt(i);
            current.addChild(c);
            current=current.getChild(c);
        }
    }
}

package cn.edu.nju.nowcode.service.impl;

import java.util.*;

/**
 * Created by cong on 2018-05-23.
 */
public class TrieNode {


    private Map<Character,TrieNode> childs=new HashMap<>();


    public TrieNode getChild(Character c){
        return childs.get(c);
    }

    public boolean isEnd(){
        return childs.isEmpty();
    }


    public void addChild(Character c){
        if(c!=null&&childs.get(c)==null){
            childs.put(c,new TrieNode());
        }
    }

    public void clear(){
        childs=new HashMap<>();
    }

    public boolean contains(Character c){
        return childs.containsKey(c);
    }

}

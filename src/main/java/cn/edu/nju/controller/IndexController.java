package cn.edu.nju.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cong on 2018-05-12.
 */
@Controller
public class IndexController {


    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

}

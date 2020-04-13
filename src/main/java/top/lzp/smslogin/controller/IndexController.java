package top.lzp.smslogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Lu
 * @date 2020/4/13:11:34:57
 * @description
 */
@Controller
public class IndexController {
    //接受并处理index请求
    @RequestMapping("/index")
    public String index(){
        return "index.html";
    }

    //登录请求
    @RequestMapping("/login")
    public String login(){
        return "login.html";
    }

    @RequestMapping("/register")
    public String register(){
        return "register.html";
    }
}

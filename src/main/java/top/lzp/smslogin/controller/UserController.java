package top.lzp.smslogin.controller;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lzp.smslogin.entity.User;
import top.lzp.smslogin.service.UserService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;


@Controller
public class UserController {

    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;


    //用户注册
    @RequestMapping("/userRegister")
    public String userRegister(User user){
        System.out.println(user);
        userService.save(user);
        return "redirect:/login";
    }

    //用户登录
    @RequestMapping("/userLogin")
    public String userLogin(User user, Model model){
        boolean success = userService.findUserByPhoneAndPassword(user);
        if (success){
            //找到数据，登录成功
            session.setAttribute("user",user);
            return "redirect:index";
        }else {
            // 没有找到用户，登录失败
            model.addAttribute("error","用户名或密码错误");
            return "/login.html";
        }
    }

    //短信验证码
    @RequestMapping("/sms")
    @ResponseBody
    public String smsCode(String phone){
//        System.out.println(phone);
        //保护机制 如果用户注册过了不发送短信
        //select * from user where phone = #{phone}
        boolean success = userService.findUserByPhone(phone);
        String json = null;
        if (success){
            //该用户没有注册
            String sms = SMS(phone);

            json = "{\"message\":"+true+",\"sms\":\""+sms+"\"}";

        }else {
            //用户注册过了
            json ="{\"message\":"+false+"}";
        }
        return json;
    }




    //发送短信
    private String SMS(String phone){

        //手机号码
        String phoneNumber = phone;
        //短信的内容 正文id
        int templateId = 574976;
        //数组形式 {1} {2}等等，代替验证码等
        String code = "";
        Random r = new Random();
        for (int i = 0;i<4;i++){
            code += r.nextInt(10);
        }
        String[] params = new String[1];
        params[0] = code;
        //将code放到session中

        session.setAttribute("sms",code);
        System.out.println(code);
        //sign为短信签名
        String sign = "luzongpeng";
        //id:1400349533
        //key:6402ddc20e5c50b93f4adb975f1432af
        //拿到发送短信的核心类
        SmsSingleSender ssender = new SmsSingleSender(1400349533,"6402ddc20e5c50b93f4adb975f1432af");
        //发送短信
        try {
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber, templateId, params,
                    sign, "", "");
            System.out.println(result);

        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

}

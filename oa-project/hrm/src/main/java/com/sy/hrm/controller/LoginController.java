package com.sy.hrm.controller;

import com.alibaba.fastjson.JSONObject;
import com.sy.hrm.bean.User;
import com.sy.hrm.constant.WebConstant;
import com.sy.hrm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/tologin")
    public String tologin(){

        return "login";
    }

    @RequestMapping("/loginAjax")
    @ResponseBody
    public String loginAjax(String userId, String password, String code, HttpServletRequest request){
        //由于在生成验证码的时候就已经放到session里面了 在这里可以从session中取出验证码
        String oldCode = (String) request.getSession().getAttribute(WebConstant.VERIFY_CODE);

        //创建json对象
        JSONObject jsonObject = new JSONObject();

        if(oldCode.equalsIgnoreCase(code)){
            User user = loginService.findByNameAndPwd(userId,password);
            if(user != null){
                //把user放入session作用域中
                request.getSession().setAttribute(WebConstant.SESSION_USER,user);

                jsonObject.put("flag","0");
                jsonObject.put("tip","登录成功");
            }else {
                jsonObject.put("flag","1");
                jsonObject.put("tip","登录失败,用户名密码有误");
            }
        }
        return jsonObject.toString();
    }
}

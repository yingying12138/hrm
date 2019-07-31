package com.sy.hrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LogoutController {

    @RequestMapping("/logout")
    public void loginAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //清空session
        request.getSession().invalidate();

        //重定向到登录页面
        response.sendRedirect("/tologin");
    }
}

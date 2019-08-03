package com.elead.controller;


import com.elead.pojo.SmbmsUser;
import com.elead.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;

@SessionAttributes({"user"})
@Controller
@RequestMapping("/jsp")
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(SmbmsUser user, Model mv, HttpServletRequest request) {
//        String usercode = request.getParameter("usercode");
//        String userpassword = request.getParameter("userpassword");
        System.out.println(user);
        SmbmsUser loginInfo = userService.loginInfo(user);
        if (loginInfo != null) {
            mv.addAttribute("user", user);
//            session.setAttribute("userpassword",user.getUserpassword());
            return "redirect:/jsp/frame";
        } else {
            request.setAttribute("status", "1");
            return "../../login";
        }


    }

    @RequestMapping("/logout")
    public String logout(SessionStatus sessionStatus) throws Exception {
        // 清除 @SessionAttributes设置的session
        sessionStatus.setComplete();
        return "redirect:/login.jsp";
    }

    @RequestMapping("/loginjsp")
    public String loginjsp() {

        return "login";
    }

}


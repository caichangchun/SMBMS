package com.elead.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
//    @RequestMapping("/**")
//    public String errorPage() { return "errorPage"; }

    @RequestMapping("/jsp/frame")
    public String frame() { return "frame"; }

    @RequestMapping("/jsp/provideradd")
    public String provideradd() {
        return "provideradd";
    }

    @RequestMapping("/jsp/statistical")
    public String statistical() {
        return "statistical";
    }

    @RequestMapping("/jsp/providerlist")
    public String providerlist() {
        return "providerlist";
    }

    @RequestMapping("/jsp/providerview")
    public String providerview() {
        return "providerview";
    }

    @RequestMapping("/jsp/providermodify")
    public String providemodify() {
        return "providermodify";
    }

    @RequestMapping("/jsp/userlist")
    public String userlist() {
        return "userlist";
    }

    @RequestMapping("/jsp/pwdmodify")
    public String pwdmodify() {
        return "pwdmodify";
    }

    @RequestMapping("/jsp/billadd")
    public String billadd() {
        return "billadd";
    }

    @RequestMapping("/jsp/billlist")
    public String billlist() { return "billlist"; }

    @RequestMapping("/jsp/billmodify")
    public String billmodify() { return "billmodify"; }
}

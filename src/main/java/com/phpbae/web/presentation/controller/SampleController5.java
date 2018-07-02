package com.phpbae.web.presentation.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/security-web")
public class SampleController5 {

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("security-web/index");
        return mav;
    }

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView mav = new ModelAndView("security-web/main");
        return mav;
    }

    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public ModelAndView admin() {
        ModelAndView mav = new ModelAndView("security-web/admin");
        return mav;
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public ModelAndView user() {
        ModelAndView mav = new ModelAndView("security-web/user");
        return mav;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView login(){
        System.out.println("로그인 성공!!");
        ModelAndView mav = new ModelAndView("security-web/main");
        return mav;
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public ModelAndView logout(){
        System.out.println("로그아웃 성공!!");
        ModelAndView mav = new ModelAndView("security-web/index");
        return mav;
    }
}

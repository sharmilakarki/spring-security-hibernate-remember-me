/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sharmila
 */
@Controller
@RequestMapping("/")
public class LogoutController {

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    private ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView();
        HttpSession session = request.getSession(false);
        session.invalidate();
        //Cookie[] cookie = request.getCookies();
//        for(Cookie coo:cookie){
//            coo.setMaxAge(0);
//            coo.setHttpOnly(true);
//            coo.setPath("/login");
//            response.addCookie(coo);
//        }

        Cookie cookie = new Cookie("remember-me", null); // Not necessary, but saves bandwidth.
        cookie.setPath("/login");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0); // Don't set to -1 or it will become a session cookie!
        response.addCookie(cookie);
        model.addObject("message", "Logout Successful");

        model.setViewName("redirect:login");
        return model;

    }
}

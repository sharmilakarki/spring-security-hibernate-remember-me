/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.controller;

import com.sharmila.hibernatespringsecurity.dao.RoleDao;
import com.sharmila.hibernatespringsecurity.dao.UserDao;
import com.sharmila.hibernatespringsecurity.entity.Role;
import com.sharmila.hibernatespringsecurity.service.UserService;
import com.sharmila.hibernatespringsecurity.entity.User;
import com.sharmila.hibernatespringsecurity.entity.UserRoles;
import com.sharmila.hibernatespringsecurity.service.UserRoleService;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class DefaultController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleDao roleDao;
    private Session session;

    @RequestMapping
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public ModelAndView defaultPage(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid user credentials");
            //login from noRemebermePage
            //if error get targetUrl from session again
            String targetUrl = getRememberMeTargetUrlFromSession(request);
            System.out.println(targetUrl);
            if (StringUtils.hasText(targetUrl)) {
                model.addObject("targetUrl", targetUrl);
                model.addObject("loginUpdate", true);
            }

        }
        if (logout != null) {
            model.addObject("message", "Logged out successfully");

        }

        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/user/SignupPage")
    public ModelAndView signup() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("Signup");
        return mv;
    }

    @RequestMapping(value = "/admin/**", method = RequestMethod.GET)
    public ModelAndView adminProfile(Principal principal) {
        String name = principal.getName();
        ModelAndView mv = new ModelAndView();

        mv.addObject("username", name);
        mv.setViewName("adminDashboard");
        System.out.println(name + " is now logged In");

        return mv;
    }

    @RequestMapping(value = {"/userprofile"})
    public ModelAndView userProfile() {

        return new ModelAndView("profile");
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView errorPage() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("error page");
        return model;
    }

    @RequestMapping(value = "/admin/AllUsers")
    public ModelAndView getUsers() {

        return new ModelAndView("AllUsers", "user", userService.getAll());
    }

    @RequestMapping(value = "user/add", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user, BindingResult result) {
        System.out.println("inside insert");
        
            Role role = roleDao.getById(2);
            Set<Role> roles = new HashSet<Role>();
            roles.add(role);
            user.setRole(roles);
            userService.insert(user);
//            System.out.println("Users "+user.toString());
       
        return new ModelAndView ("redirect:/admin/AllUsers");
    }

    @RequestMapping(value = "editUser", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("userAdd") User user, BindingResult result, @RequestParam("id") int id) {
        String view = "";
        User u = userService.getById(id);
        if (u.getId() != 0) {

            Date date = new Date();
            Timestamp t = new Timestamp(date.getTime());
            user.setModifiedDate(t);
            userService.update(user);
//            view="redirect:/adminDashboard";
        }
        System.out.println(user.toString());

        return "redirect:/admin/AllUsers";
    }

    @RequestMapping(value = "delete")
    public ModelAndView deleteUser(@RequestParam int id) {
        userService.delete(id);
        return new ModelAndView("redirect:/admin/AllUsers");
    }

    @RequestMapping(value = "edit")
    public ModelAndView editUser(@RequestParam int id, @ModelAttribute("userEdit") User user) {
        user = userService.getById(id);
        return new ModelAndView("editUser", "user", user);
    }

    @RequestMapping("/info")
    public String info() {
        return "InformationPage";
    }
    //checks if the user is logged in by remember-me cookie, it refers to org.springframework.security.authentication.AuthenticationTrustResolverImpl

    private boolean isRememberMeAuthenticated() {

        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }

        return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
    }
//    With 'true' it creates a session if it does not exist, with 'false' it returns active session if exists otherwise null. It would be more efficient to use 'false', so you don't unnecessarily create sessions, and create sessions only when needed.

    private void setRememberMeTargetUrlToSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("targetUrl", "/admin/update**");
        }
    }

    private String getRememberMeTargetUrlFromSession(HttpServletRequest request) {
        String targetUrl = "";
        HttpSession session = request.getSession(false);
        if (session != null) {
            targetUrl = session.getAttribute("targetUrl") == null ? "" : session.getAttribute("targetUrl").toString();
        }
        return targetUrl;

    }

    /**
     * This update page is for user login with password only. If user is login
     * via remember me cookie, send login to ask for password again. To avoid
     * stolen remember me cookie to update info
     */
    @RequestMapping(value = "/admin/update**", method = RequestMethod.GET)
    public ModelAndView updatePage(HttpServletRequest request) {

        ModelAndView model = new ModelAndView();

        if (isRememberMeAuthenticated()) {
            //send login for update
            setRememberMeTargetUrlToSession(request);
            model.addObject("loginUpdate", true);
            model.setViewName("/login");

        } else {
            model.setViewName("viewForUpdateUsername");
        }

        return model;

    }

}

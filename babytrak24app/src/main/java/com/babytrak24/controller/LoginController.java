package com.babytrak24.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.babytrak24.model.Baby;
import com.babytrak24.model.Login;
import com.babytrak24.service.BabyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@EnableWebMvc
@Controller
@RequestMapping("")
@SessionAttributes("login")
public class LoginController {
    @Autowired
    BabyService babyService;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("login2");
        mav.addObject("login", new Login());
        return mav;
    }
    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
                                     @ModelAttribute("login") Login login) {
        ModelAndView mav = null;
        Baby inputBaby = new Baby();
        inputBaby.setEmail(login.getEmail());
        inputBaby.setPassword(login.getPassword());
        Baby baby = babyService.findOne(Example.<Baby>of(inputBaby, ExampleMatcher.matching()
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.ignoreCase())));
        if (null != baby && baby.getPassword().equals(inputBaby.getPassword())) {
            mav = new ModelAndView("redirect:/home");
            mav.addObject("baby", baby);
        } else {
            mav = new ModelAndView("login2");
            mav.addObject("message", "Username or Password is wrong!!");
        }
        return mav;
    }
  
}
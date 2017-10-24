package com.babytrak24.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import com.babytrak24.model.Login;

@Controller
@RequestMapping("")
public class LogoutController {
	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public String logout(HttpServletRequest httprequest , WebRequest request,SessionStatus status,Model model,@ModelAttribute("login") Login login) {
		model.asMap().clear();
		HttpSession httpSession = httprequest.getSession();
        httpSession.invalidate();
        status.setComplete();
        login.setEmail("");
        login.setPassword("");
        request.removeAttribute("login", WebRequest.SCOPE_SESSION);
        return "redirect:/";
		
	}
}
package com.babytrak24.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.babytrak24.model.Baby;
import com.babytrak24.service.BabyService;

@EnableWebMvc
@Controller
@RequestMapping("")
// TODO Registration Controller created for Profile create UI Integration
// pending
public class RegistrationController {
	@Autowired
	private BabyService babyService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("register1");
		mav.addObject("baby", new Baby());
		return mav;
	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("baby") Baby baby) {

		ModelAndView mav = null;
		try {
			babyService.save(baby);
			mav = new ModelAndView("welcome", "firstname", baby.getFirstname());
		} catch (Exception e) {
			mav = new ModelAndView("register1");
			mav.addObject("message", "Baby with  email id  " + baby.getEmail() + "Already exists");
		}

		return mav;

	}

}

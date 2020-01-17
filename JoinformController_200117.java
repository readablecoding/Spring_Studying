package com.springlegacy.ex3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JoinformController {

	@RequestMapping(value="joinForm", method = RequestMethod.GET)
	public String joinForm() {
		
		return "joinForm";
	}
	
}

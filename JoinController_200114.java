package com.springlegacy.ex2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springlegacy.ex2.vo.Member;

@Controller
public class JoinController {
	
	@RequestMapping(value = "join", method = RequestMethod.POST)
	public String join(Member member, Model model) 
	{	//Member 클래스에 jsp로부터 들어온 값을 넣는다. Model 객체는 큰 상자에 데이터 넣는 것으로 데이터를 화면에 보여줄 때 사용
		System.out.println(member); //VO에 담긴 값들을 toString으로 보여줌
		
		model.addAttribute("id", member.getId()); 	  //이름은 id이고 값은 member.getId()을 model에 넣는다.
		model.addAttribute("name", member.getName()); //이름은 name이고 member.getName()을 model에 넣는다.
		
		
		return "index";	//돌아갈 jsp 주소를 적음. model에 담은 값을 가지고 돌아간다.
	}
}

package com.springlegacy.ex3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springlegacy.ex3.dao.CustomerDAO;
import com.springlegacy.ex3.vo.Customer;

@Controller
public class CustomerController {

	@Autowired
	private CustomerDAO dao;
	
	@RequestMapping(value="join", method=RequestMethod.POST)
	public String insertCustomer(Customer customer, Model model) {
		
		int n = 0;
		n = dao.insertCustomer(customer);
		if(n == 0)
		{
			System.out.println("값 저장 실패");
		}
		else 
		{
			System.out.println("값 저장 성공");
		}
		model.addAttribute("custid", customer.getCustid());
		model.addAttribute("name", customer.getName());
		
		System.out.println(model);
		
		return "redirect:check";
	}
	
	@RequestMapping(value="check", method = {RequestMethod.GET,RequestMethod.POST})
	public String check() {
		
		return "celebrate";
	}
}

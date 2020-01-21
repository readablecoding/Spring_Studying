package com.springlegacy.ex3.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springlegacy.ex3.HomeController;
import com.springlegacy.ex3.dao.CustomerDAO;
import com.springlegacy.ex3.vo.Customer;

@Controller
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	//(클래스 이름.class)로 가져온다.
	
	@Autowired
	private CustomerDAO dao;	//dao를 쓸 수 있는 이유

	@RequestMapping(value="join", method=RequestMethod.POST)
	public String insertCustomer(Customer customer, Model model) {
		
		logger.info("회원가입 컨트롤러 시작"); //이게 찍힌다면 잘 작동한 것이다. 
		
		int cnt = 0;
		cnt = dao.insertCustomer(customer);
		
		if(cnt > 0)
		{
			logger.info("회원가입 성공, 쿼리 출력값 : {}", cnt);
		}
		else 
		{
			logger.info("회원가입 실패, 쿼리 출력값 : {}", cnt);
			return "redirect:joinForm";
			//실패했을 때 회원가입 페이지로 돌려보낼 수 있다. forward방식으로 적으면 안된다. redirect로 정함
		}
		
		logger.info("{}",customer); // log에서 값들을 확인하기 위함
		
		logger.info("회원가입 컨트롤러 종료");
		return "redirect:check";	//메인(home.jsp)으로 이동시키기 무조건 redirect를 해야 한다.
	}
	
	@RequestMapping(value="check", method = {RequestMethod.GET,RequestMethod.POST})
	public String check() {
		
		return "celebrate";	//가입 축하하는 페이지로 이동한다.
	}
	
	
	@RequestMapping(value="search", method=RequestMethod.GET)
	public String select() {
		
		return "search";
	}
	

	/**
	 * 회원 목록 조회
	 * @param
	 * @Return - 회원 목록 리스트 페이지
	 */
	@RequestMapping(value="customerList", method=RequestMethod.GET)
	public String customerList(Model model) {
		
		logger.info("회원 목록 조회 컨트롤러 시작");
		ArrayList<Customer> list = dao.selectCustomerAll();
		logger.info("회원 목록 데이터 {}", list);
		model.addAttribute("list", list);	//model객체에 list를 담는다.
		
		logger.info("회원 목록 조회 컨트롤러 종료");
		return "customerList";	//forward 방식으로 해야 데이터를 가져갈 수 있다.
	}
	
	
	/**
	 * 회원 목록 삭제
	 *@param - 회원 아이디
	 */
	@RequestMapping(value="deleteCustomer", method=RequestMethod.GET)
	public String deleteCustomer(String custid) {
		
		//삭제 기능
		//다 지울순 없다. primary key에 해당하는 값만 지워야 한다.
		int cnt = dao.deleteCustomer(custid);
		
		if(cnt > 0)
		{ 
			logger.info("삭제 성공");
		}
		else
		{
			logger.info("삭제 실패");
		}
		return "redirect:customerList";  //f5를 눌렀을 때 다시 삭제되지 않도록 redirect를 씀
										//목록으로 redirect하게 끔 만든다.
	}
	

	/**
	 * 회원 수정
	 *@param - Customer customer
	 *@return - 회원 목록 
	 */
	@RequestMapping(value="updateCustomer", method=RequestMethod.GET)
	public String updateCustomer(Customer customer) {
		
		int cnt = dao.updateCustomer(customer);
		if(cnt > 0)
		{
			logger.info("수정 성공");
		}
		else
		{
			logger.info("수정 실패");
		}
		
		return "redirect:customerList";
	}
	
}

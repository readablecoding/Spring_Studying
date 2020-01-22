package com.springlegacy.ex3.controller;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	

	/**
	 * 회원 가입 창으로 이동시키기
	 * @param 
	 * @return - joinForm.jsp로 이동
	 */
	@RequestMapping(value="joinForm", method = RequestMethod.GET) //a태그는  GET방식이다.
	public String joinForm() {
		
		return "joinForm";	//joinForm.jsp로 이동
	}

	@RequestMapping(value="join", method=RequestMethod.POST)
	public String insertCustomer(Customer customer) {
		//join을 통해 가져온 값의 name과 vo의 변수명이 같으면 Customer에 join을 통해 가져온 값이 들어감
		
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
		model.addAttribute("list", list);	//model객체에 list를 담는다. -> jsp에서 ${list}로 사용가능해짐
		
		logger.info("회원 목록 조회 컨트롤러 종료");
		return "customerList";	//customerList.jsp로 이동 -> forward 방식으로 해야 데이터를 가져갈 수 있다. 
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
		return "redirect:customerList";  
		//value="customerList"인 customerList()로 이동한다.
		//f5를 눌렀을 때 다시 삭제되지 않도록 redirect를 씀
		//목록으로 redirect하게 끔 만든다.
	}
	
	//HashMap을 사용
	@RequestMapping(value = "deleteCustomer2", method = RequestMethod.POST)
	public String deleteCustomer2(String custid, String password){
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		//키와 밸류, <>안에 제네릭 타입을 정한다. 제네릭 타입은 참조형 변수로 기본 자료형은 못 넣는다. 객체가 가진 타입을 제네릭이라고 함
		//밸류에 어떤 것이 올지 모른다면 Object로 정함
		
		params.put("custid", custid);
		params.put("password", password); //마이바티스에서 꺼내기 위해 사용되어짐
		
		int cnt = dao.deleteCustomer2(params);
		
		if(cnt > 0)
		{
			logger.info("삭제 성공");
		}
		else
		{
			logger.info("삭제 실패");
		}
		
		return "redirect:customerList";	
		//value="customerList"인 customerList()로 이동한다.
	}
	
	

	/**
	 * 회원 1명 정보
	 *@param - Customer customer
	 *@return - updateCustomerForm.jsp로 이동 
	 */
	@RequestMapping(value="updateCustomerForm", method=RequestMethod.GET)
	public String updateCustomerForm(String custid, Model model) {
		//a태그의 href에 요청 주소(?나오기 전)와 데이터(?이후), 이름(=이전)과 서버의 변수 이름이 일치해야 화면에서 컨트롤러로 데이터가 전달이 된다.
		//프라이머리 키(기본키)에 해당하는 정보를 Model에 담아 리턴하기 위함
		
		Customer customer = dao.selectCustomerOne(custid);
		model.addAttribute("customer", customer);	//화면에 뿌려주기 위해 -> jsp에서 ${customer}로 쓰기 위함
		
		return "updateCustomerForm";	//select한  것이므로 forward방식으로 updateCustomerForm.jsp로 이동 
	}
	
	

	/**
	 * 회원 정보 수정
	 * @param customer
	 * @return	회원 목록 창
	 */
	@RequestMapping(value="updateCustomer", method=RequestMethod.POST)
	public String updateCustomer(Customer customer) {
		logger.info("회원 정보 컨트롤러 시작");
		
		int cnt = 0;
		cnt = dao.updateCustomer(customer);
		if(cnt > 0)
		{
			logger.info("회원 수정 성공, 쿼리 출력 값 : {}", cnt);
		}
		else
		{
			logger.info("회원 수정 실패, 쿼리 출력 값 : {}", cnt);
		}
		
		logger.info("회원 정보 컨트롤러 종ㄽ");
		//업데이트 하고 select할 때 이미 회원 목록을 select하는 곳으로 이동한다.
		return "redirect:customerList";
	}
	
	
}

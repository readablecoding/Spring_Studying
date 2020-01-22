package com.springlegacy.ex3.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.springlegacy.ex3.vo.Customer;

public interface CustomerMapper {
	
	//입력
	public int insertCustomer(Customer customer);
	
	//목록
	public ArrayList<Customer> selectCustomerAll();
	
	//삭제: vo에 담긴 값을 바로 삭제하기
	public int deleteCustomer(String custid);
	
	//HashMap을 사용한 삭제: vo에 담겨 있지 않은 값을 HashMap을 사용해 삭제
	public int deleteCustomer2(HashMap<String, Object> params);
	
	//회원 한 명 정보 가져오기: primary key(기본키)인 아이디를 통해 가입할 때 회원 정보 가져오기
	public Customer selectCustomerOne(String custid);
	
	//수정
	public int updateCustomer(Customer customer);

}

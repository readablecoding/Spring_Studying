package com.springlegacy.ex3.dao;

import java.util.ArrayList;

import com.springlegacy.ex3.vo.Customer;

public interface CustomerMapper {
	
	//입력
	public int insertCustomer(Customer customer);
	//목록
	public ArrayList<Customer> selectCustomerAll();
	//삭제
	public int deleteCustomer(String custid);
	//수정
	public int updateCustomer(Customer custmer);
}

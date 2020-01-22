package com.springlegacy.ex3.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springlegacy.ex3.vo.Customer;

@Repository 
public class CustomerDAO {

	//SqlSessionFactory로부터 SqlSession 객체를 가져오는 코드 -> @Autowired로 간략화
	
	@Autowired		//자동으로 연결해준다. 의존성 주입을 하겠다.
	private SqlSession session;
	
	//가입
	public int insertCustomer(Customer customer) 
	{
		//openSession함수를 통해서 SqlSession을 open해서 사용할 수 있게 설정
		//try catch finally 문을 사용해서 트랜잭션을 실행하고 commit -> 스프링이 알아서 다 해준다.
		//마지막에 close
		int cnt = 0;	//지역변수이니까 초기화하기
		
		try 
		{
			CustomerMapper mapper = session.getMapper(CustomerMapper.class);
			cnt = mapper.insertCustomer(customer);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	//목록
	public ArrayList<Customer> selectCustomerAll(){
		ArrayList<Customer> list = null;	//리턴을 받기 위해 미리 만들어 놓음
		try
		{
			CustomerMapper mapper = session.getMapper(CustomerMapper.class);
			list = mapper.selectCustomerAll();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;		
	}
	
	//삭제
	public int deleteCustomer(String custid) {
		int cnt = 0;
		
		try
		{
			CustomerMapper mapper = session.getMapper(CustomerMapper.class);
			cnt = mapper.deleteCustomer(custid);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cnt;
	}
	
	//삭제2: HashMap 사용
	public int deleteCustomer2(HashMap<String, Object> params) {
		int cnt = 0;
		
		try
		{
			CustomerMapper mapper = session.getMapper(CustomerMapper.class);
			cnt = mapper.deleteCustomer2(params);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cnt;
	}
	
	//회원 1명 정보
	public Customer selectCustomerOne(String custid) {
		Customer customer = null;
		try
		{
			CustomerMapper mapper = session.getMapper(CustomerMapper.class);
			customer = mapper.selectCustomerOne(custid);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return customer;	
	}
	
	//
	public int updateCustomer(Customer customer) {
		int cnt = 0;
		try
		{
			CustomerMapper mapper = session.getMapper(CustomerMapper.class);
			cnt = mapper.updateCustomer(customer);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cnt;
	}
}

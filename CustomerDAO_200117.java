package com.springlegacy.ex3.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springlegacy.ex3.vo.Customer;

@Repository
public class CustomerDAO {

	//SqlSessionFactory로부터 SqlSession 객체를 가져오는 코드 -> @Autowired로 간략화
	
	@Autowired		//자동으로 연결해준다. 의존성 주입을 하겠다.
	private SqlSession session;
	
	public int insertCustomer(Customer customer) {
		
		//openSession함수를 통해서 SqlSession을 open해서 사용할 수 있게 설정
		//try catch finally 문을 사용해서 트랜잭션을 실행하고 commit -> 스프링이 알아서 다 해준다.
		//마지막에 close
		int cnt = 0;	//지역변수이니까 초기화하기
		
		try {
			CustomerMapper mapper = session.getMapper(CustomerMapper.class);
			cnt = mapper.insertCustomer(customer);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return cnt;
	}
}

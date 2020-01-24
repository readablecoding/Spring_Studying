package com.swdo.memo.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.swdo.memo.vo.Memo;

@Repository
public class MemoDAO {
	
	@Autowired
	public SqlSession session;
	
	
	/**
	 * 메모 등록
	 * @param Memo 객체의 memo 변수
	 * @return 숫자
	 */
	public int insertMemo(Memo memo) {
		int cnt = 0;
		
		try
		{
			MemoMapper mapper = session.getMapper(MemoMapper.class);
			cnt = mapper.insertMemo(memo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		return cnt;
	}
	
	
	/**
	 * 메모 확인
	 * @param 없음
	 * @return ArrayList<Memo>
	 */
	public ArrayList<Memo> selectMemo(){
		ArrayList<Memo> list = null;
		try
		{
			MemoMapper mapper = session.getMapper(MemoMapper.class);
			list = mapper.selectMemo();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 메모 삭제
	 * @param HashMap
	 * @return 숫자
	 */
	public int deleteMemo(HashMap<String, Object> params) {
		int cnt = 0;
		
		try
		{
			MemoMapper mapper = session.getMapper(MemoMapper.class);
			cnt = mapper.deleteMemo(params);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cnt;
	}
	
}

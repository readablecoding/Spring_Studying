package com.springlegacy.homework.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springlegacy.homework.vo.Memo;

@Repository
public class MemoDAO {
	
	@Autowired
	private SqlSession session;
	
	//메모 등록
	public int insertMemo(Memo memo) {
		int count = 0;
		try {
			MemoMapper mapper = session.getMapper(MemoMapper.class);
			count = mapper.insertMemo(memo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	//메모 리스트 조회
	public ArrayList<Memo> selectMemoAll(){
		ArrayList<Memo> list = null;
		try {
			MemoMapper mapper = session.getMapper(MemoMapper.class);
			list = mapper.selectMemoAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//메모 삭제
	public int deleteMemo(Memo memo) {
		int count = 0;
		try {
			MemoMapper mapper = session.getMapper(MemoMapper.class);
			count = mapper.deleteMemo(memo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}

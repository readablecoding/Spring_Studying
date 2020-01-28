package com.springlegacy.homework.dao;

import java.util.ArrayList;

import com.springlegacy.homework.vo.Memo;

public interface MemoMapper {
	
	//메모 등록
	public int insertMemo(Memo memo);
	//메모 리스트 조회
	public ArrayList<Memo> selectMemoAll();
	//메모 삭제
	public int deleteMemo(Memo memo);
}

package com.swdo.memo.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.swdo.memo.vo.Memo;

public interface MemoMapper {
	
	
	/**
	 * 메모 등록
	 * @param memo
	 * @return 숫자
	 */
	public int insertMemo(Memo memo);
	
	/**
	 * 메모 확인
	 * @param  없음
	 * @return 메모 리스트
	 */
	public ArrayList<Memo> selectMemo();
	
	/**
	 * 메모 삭제
	 * @param HashMap에 담긴 메모 일련번호와 비밀번호
	 * @return 숫자
	 */
	public int deleteMemo(HashMap<String, Object> params);
	
}

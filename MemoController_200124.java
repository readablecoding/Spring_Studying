package com.swdo.memo.controller;


import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.swdo.memo.dao.MemoDAO;
import com.swdo.memo.vo.Memo;

@Controller
public class MemoController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemoController.class);
	
	@Autowired
	private MemoDAO dao;
	
	
	/**
	 * 메모 목록 여부에 따른 페이지 이동하기
	 * @param 값을 담아둘 Model 객체
	 * @return 데이터가 없으면 nothing.jsp, 데이터가 있으면 having.jsp로 이동
	 */
	@RequestMapping(value="selectMemo", method=RequestMethod.GET)
	public String selectMemo(Model model) {
		
		ArrayList<Memo> list = dao.selectMemo();
		model.addAttribute("list", list);
			
		if(list.size() == 0)
		{
			logger.info("메모 데이터 없음");
			return "nothing";
		}
		else
		{
			logger.info("메모 데이터 있음");
			return "having";
		}		
	}
	
	
	/**
	 * 메모 등록 페이지로 이동하기
	 * @param 없음
	 * @return memoForm.jsp로 이동
	 */
	@RequestMapping(value="memoForm", method=RequestMethod.GET)
	public String memoForm() {
	
		return "memoForm";
	}
	
	
	/**
	 * 메모 등록하기
	 * @param Memo 클래스의 memo 변수
	 * @return home.jsp로 이동
	 */
	@RequestMapping(value="insertMemo", method=RequestMethod.POST)
	public String insertMemo(Memo memo) {
		logger.info("메모 등록 시작");
		int cnt = dao.insertMemo(memo);
		if(cnt > 0)
		{
			logger.info("등록 성공");		
		}
		else
		{
			logger.info("등록 실패");
		}
		logger.info("메모 등록 종료");
		
		return "redirect:selectMemo";	
	}
	
	/**
	 * 메모 삭제
	 * @param 메모번호: memo_no, 메모 비밀번호: memo_pw
	 * @return having.jsp
	 */
	@RequestMapping(value="deleteMemo", method=RequestMethod.POST)
	public String deleteMemo(int memo_no, String memo_pw) {
		logger.info("삭제 동작 시작");
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		params.put("memo_no", memo_no);
		params.put("memo_pw", memo_pw);
	
		int cnt = dao.deleteMemo(params);
		
		if(cnt > 0)
		{
			logger.info("삭제 성공");
		}
		else
		{
			logger.info("삭제 실패");	
		}
		logger.info("삭제 동작 끝");
		
		return "redirect:selectMemo";
	}	
}

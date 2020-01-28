package com.springlegacy.homework.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springlegacy.homework.dao.MemoDAO;
import com.springlegacy.homework.vo.Memo;

@Controller
public class MemoController {
	
	@Autowired
	private MemoDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(MemoController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main() {
		logger.info("메모 메인 페이지");
		return "main";
	}
	
	@RequestMapping(value = "selectMemoList", method = RequestMethod.GET)
	public String selectMemoList(Model model) {
		logger.info("메모 리스트 페이지");
		ArrayList<Memo> list = dao.selectMemoAll();
		model.addAttribute("list", list);
		
		return "selectMemoList";
	}
	
	@RequestMapping(value = "insertMemoForm", method = RequestMethod.GET)
	public String insertMemoForm() {
		logger.info("메모 등록 페이지");
		
		return "insertMemoForm";
	}
	
	@RequestMapping(value = "insertMemo", method = RequestMethod.POST)
	public String insertMemo(Memo memo) {
		logger.info("메모 등록");
		int count = dao.insertMemo(memo);
		
		if(count == 0) {
			logger.info("메모 등록 실패");
			return "redirect:insertMemoForm";
		}
		
		return "redirect:selectMemoList";
	}
	
	@RequestMapping(value = "deleteMemo", method = RequestMethod.POST)
	public String deleteMemo(Memo memo) {
		logger.info("메모 삭제");
		int count = dao.deleteMemo(memo);
		
		if(count == 0) {
			logger.info("메모 삭제 실패");
		}
		
		return "redirect:selectMemoList";
	}
	
}

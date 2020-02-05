package global.sesoc.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import global.sesoc.sample.dao.MemberDAO;
import global.sesoc.sample.vo.Member;

/**
 * 사용자 입력을 받아 처리
 */
@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberDAO dao;
	
	@RequestMapping(value="/memberJoinForm", method=RequestMethod.GET)
	public String memberJoinForm() {
		logger.info("회원 가입 폼 이동");
		
		return "member/memberJoinForm";	//member폴더의 memberJoinForm.jsp로 이동
	}
	
	@RequestMapping(value="/memberIdCheckForm", method=RequestMethod.GET)
	public String memberIdCheckForm(Model model) {
		logger.info("아이디 중복 체크 폼 이동");
		boolean checkFlag = false;
		model.addAttribute("checkFlag", checkFlag);
		
		return "member/memberIdCheckForm";
	}
	
	@RequestMapping(value="/memberIdCheck", method=RequestMethod.POST)
	public String memberIdCheck(String checkId, Model model) {	
		logger.info("회원 중에 같은 아이디가 있는지 확인");
		Member member = dao.memberSelectOne(checkId);
		boolean checkFlag = true;
		model.addAttribute("member", member);	//조회한 결과: member가 null일때 아이디 사용 가능, null이 아니면 아이디 사용 불가
		model.addAttribute("checkId", checkId);	//member 객체에 값이 없을 때 사용자가 입력한 아이디를 보여주기 위해 checkId을  model에 집어넣음 
		model.addAttribute("checkFlag", checkFlag);	//아이디 사용 여부 버튼이라는 것을 알게 한다.
		
		return "member/memberIdCheckForm";
	}
	
	@RequestMapping(value="/memberJoin", method=RequestMethod.POST)
	public String memberJoin(Member member){
		logger.info("회원 가입 ");
		int cnt = dao.memberInsert(member);
		if(cnt != 0) {
			logger.info("회원 가입 성공");
		}
		else {
			logger.info("회원 가입 실패");
		}	
		return "redirect:/"; //홈으로 이동하는데 redirect해야 한다.
	}	
}

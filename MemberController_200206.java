package global.sesoc.sample.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	@RequestMapping(value="/memberLoginForm", method=RequestMethod.GET)
	public String memberLoginForm(HttpServletRequest request, Model model){
		logger.info("로그인 폼 이동");
		Cookie[] cookies = request.getCookies(); //쿠키를 다 받아온다. 리턴은 배열형태(Cookie[])
		String rememberId = null;
		
		if(cookies != null){
			for(Cookie c : cookies) {
				String name = c.getName(); //쿠키 이름 다 가져오기
				if(name.equals("rId")){
					rememberId = c.getValue(); // 쿠키 값 가져오기
				}
			}
		}
		model.addAttribute("rememberId", rememberId); //Model 객체에 쿠키에 저장된 아이디를 저장(없으면 null)
		
		return "member/memberLoginForm";
	}
	
	@RequestMapping(value="/memberLogin", method = RequestMethod.POST)
	public String memberLogin(Member member, String remember
			, HttpSession session, HttpServletResponse response
			, Model model) {
		logger.info("로그인 하기");
		//)전달받은 ID와 PW를 가지고 DB로 이동해서
		//실제 정보와 비교해서 정확한 내용인지 파악(select 쿼리 실행하겠다.)
		//select 쿼리를 실행시켜서 결과가 있으면 로그인 처리
		//결과가 null이면 로그인 X
		Member newMember = dao.memberSelectOne(member.getMember_id()); //아이디 전달해 결과를 가져옴
		String errMsg = "";
		if(newMember != null) {
			if(member.getMember_pw().equals(newMember.getMember_pw())) {
				//정확하다고 판단되면 로그인 처리
				//세션에 아이디를 저장: 세션에 로그인했다는 기준을 만든다.
				session.setAttribute("loginId", member.getMember_id()); 
				
				//이 때 체크박스가 체크되어 있었다면 아이디를 기억: 쿠키를 사용자에게 주기
				if(remember != null && remember.equals("1")) {
					//로그인 아이디용 쿠키
					Cookie cookie = new Cookie("rId", member.getMember_id()); //이름, 값
					cookie.setMaxAge(60*60*24*7); // 쿠키 유지기간 설정: ()안에 초를 지정함, 1주일이면 60*60*24*7(초, 분, 시간, 일)
					response.addCookie(cookie);
				}
			}
			else {
				//비밀번호 틀렸다
				errMsg = "비밀번호가 틀렸습니다.";
				model.addAttribute("errMsg", errMsg);
				return "member/memberLoginForm";
			}
		}
		else {
			//아이디 틀렸다
			errMsg = "아이디가 틀렸습니다.";
			model.addAttribute("errMsg", errMsg);
			return "member/memberLoginForm";
		}
			
		return "redirect:/";
	}
	
	@RequestMapping(value="/memberLogout", method=RequestMethod.GET)
	public String memberLogout(HttpSession session) {
		//세션에 로그인 할 때 저장했던 값을 지우는 일
		session.removeAttribute("loginId"); //세션에 있던 loginId를 지움
		return "redirect:/";
	}
		
}

package com.springlegacy.ex2;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value="JstlCore", method=RequestMethod.GET)
	public String JstlCore(Model model) {
		//model에 데이터 넣기
		int number = 1;
		String str = "abc";
		ArrayList<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		
		String phone = "010-1111-2222";
		String data = "<marquee>문자열</marquee>";
		
		model.addAttribute("number", number);
		model.addAttribute("str", str);
		model.addAttribute("list", list);
		model.addAttribute("phone", phone);
		model.addAttribute("data", data);

		return "jstlCore";	//jstlCore.jsp로 이동
	}
	
	@RequestMapping(value="Session1", method=RequestMethod.GET)
	public String Session1(HttpSession session, Model model) {
		//아이디와 비밀번호에 대한 검사
		//아이디가 존재하는지? 존재하는 아이디와 입력받은 비밀번호가 같은지?
		//맞다면 아이디를 Session에 넣는다.
		
		session.setAttribute("id", "admin"); 	//HttpSession을 통해 값 넣기 -> key와 value, value는 들어갈 때 Object로 들어감
		model.addAttribute("pw", "password");	//Model을 통해 값 넣기 ->  페이지가 바뀌면 값이 안 보임		
		return "Session1";
	}
	
	//세션에서 값 가져오기
	@RequestMapping(value="SessionCheck", method=RequestMethod.GET)
	public String SessionCheck(HttpSession session) {	
		//SessionCheck 페이지는 로그인한 사람만 접속할 수 있다.
		//Session에 id의 값이 있는지 확인
		
		String id = (String)session.getAttribute("id"); // key를 집어 넣는다. 리턴은 뭐든지 받을 수 있는 것은 Object이다. 큰것에서 작은 것 가려면 형변환하기
		if(id == null) {	//String은 값이 없으면 null
			//Session에서 꺼낸 값이 null이라면 로그인 안했다.
			//로그인 페이지로 이동시키면서 메시지를 출력
		}
		else {
			//Session에서 꺼낸 값이 null이 아니라면 로그인을 했다.
			//정상적으로 페이지 이동.
			
		}
		
		return "SessionCheck";			//SessionCheck.jsp로 이동
	}
	
	//세션 값 지우기
	@RequestMapping(value="SessionDelete", method = RequestMethod.GET)
	public String SessionDelete(HttpSession session) {
		session.removeAttribute("id"); 	//id 삭제 
		return "Session1";				//Session1.jsp로 이동
	}
	
	//리소스 가져오기, forward 방식: 데이터를 가져올 수 있다. 주소표시줄의 주소가 전 요청주소
	@RequestMapping(value="resources", method=RequestMethod.GET)
	public String resources() {	
		return "resources";
	} 
	
	//redirect 방식: 데이터를 가져갈 수 없음. 주소표시줄의 주소 바뀜
	@RequestMapping(value="forward", method=RequestMethod.GET)
	public String forward(Model model) {
		String str = "abcd";			//가상의 데이터 만들기
		System.out.println(str);		//데이터 출력
		model.addAttribute("str", str); //속성, 값 을 model에 등록함
		
		//return "forwardCheck";	-> forwardCheck.jsp로 이동하게 됨
		return "redirect:ret";	//"redirect:이동할 곳의 요청 주소" -> 한단계 거치므로 데이터가 사라지고 주소가 바뀜. redirect방식
	}
	
	@RequestMapping(value = "ret", method = RequestMethod.GET)
	public String redirect() {
		return "forwardCheck";	//forwardCheck.jsp로 이동 -> 값을 가지지 않은채로 이동한다.
	}
}

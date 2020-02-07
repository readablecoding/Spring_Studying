package global.sesoc.sample.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import global.sesoc.sample.dao.BoardDAO;
import global.sesoc.sample.util.FileService;
import global.sesoc.sample.vo.Board;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	//업로드 경로 설정: uploadFile 폴더를 사용하겠다.
	private final String uploadPath = "/uploadFile";
	
	
	@Autowired
	private BoardDAO dao;
	
	@RequestMapping(value="/boardList", method=RequestMethod.GET)
	public String boardList(Model model) {
		logger.info("글 목록 이동");
		
		ArrayList<HashMap<String, Object>> list = dao.selectBoardList();
		model.addAttribute("list", list);
		
		return "board/boardList";
	}
	
	@RequestMapping(value="/boardWriteForm", method=RequestMethod.GET)
	public String boardWriteForm() {
		logger.info("글 등록 폼 이동");
		return "board/boardWriteForm";
	}
	
	@RequestMapping(value="/boardWrite", method=RequestMethod.POST)
	public String boardWrite(Board board, MultipartFile upload
			, HttpSession session) {
		logger.info("글 등록");
		//파일이 있는지 확인해서
		
		if(!upload.isEmpty()) {
			//있을 경우에는 서버에 물리적으로 저장
			String savedFile = FileService.saveFile(upload, uploadPath); //저장된 파일명을 savedFile에 넣음
			board.setBoard_savedfile(savedFile); 					 //저장된 파일 이름
			board.setBoard_originfile(upload.getOriginalFilename()); //원래 파일 이름
		}
		//세션에서 로그인한 사용자 아이디 받아온다.(로그인할 때 session에 값을 넣었다)
		String loginId = (String)session.getAttribute("loginId"); //return이 오브젝트 타입이라 강제 형변환해줘야 함
		board.setMember_id(loginId); //로그인한 아이디를 member_id에 넣기
		
		//저장된 파일 경로를 받아아 DB에 저장한다.	
		int cnt = dao.insertBoard(board);
		if(cnt != 0) {
			logger.info("저장 성공");
		}
		else {
			logger.info("저장 실패");
		}
		
		return "redirect:boardList"; //insert가 계속 일어나지 않게끔 redirect 해줌
	}
	
	@RequestMapping(value="/boardReadForm", method=RequestMethod.GET)
	public String boardReadForm(int board_no, Model model) {
		logger.info("글 읽기 폼 이동");
		HashMap<String, Object> board = dao.selectBoardOne(board_no);
	
		model.addAttribute("board", board);
		
		return "board/boardReadForm";
	}
	
}

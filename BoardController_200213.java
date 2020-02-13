package global.sesoc.sample.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import global.sesoc.sample.dao.BoardDAO;
import global.sesoc.sample.util.FileService;
import global.sesoc.sample.util.PageNavigator;
import global.sesoc.sample.vo.Board;
import global.sesoc.sample.vo.Reply;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	//업로드 경로 설정: uploadFile 폴더를 사용하겠다.
	private final String uploadPath = "/uploadFile";
	
	private final int countPerPage = 5;
	private final int pagePerGroup = 5;
	
	
	@Autowired
	private BoardDAO dao;
	
	
	@RequestMapping(value="/boardList", method=RequestMethod.GET)
	public String boardList(Model model
			,@RequestParam(value="currentPage", defaultValue="1") int currentPage
			,@RequestParam(value="searchText", defaultValue="") String searchText
			) {
		logger.info("글 목록 이동");
		//전체 글 수(범위-> 검색어가 있냐 없냐)
		int totalCount = dao.selectTotalCount(searchText);
		PageNavigator navi = new PageNavigator(countPerPage, pagePerGroup, currentPage, totalCount); //페이지네비게이터 객체 생성
		
		//검색어가 있으면 검색어를 포함한 글 조회, 검색어가 없으면 그냥 글 조회(dao.selectBoardList(시작 위치, 개수, 검색어 있냐없냐))
		ArrayList<HashMap<String, Object>> list = dao.selectBoardList(navi.getStartRecord(), countPerPage, searchText);
		model.addAttribute("list", list); 				//글의 정보가 들어있다.
		model.addAttribute("navi", navi);				//페이지 정보가 들어있다.
		model.addAttribute("searchText", searchText); 	//검색어 
		
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
			board.setBoard_savedfile(savedFile); 					 	//저장된 파일 이름
			board.setBoard_originfile(upload.getOriginalFilename()); 	//원래 파일 이름
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
		logger.info("글과 리플 읽기 폼 이동");
		//글 정보 가져오기
		HashMap<String, Object> board = dao.selectBoardOne(board_no);
		//리플 정보 가져오기
		ArrayList<Reply> replyList = dao.selectReply(board_no);
		//글 정보 담기: 화면에 가지고 가겠다.
		model.addAttribute("board", board);		
		//리플 정보 담기: 화면에 가지고 가겠다.
		model.addAttribute("replyList", replyList); 
		return "board/boardReadForm";
	}
	
	@RequestMapping(value="/download", method=RequestMethod.GET)
	public void download(int board_no, HttpServletResponse response) {
		logger.info("다운로드하기");
		HashMap<String, Object> board = dao.selectBoardOne(board_no); // 해쉬맵에 우리가 원하는 데이터가 들어 있다.
		String originFile = (String)board.get("BOARD_ORIGINFILE"); //원본 파일명 -> 키를 Object형태로 저장했으니 가져올 때는 String으로 형변환하기
		String savedFile = (String)board.get("BOARD_SAVEDFILE");
		
		try {
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(originFile,"UTF-8" ));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String fullPath = uploadPath + "/" + savedFile; // 업로드한 파일 전체 경로
		FileInputStream fi = null; // 서버에서 파일 읽기 위함 -> 파일인풋스트림 
		ServletOutputStream so = null;	//파일 내보내기, 브라우저로 보내기 
		
		try {
			fi = new FileInputStream(fullPath); //경로가 없을 때는 예외 발생함
			so = response.getOutputStream(); 	
			
			FileCopyUtils.copy(fi, so); //인풋스트림을 통해 읽은 파일을 아웃풋스트림으로 복사
			fi.close();
			so.close();
			logger.info("다운로드 성공");
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="/boardDelete", method=RequestMethod.GET)
	public String boardDelete(Board board, HttpSession session) {
		logger.info("글 삭제하기");
		//세션에 있던 아이디를 가져와  vo에 집어넣음
		String loginId = (String)session.getAttribute("loginId"); //set할 때 넣은 그대로 get부분에 넣기
		board.setMember_id(loginId); //세션	에 로그인했던 아이디를 vo에 넣기
		
		//업로드된 파일을 확인하기 위해 vo에서 가져온 번호로 검색한 것을 해쉬맵에 넣음
		HashMap<String, Object> map = dao.selectBoardOne(board.getBoard_no());
	
		int cnt = dao.boardDelete(board);	//해당 번호로 된 글 삭제
		String savedFile = (String)map.get("BOARD_SAVEDFILE"); //저장된 파일명의 키 값 꺼내 넣기
		
		//삭제 성공했고 첨부파일이 있으면 첨부파일도 삭제한다. -> 둘 다 참일 때 한다.
		if((cnt != 0) && (savedFile != null)) {
			String fullPath = uploadPath + "/" + savedFile; //fullPath에는 전체 경로를 넣는다.
			boolean flag = FileService.deleteFile(fullPath); // 삭제할 파일 경로를 받아 파일을 삭제한 결과를 boolean 타입이 flag에 넣기
			if(flag) {
				logger.info("삭제 성공");
			}
			else {
				logger.info("파일 삭제 실패");
			}
		}
		
		return "redirect:boardList";
	}
	
	@RequestMapping(value="/boardUpdateForm", method=RequestMethod.GET)
	public String boardUpdateForm(int board_no, Model model) {
		HashMap<String, Object> map = dao.selectBoardOne(board_no);
		model.addAttribute("board", map);
		return "board/boardUpdateForm";
	}
	
	@RequestMapping(value="/boardUpdate", method = RequestMethod.POST)
	public String boardUpdate(Board board, MultipartFile upload
			,HttpSession session) {
		logger.info("글 업데이트하기");
		//jsp에서 담지 않았던 로그인한 사람의 아이디 가져와 Board에 집어 넣기
		String loginId = (String)session.getAttribute("loginId");
		board.setMember_id(loginId);
		
		//기존에 업로드한 파일을 알기 위해 기존 정보를 가져옴. 없으면 null
		HashMap<String, Object> oldBoard = dao.selectBoardOne(board.getBoard_no());
		String oldSavedFile = (String)oldBoard.get("BOARD_SAVEDFILE"); //해쉬맵에서 가져올 때 get("키 값")
		
		//파일을 어떻게 하는지?(기존, 수정을 고려함), 파일이 있는지 여부만 확인
		//1)새로 업로드 한 파일이 있을 때
		if(!upload.isEmpty()) {
			
			//1-1)기존 파일이 있을 때 => 삭제를 한다.
			if(oldSavedFile != null) {
				String fullPath = uploadPath +  "/" + oldSavedFile;
				
				if(FileService.deleteFile(fullPath)) {
					logger.info("기존 파일 삭제 성공");
				}		
			} //if(oldSavedFile != null)
			
			//1-2)저장 파일명, 원본 파일명을 board에 넣기
			String savedFile = FileService.saveFile(upload, uploadPath); //FileService.saveFile(업로드 파일, 저장경로)
			String originFile = upload.getOriginalFilename();
			board.setBoard_savedfile(savedFile);
			board.setBoard_originfile(originFile);	
		} //if(!upload.isEmpty())
		
		//2)수정하는 함수 호출
		int cnt = dao.boardUpdate(board);
		if(cnt != 0) {
			logger.info("수정 성공");
		}
		else {
			logger.info("수정 실패");
		}
		
		//3)boardReadForm으로 이동하는데 파라미터로 board_no가 필요하므로 쿼리스트링으로 board_no를 넘겨줌
		return "redirect:boardReadForm?board_no=" + board.getBoard_no();
	}
	
	@RequestMapping(value="/replyInsert", method=RequestMethod.POST)
	public String replyInsert(Reply reply, HttpSession session) {
		//board_no, reply_content을 가져오기 위해 Reply reply 사용
		//member_id(로그인한 사람)를 가져오기 위해 HttpSession session 사용
		//가져온 member_id를 vo에 집어넣기
		logger.info("리플  등록하기");
		String loginId = (String)session.getAttribute("loginId");
		reply.setMember_id(loginId);
		int cnt = dao.replyInsert(reply);
		if(cnt != 0) {
			logger.info("리플 등록 성공");
		}
		else {
			logger.info("리플 등록 실패");
		}
		//리플을 등록하고 다시 원래 그대로인 boardReadForm으로 돌아간다. boardReadForm의 파라미터로 필요한 board_no를 쿼리스트링으로 들고감
		return "redirect:boardReadForm?board_no=" + reply.getBoard_no(); 
	}
	
	@RequestMapping(value="/replyDelete", method=RequestMethod.GET)
	public String replyDelete(Reply reply, HttpSession session) {
		logger.info("리플 삭제하기");
		//세션에 저장된 로그인한 아이디를 가져와서 vo에 집어넣기
		String loginId = (String)session.getAttribute("loginId");
		reply.setMember_id(loginId);
		
		//리플 삭제 실행
		int cnt = dao.replyDelete(reply);
		if(cnt != 0) {
			logger.info("리플 삭제 성공");
		}
		else {
			logger.info("리플 삭제 실패");
		}
		
		//boardReadForm으로 돌아가기
		return "redirect:boardReadForm?board_no=" + reply.getBoard_no();
	}
	
	
	@RequestMapping(value="/replyUpdate", method=RequestMethod.POST)
	public String replyUpdate(Reply reply, HttpSession session) {
		
		//로그인 할 때 당시의 아이디를 세션에서 꺼내온다.String으로 형변환한다. 그리고 vo에 아이디를 넣는다.
		String loginId = (String)session.getAttribute("loginId");
		reply.setMember_id(loginId);
		int cnt = dao.replyUpdate(reply);
		if(cnt != 0) {
			logger.info("리플 업데이트 성공");
		}
		else {
			logger.info("리플 업데이트 실패");
		}
		
		return "redirect:boardReadForm?board_no=" + reply.getBoard_no();
	}
}

package global.sesoc.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import global.sesoc.sample.dao.noticeDAO;
import global.sesoc.sample.util.FileService;
import global.sesoc.sample.vo.Notice;

/**
 * 사용자 입력을 받아 처리
 */
@Controller
@RequestMapping(value="/notice")	//notice폴더를 거치게 만든다.
public class NoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	private final String uploadPath= "/uploadFile"; //c드라이브 아래 uploadFile 폴더
	
	@Autowired
	private noticeDAO dao;

	@RequestMapping(value="/noticeList", method=RequestMethod.GET)
	public String noticeList() {
		logger.info("게시글 목록 페이지 이동");
		return "notice/noticeList";	//views폴더-notice폴더-noticeList.jsp로 이동
	}
	
	@RequestMapping(value="/noticeInsertForm", method=RequestMethod.GET)
	public String noticeInsertForm() {
		logger.info("게시글 등록 폼 이동");
		return "notice/noticeInsertForm";
	}
	
	@RequestMapping(value="/noticeInsert", method=RequestMethod.POST)
	public String noticeInsert(Notice notice, MultipartFile upload){
		
		//파일 업로드(물리적): 파일을 pc에 저장하는 방식
		//파일이 있을 때
		if(!upload.isEmpty()){
			String savedfile = FileService.saveFile(upload, uploadPath); //static이므로 그냥 부른다.
			//업로드된 파일의 경로(파일명)을 VO에 SET
			//저장된 파일명
			notice.setNotice_savedfile(savedfile);
			//원본 파일명
			notice.setNotcie_originfile(upload.getOriginalFilename());
		}
		//VO를 DB에 insert하기
		int cnt = dao.noticeInsert(notice);
		
		if(cnt == 0) {
			logger.info("등록 실패");
			//파일을 삭제(물리적)
			
		}

		return "redirect:noticeList"; //여기서는 /(루트)를 붙이면 안된다. 이미 위에 @RequestMapping(value="/notice")가 있어서
		//return ""redirect:/notice/noticeList"; 로 해도 된다. 이것은 절대경로일 경우
	}
}

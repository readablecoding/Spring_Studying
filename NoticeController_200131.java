package global.sesoc.sample.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
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
	public String noticeList(Model model) {
		logger.info("게시글 목록 페이지 이동");
		ArrayList<Notice> list = dao.noticeSelectList();
		model.addAttribute("list", list);
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
			notice.setNotice_originfile(upload.getOriginalFilename());
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
	
	@RequestMapping(value="/noticeReadForm", method=RequestMethod.GET)
	public String noticeReadForm(int notice_no, Model model){
		//읽기 폼으로 가기 위해서 정보를 조회
		logger.info("조회폼");
		Notice notice = dao.noticeSelectOne(notice_no);
		model.addAttribute("notice", notice);
		return "notice/noticeReadForm";
	}
	
	@RequestMapping(value="download", method=RequestMethod.GET)
	public void download(int notice_no, HttpServletResponse response){
		//조회: pk에 해당하는 정보를 가져옴
		Notice notice = dao.noticeSelectOne(notice_no);
		
		//원본 파일명
		String originFile = notice.getNotice_originfile();
		
		//다운로드시 창으로 띄우게끔 HttpServletResponse 기능을 바꿈, 파일에 접근은 하지 않음
		try {
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(originFile,"UTF-8"));
			//프로토콜도 head와 body영역이 있다. 파일 전달되는 header로 바꾸기
			//Content-Disposition -> 저장하시겠습니까? 라는 창 띄움, "attachment;filename="는 첨부파일이 있다. + 뒤에 파일 이름이 온다.
			//URLEncoder.encode()을 통해 파일이름을 UTF-8 형식으로 바꿔줌
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		
		//저장된 파일 경로
		String fullpath = uploadPath + "/" + notice.getNotice_savedfile();
		
		//서버의 파일을 읽을 입력 스트림과 클라이언트에게 전달할 출력스트림
		FileInputStream filein = null; 				//컴퓨터에서 파일 읽어오는 것
		ServletOutputStream fileout = null; 		//쓰는 용도
		
		try {
			//데이터가 지나갈 길을 만드는 것
			filein = new FileInputStream(fullpath); //경로의 파일을 읽어오는 통로를 개설
			fileout = response.getOutputStream();	//응답하는 쪽으로 파일을 보내겠다고 통로를 개설
			
			//서버의 파일을 읽어 브라우저로 복사해줌, input에서 읽어 output으로 파일을 복사
			FileCopyUtils.copy(filein, fileout);
			
			//닫기: 스트림은 다 쓰면 닫아야 한다.
			filein.close();
			fileout.close();
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="/noticeUpdateForm", method=RequestMethod.GET)
	public String noticeUpdateForm(int notice_no, Model model){
		//수정 폼으로 가기 위해서 정보를 조회
		logger.info("조회폼");
		Notice notice = dao.noticeSelectOne(notice_no);
		model.addAttribute("notice", notice);
		return "notice/noticeUpdateForm";
	}
	
	@RequestMapping(value="noticeUpdate", method=RequestMethod.POST)
	public String noticeUpdate(Notice notice, MultipartFile upload) {
		
		//savedFile과 originFile 정보가 필요하다.
		 Notice oldNotice = dao.noticeSelectOne(notice.getNotice_no());
		 
		//첨부파일에 대한 경우의 수
		 if(!upload.isEmpty()) {
			 	
			//기존 파일이 있고 새롭게 파일을 수정했을 때
			if(oldNotice.getNotice_savedfile() != null) {
				 
			 //기존 파일을 삭제하고
				String fullPath = uploadPath + "/" + oldNotice.getNotice_savedfile();
				boolean flag = FileService.deleteFile(fullPath);
				if(flag) {
					logger.info("삭제 성공");
				}
				else {
					logger.info("삭제 실패");
				}
					
			}//if(oldNotice.getNotice_savedfile() != null)
			
			//새로운 파일을 등록한다
			String savedFile = FileService.saveFile(upload, uploadPath);
			notice.setNotice_savedfile(savedFile);
			notice.setNotice_originfile(upload.getOriginalFilename());	
			
		}//if(!upload.isEmpty())
		 
		 //수정한다.
		 int cnt = dao.noticeUpdate(notice);
		 
		 if(cnt > 0) {
			 logger.info("수정 성공");
		 }
		 else {
			 logger.info("수정 실패");
		 }
 
		return "redirect:noticeReadForm?notice_no=" + notice.getNotice_no(); 
		//noticeReadForm(게시판 읽기 폼)으로 리다이렉트하는데 notice_no 값을 가지고 간다.
	}//noticeUpdate()
	
}//NoticeController 클래스

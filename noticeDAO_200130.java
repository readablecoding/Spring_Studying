package global.sesoc.sample.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import global.sesoc.sample.vo.Notice;

/**
 * 개인정보 관련 DB처리를 담당
 */
@Repository
public class noticeDAO {
	@Autowired
	private SqlSession session;
	
	
	/**
	 * insert
	 * @param notice
	 * @return cnt
	 */
	public int noticeInsert(Notice notice) {
		int cnt = 0;
		try {
			noticeMapper mapper = session.getMapper(noticeMapper.class);
			cnt = mapper.noticeInsert(notice);
		}
		catch(Exception e) {
			e.printStackTrace();
			//파일 삭제
			
		}
		return cnt;
	}
	
	
	/**
	 * 전체 목록 select 
	 * @param 
	 * @return list
	 */
	public ArrayList<Notice> noticeSelectList(){
		ArrayList<Notice> list = null;
		try {
			noticeMapper mapper = session.getMapper(noticeMapper.class);
			list = mapper.noticeSelectList();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 1개 글 select 
	 * @param notice_no
	 * @return	notice
	 */
	public Notice noticeSelectOne(int notice_no) {
		Notice notice = null;
		try {
			noticeMapper mapper = session.getMapper(noticeMapper.class);
			notice = mapper.noticeSelectOne(notice_no);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return notice;
	}
}

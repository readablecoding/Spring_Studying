package global.sesoc.sample.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
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
	public ArrayList<Notice> noticeSelectList(String searchText, int startRecord, int countPerPage){
		ArrayList<Notice> list = null;
		try {
			noticeMapper mapper = session.getMapper(noticeMapper.class);
			
			//RowBounds객체 = Paging처리를 도와주는 Mybatis 객체다.  
			//offset(객체 생성시 시작위치), limit(보여줄 글 개수)를 생성자에 전달
			//쿼리를 있는 그대로 쓰기 위해서 RowBounds객체를 쓴다.
			RowBounds rb = new RowBounds(startRecord, countPerPage);
			
			//RowBounds 객체를 넣어줌
			list = mapper.noticeSelectList(searchText, rb);
			
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
	
	
	/**
	 * update
	 * @param notice
	 * @return cnt
	 */
	public int noticeUpdate(Notice notice) {
		int cnt = 0;
		try {
			noticeMapper mapper = session.getMapper(noticeMapper.class);
			cnt = mapper.noticeUpdate(notice);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return cnt;	
	}
	
	
	/**
	 * 전체 글 개수 
	 * @return cnt
	 */
	public int noticeSelectCount(String searchText) {
		int cnt = 0;
		try {
			noticeMapper mapper = session.getMapper(noticeMapper.class);
			cnt = mapper.noticeSelectCount(searchText);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cnt;
	}
	
	
	/**
	 * 조회수 증가
	 * @param notice_no
	 */
	public void updateHits(int notice_no) {
		try {
			noticeMapper mapper = session.getMapper(noticeMapper.class);
			mapper.updateHits(notice_no);	
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
}

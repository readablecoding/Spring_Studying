package global.sesoc.sample.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}

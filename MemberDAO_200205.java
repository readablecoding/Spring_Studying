package global.sesoc.sample.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.sample.vo.Member;

/**
 * 개인정보 관련 DB처리를 담당
 */
@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSession session;
	
	
	public Member memberSelectOne(String member_id) {
		Member member = null;
		try {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			member = mapper.memberSelectOne(member_id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	
	public int memberInsert(Member member) {
		int cnt = 0;
		try {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			cnt = mapper.memberInsert(member);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
}

package global.sesoc.sample.dao;

import global.sesoc.sample.vo.Member;

public interface MemberMapper {
	
	public Member memberSelectOne(String member_id); //아이디를 통해 객체를 리턴
	
	public int memberInsert(Member member);

}

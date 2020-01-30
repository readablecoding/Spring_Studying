package global.sesoc.sample.dao;

import java.util.ArrayList;

import global.sesoc.sample.vo.Notice;

public interface noticeMapper {
	
	public int noticeInsert(Notice notice);
	
	public ArrayList<Notice> noticeSelectList();
	
	public Notice noticeSelectOne(int notice_no);
}

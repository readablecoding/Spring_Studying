package global.sesoc.sample.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;

import global.sesoc.sample.vo.Notice;

public interface noticeMapper {
	
	public int noticeInsert(Notice notice);
	
	public ArrayList<Notice> noticeSelectList(RowBounds rb);
	
	public Notice noticeSelectOne(int notice_no);
	
	public int noticeUpdate(Notice notice);
	
	public int noticeSelectCount();
	
	public void updateHits(int notice_no);
}

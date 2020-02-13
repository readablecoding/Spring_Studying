package global.sesoc.sample.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;

import global.sesoc.sample.vo.Board;
import global.sesoc.sample.vo.Reply;

public interface BoardMapper {
	
	//글 읽기
	public ArrayList<HashMap<String, Object>> selectBoardList(RowBounds rb, String searchText);
	
	//글 쓰기
	public int insertBoard(Board board);
	
	//검색
	public HashMap<String, Object> selectBoardOne(int board_no);
	
	//개수
	public int selectTotalCount(String searchText);
	
	//삭제
	public int boardDelete(Board board);
	
	//수정
	public int boardUpdate(Board board);
	
	//리플 등록
	public int replyInsert(Reply reply);
	
	//리플 리스트 가져오기
	public ArrayList<Reply> selectReply(int board_no);
	
	//리플 삭제
	public int replyDelete(Reply reply);
	
	//리플 업데이트
	public int replyUpdate(Reply reply);
}

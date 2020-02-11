package global.sesoc.sample.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;

import global.sesoc.sample.vo.Board;

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
}

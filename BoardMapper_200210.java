package global.sesoc.sample.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;

import global.sesoc.sample.vo.Board;

public interface BoardMapper {

	public ArrayList<HashMap<String, Object>> selectBoardList(RowBounds rb, String searchText);
	
	public int insertBoard(Board board);
	
	public HashMap<String, Object> selectBoardOne(int board_no);
	
	public int selectTotalCount(String searchText);
}

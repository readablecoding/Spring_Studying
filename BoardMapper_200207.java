package global.sesoc.sample.dao;

import java.util.ArrayList;
import java.util.HashMap;

import global.sesoc.sample.vo.Board;

public interface BoardMapper {

	public ArrayList<HashMap<String, Object>> selectBoardList();
	
	public int insertBoard(Board board);
	
	public HashMap<String, Object> selectBoardOne(int board_no);
}

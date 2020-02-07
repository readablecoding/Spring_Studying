package global.sesoc.sample.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.sample.vo.Board;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSession session;
	
	public ArrayList<HashMap<String, Object>> selectBoardList(){
		ArrayList<HashMap<String, Object>> list = null;
		
		try {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			list = mapper.selectBoardList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;	
	}
	
	
	public int insertBoard(Board board) {
		int cnt = 0;
		try {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			cnt = mapper.insertBoard(board);	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return cnt;	
	}
	
	
	public HashMap<String, Object> selectBoardOne(int board_no){
		HashMap<String, Object> result = null;
		try {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			result = mapper.selectBoardOne(board_no);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}

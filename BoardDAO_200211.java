package global.sesoc.sample.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.sample.vo.Board;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSession session;
	
	public ArrayList<HashMap<String, Object>> selectBoardList(int startRecord, int countPerpage, String searchText){
		ArrayList<HashMap<String, Object>> list = null;
		
		try {
			RowBounds rb = new RowBounds(startRecord, countPerpage); 
			//시작 위치, 개수 -> 마이바티스가 제공, 특정 쿼리가 실행될 때 값이 전달되면 RowBounds를 참조해 값 가져옴, 직접 쿼리를 만들지 않아도 적용시켜줌.쿼리를 안 고치기 위해 쓴다. 
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			list = mapper.selectBoardList(rb, searchText);
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
	
	public int selectTotalCount(String searchText) {
		int totalCount = 0;
		try {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			totalCount = mapper.selectTotalCount(searchText);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}
	
	public int boardDelete(Board board) {
		int cnt  = 0;
		try {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			cnt = mapper.boardDelete(board);
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		return cnt;
	}
	
	public int boardUpdate(Board board) {
		int cnt = 0;
		try {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			cnt = mapper.boardUpdate(board);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
}

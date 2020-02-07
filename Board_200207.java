package global.sesoc.sample.vo;

import lombok.Data;

@Data
public class Board {
	private 	int 		board_no;			
	private 	String 		member_id;			
	private 	String 		board_title;		
	private 	String 		board_content;		
	private 	int 		board_hits;		
	private 	String 		board_savedfile;			
	private 	String 		board_originfile;							
	private 	String 		board_indate;	
}

package global.sesoc.sample.vo;

import lombok.Data;

@Data
public class Reply {

	private 	int 		reply_no;		//자동, 시퀀스로 입력해줄 필요 없다.
	private 	int 		board_no;		//글 번호는 이미 존재함. 화면으로부터 board_no를 받아오기 	
	private 	String 		reply_content;	//사용자가 입력한 것 그대로 받아오기
	private 	String 		member_id;		//세션에서 받아와야 한다.
	private 	String 		reply_indate;		//시스템이 자동으로 입력해줌
}

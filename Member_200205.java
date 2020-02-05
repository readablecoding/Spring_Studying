package global.sesoc.sample.vo;

import lombok.Data;

/**
 * 개인정보를 저장하는 VO 
 */
@Data
public class Member {
	private String member_id;
	private String member_pw;
	private String member_nm;
	private String member_addr;
	private String member_indate;
}

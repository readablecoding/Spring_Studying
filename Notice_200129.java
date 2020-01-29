package global.sesoc.sample.vo;

import lombok.Data;

/**
 * 개인정보를 저장하는 VO 
 */
@Data
public class Notice {
	private int notice_no;
	private String notice_title;
	private String notice_context;
	private	String notice_name;
	private	int notice_hits;
	private String notice_indate;
	private String notice_savedfile;
	private	String notcie_originfile;	
}

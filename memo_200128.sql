-- 메모 정보 테이블
create table memo (
	memo_no			number			 primary key		--메모 번호(PK)
	,memo_pw		varchar2(20)	 not null			--메모 비밀번호
	,memo_content	varchar2(2000)	 not null			--메모 내용
	,memo_indate	date			 default sysdate	--메모 등록일
);

-- 메모 번호 시퀀스
create sequence memo_seq;
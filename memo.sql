CREATE TABLE MEMO(
    MEMO_NO NUMBER PRIMARY KEY
    ,MEMO_PW VARCHAR2(50) NOT NULL
    ,MEMO_CONTENT VARCHAR2(50) NOT NULL
    ,MEMO_INDATE DATE DEFAULT SYSDATE
);

CREATE Sequence MEMO_SEQ;
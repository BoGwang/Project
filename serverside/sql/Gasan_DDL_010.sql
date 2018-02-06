
CREATE TABLE coupon (
   cno NUMBER NOT NULL,
   sale VARCHAR2(150) NOT NULL,
   CONSTRAINT pk_coupon PRIMARY KEY (cno)
);


-------------------------------------------------------------

CREATE TABLE user_info (
	id NUMBER NOT NULL,
	email VARCHAR2(320) NOT NULL,
	password VARCHAR2(60) NOT NULL,
	name VARCHAR2(20) NOT NULL,
	avatar VARCHAR2(255),
	cno NUMBER NULL,
	CONSTRAINT pk_user_info PRIMARY KEY (id),
	CONSTRAINT uk_user_info UNIQUE (email),
	CONSTRAINT fk_coupon FOREIGN KEY (cno) REFERENCES coupon(cno)
);


-------------------------------------------------------------

CREATE TABLE user_type (
	id NUMBER NOT NULL,
	type VARCHAR2(30) NOT NULL,
	CONSTRAINT pk_user_type PRIMARY KEY (id)
);


CREATE TABLE user_info_type (
	user_info_id NUMBER NOT NULL,
	user_type_id NUMBER NOT NULL,
	CONSTRAINT fk_user_info FOREIGN KEY (user_info_id) REFERENCES user_info (id),
	CONSTRAINT fk_user_type FOREIGN KEY (user_type_id) REFERENCES user_type (id)
);

---------------------------------------------------------------
CREATE TABLE board (
   no 			NUMBER,
   title  		VARCHAR2(100),
   content  	VARCHAR2(4000),
   id			NUMBER,
   regdate  	DATE,
   attachment	VARCHAR2(255),
   CONSTRAINT pk_board PRIMARY KEY (no),
   CONSTRAINT fk_board FOREIGN KEY (id) REFERENCES user_info(id)
);


------------------------------------------------------------------------------


/* 영화관 */
CREATE TABLE  theater (
  the_no NUMBER   NOT NULL, /*극장 번호*/
  the_name VARCHAR2(30),
  CONSTRAINT pk_theater PRIMARY KEY(the_no)
);


/*상영관*/
CREATE TABLE screen (
  sc_no NUMBER NOT NULL, /* 상영관 번호 */
  the_no NUMBER NOT NULL, /* 극장 번호*/
  sc_name VARCHAR2(30) NOT NULL,  /* 상영관 이름*/
  sc_line NUMBER NOT NULL, /* 라인 수*/
  sc_seat NUMBER NOT NULL, /*좌석 수*/
  CONSTRAINT pk_screen PRIMARY KEY (sc_no),
  CONSTRAINT fk_screen FOREIGN KEY (the_no) REFERENCES theater(the_no)
);


CREATE TABLE movie (
    mov_no NUMBER NOT NULL, /* 영화 번호*/
    mov_title VARCHAR2(400) NOT NULL, /*영화 제목*/
    mov_content VARCHAR2(200) NOT NULL, /*내용*/
    mov_director VARCHAR2(100) NOT NULL, /*감독 */
    mov_poster VARCHAR2(300),  /*포스터*/
    CONSTRAINT pk_movie PRIMARY KEY(mov_no)

  );
  

  
/* 스케줄 */
CREATE TABLE schedule (
   sch_no NUMBER NOT NULL, /* 스케줄 번호*/
   mov_no NUMBER NOT NULL, /* 영화 번호*/
   sc_no NUMBER NOT NULL, /*상영관 번호*/
   sch_starttime CHAR(8) NOT NULL, /* 시작 시간*/
   CONSTRAINT pk_schedule PRIMARY KEY(sch_no),
   CONSTRAINT fk_schedule_mov FOREIGN KEY (mov_no) REFERENCES movie( mov_no),
    CONSTRAINT fk_schedule_scr FOREIGN KEY (sc_no) REFERENCES screen(sc_no)
);

  
  SELECT * FROM 

/* 영화 티켓 */
CREATE TABLE ticket(
 tic_no NUMBER NOT NULL, /* 티켓번호 */
 id NUMBER NOT NULL, /* 회원 아이디*/
 sch_no NUMBER NOT NULL, /*스케줄 번호*/
 tic_seatno VARCHAR2(400) NOT NULL, /*좌석 번호*/
 CONSTRAINT pk_ticket PRIMARY KEY(tic_no),
 CONSTRAINT fk_ticket_user FOREIGN KEY (id) REFERENCES user_info (id),
 CONSTRAINT fk_ticket_sch FOREIGN KEY (sch_no) REFERENCES schedule (sch_no)
 );

 
 SELECT * FROM schedule;





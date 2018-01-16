DROP TABLE user_info_type;
DROP TABLE user_type;
DROP SEQUENCE user_type_seq;
DROP TABLE board;
DROP SEQUENCE board_seq;
DROP TABLE user_info;
DROP SEQUENCE user_info_seq;
DROP TABLE movie CASCADE CONSTRAINTS;
DROP SEQUENCE movie_seq;
DROP TABLE reservation;
DROP SEQUENCE reservation_seq;
DROP TABLE coupon;
DROP SEQUENCE coupon_seq;



CREATE TABLE user_info (
	id NUMBER NOT NULL,
	email VARCHAR2(320) NOT NULL,
	password VARCHAR2(60) NOT NULL,
	name VARCHAR2(20) NOT NULL,
	avatar VARCHAR2(255),
	CONSTRAINT pk_user_info PRIMARY KEY (id),
	CONSTRAINT uk_user_info UNIQUE (email)
);
---------------------------------------------------------------
CREATE SEQUENCE user_info_seq
START WITH 1 INCREMENT BY 1;

CREATE TABLE user_type (
	id NUMBER NOT NULL,
	type VARCHAR2(30) NOT NULL,
	CONSTRAINT pk_user_type PRIMARY KEY (id)
);

CREATE SEQUENCE user_type_seq
START WITH 1 INCREMENT BY 1;

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

CREATE SEQUENCE board_seq
START WITH 1 INCREMENT BY 1;
---------------------------------------------------------------
CREATE TABLE movie (
    mno NUMBER,
    mtitle VARCHAR2(100),
    director VARCHAR2(50),
    mcontent VARCHAR2(4000),
    mposter VARCHAR2(255),
    CONSTRAINT pk_movie PRIMARY KEY (mno)
);

CREATE SEQUENCE movie_seq 
START WITH 1 INCREMENT BY 1;

------------------------------------------------------------------
CREATE TABLE reservation (
	 rno NUMBER,
	 mno NUMBER,
	 mscr VARCHAR2(255),
	 rdate DATE,
	 id NUMBER,
	 mposter VARCHAR2(255),
	 CONSTRAINT pk_reservation PRIMARY KEY (rno),
	 FOREIGN KEY (id) REFERENCES user_info(id),
	 FOREIGN KEY (mno) REFERENCES movie(mno)
);


CREATE SEQUENCE reservation_seq
START WITH 1 INCREMENT BY 1;

------------------------------------------------------------------------

CREATE TABLE coupon (
   cno NUMBER,
   id NUMBER NOT NULL,
   email VARCHAR2(320) NOT NULL UNIQUE,
   sale VARCHAR2(150) NOT NULL,
   CONSTRAINT pk_coupon PRIMARY KEY (cno),
   CONSTRAINT fk_coupon FOREIGN KEY (id) REFERENCES user_info(id)
);

CREATE SEQUENCE coupon_seq
START WITH 1 INCREMENT BY 1;




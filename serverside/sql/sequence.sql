DROP TABLE user_info_type CASCADE CONSTRAINTS;
DROP TABLE user_type CASCADE CONSTRAINTS;
DROP TABLE board CASCADE CONSTRAINTS;
DROP TABLE theater CASCADE CONSTRAINTS;
DROP TABLE user_info CASCADE CONSTRAINTS;
DROP TABLE screen CASCADE CONSTRAINTS;
DROP TABLE movie CASCADE CONSTRAINTS;
DROP TABLE ticket CASCADE CONSTRAINTS;
DROP TABLE coupon CASCADE CONSTRAINTS;
DROP TABLE schedule CASCADE CONSTRAINTS;




DROP SEQUENCE user_type_seq;
DROP SEQUENCE SCHEDULE_seq;
DROP SEQUENCE COUPON_seq;
DROP SEQUENCE MOVIE_seq;
DROP SEQUENCE user_info_seq;
DROP SEQUENCE board_seq;
DROP SEQUENCE theater_seq;
DROP SEQUENCE screen_seq;
DROP SEQUENCE ticket_seq;

CREATE SEQUENCE coupon_seq 
START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE user_info_seq 
START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE user_type_seq 
START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE board_seq 
START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE theater_seq 
START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE screen_seq 
START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE ticket_seq 
START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE schedule_seq 
START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE movie_seq 
START WITH 1 INCREMENT BY 1;



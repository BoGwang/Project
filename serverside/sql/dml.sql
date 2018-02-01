INSERT INTO user_type(id, type)
VALUES (user_type_seq.NEXTVAL, 'ADMIN');

INSERT INTO user_type(id, type)
VALUES (user_type_seq.NEXTVAL, 'USER');

INSERT INTO user_info(id, email, password, name, avatar, cno)
VALUES (user_info_seq.NEXTVAL, 'admin@koitt.com', 
'$2a$10$b.Epv/t3IKT8v7oh4Byx8.1G7WszQRm42a6Nvbpne0o4BsA/4sUOi',
'관리자', NULL, NULL);

INSERT INTO user_info(id, email, password, name, avatar, cno)
VALUES (user_info_seq.NEXTVAL, 'hoon@koitt.com', 
'$2a$10$b.Epv/t3IKT8v7oh4Byx8.1G7WszQRm42a6Nvbpne0o4BsA/4sUOi',
'이상훈', NULL, NULL);


INSERT INTO user_info_type(user_info_id, user_type_id)
VALUES (1, 1);

INSERT INTO user_info_type(user_info_id, user_type_id)
VALUES (2, 2);



---------------------------------
INSERT INTO theater VALUES(theater_seq.NEXTVAL,'가산시네마');
INSERT INTO theater VALUES(theater_seq.NEXTVAL,'용산시네마');
INSERT INTO theater VALUES(theater_seq.NEXTVAL,'신촌시네마');
INSERT INTO theater VALUES(theater_seq.NEXTVAL,'홍대시네마');
INSERT INTO theater VALUES(theater_seq.NEXTVAL,'연신내시네마');


----------------------------------------------------------------------------------------------------------------------------

INSERT INTO movie VALUES(movie_seq.NEXTVAL , '1987','가즈아',NULL,1,'13:00');
INSERT INTO movie VALUES(movie_seq.NEXTVAL, '주문은 토끼입니까?','덕후후후후후후',NULL,2,'15:00');
INSERT INTO movie VALUES(movie_seq.NEXTVAL, '위대한 쇼맨','불가능한 꿈, 그 이상의 쇼!','마이클 그레이시',NULL,,3,'17:00');
INSERT INTO movie VALUES(movie_seq.NEXTVAL, '극장판 포켓몬스터 너로 정했다!','잡히기 싫은데 납치당하는 스토리',NULL,4,'18:00');

---------------------------------------------------------------------------------------------------------------------------------

INSERT INTO schedule VALUES (schedule_seq.NEXTVAL, 1, SYSDATE, '13:00','15:32','가산 시네마');
INSERT INTO schedule VALUES (schedule_seq.NEXTVAL, 2, SYSDATE, '16:00','17:40','용산 시네마');
INSERT INTO schedule VALUES (schedule_seq.NEXTVAL, 3, SYSDATE, '18:00','19:20','신촌 시네마');
INSERT INTO schedule VALUES (schedule_seq.NEXTVAL, 4, SYSDATE, '21:00','23:00','홍대 시네마');

-------------------------------------------------------------------------------------------------------------------
INSERT INTO board VALUES(board_seq.NEXTVAL, 'TITLE1', 'CONTENT-1', 1, SYSDATE, NULL);
INSERT INTO board VALUES(board_seq.NEXTVAL, 'TITLE2', 'CONTENT-2', 1, SYSDATE, NULL);
INSERT INTO board VALUES(board_seq.NEXTVAL, 'TITLE3', 'CONTENT-3', 1, SYSDATE, NULL);
INSERT INTO board VALUES(board_seq.NEXTVAL, 'TITLE4', 'CONTENT-4', 1, SYSDATE, NULL);
INSERT INTO board VALUES(board_seq.NEXTVAL, 'TITLE5', 'CONTENT-5', 1, SYSDATE, NULL);
INSERT INTO board VALUES(board_seq.NEXTVAL, 'TITLE6', 'CONTENT-6', 1, SYSDATE, NULL);
INSERT INTO board VALUES(board_seq.NEXTVAL, 'TITLE7', 'CONTENT-7', 1, SYSDATE, NULL);
INSERT INTO board VALUES(board_seq.NEXTVAL, 'TITLE8', 'CONTENT-8', 1, SYSDATE, NULL);
INSERT INTO board VALUES(board_seq.NEXTVAL, 'TITLE9', 'CONTENT-9', 1, SYSDATE, NULL);
INSERT INTO board VALUES(board_seq.NEXTVAL, 'TITLE10', 'CONTENT-10', 1, SYSDATE, NULL);

-------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/*영화 감독 */

INSERT INTO director VALUES(director_seq.nextval,'장준환');
INSERT INTO director VALUES(director_seq.nextval,'리 언크리치');
INSERT INTO director VALUES(director_seq.nextval,'김용화	');
INSERT INTO director VALUES(director_seq.nextval,'마이클 그레이시');
INSERT INTO director VALUES(director_seq.nextval,'리처드 클라우스');
INSERT INTO director VALUES(director_seq.nextval,'하시모토 히로유키');
INSERT INTO director VALUES(director_seq.nextval,'웨스 볼');




----------------------------------------------------------------------------------------------------------------------------
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL,   '배송 무료 쿠폰');
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, '20% 세일 쿠폰');
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL,  '반값 세일 쿠폰');



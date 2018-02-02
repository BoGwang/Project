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






----------------------------------------------------------------------------------------------------------------------------

INSERT INTO movie VALUES(movie_seq.NEXTVAL , '1987','가즈아','장준환',NULL);
INSERT INTO movie VALUES(movie_seq.NEXTVAL, '코코','뮤지션 꿈꾸는 아이','리 언크리치', NULL);
INSERT INTO movie VALUES(movie_seq.NEXTVAL, '위대한 쇼맨','불가능한 꿈, 그 이상의 쇼!','마이클 그레이시', NULL);
INSERT INTO movie VALUES(movie_seq.NEXTVAL, '그것만이 내 세상','웰터급 동양 챔피언','최성현', NULL);

---------------------------------------------------------------------------------------------------------------------------------


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

INSERT INTO theater VALUES (theater_seq.NEXTVAL, '가산시네마');
INSERT INTO theater VALUES (theater_seq.NEXTVAL, '용산시네마');
INSERT INTO theater VALUES (theater_seq.NEXTVAL, '신촌시네마');
INSERT INTO theater VALUES (theater_seq.NEXTVAL, '연신내시네마');




--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/*상영관 */
INSERT INTO screen VALUES (screen_seq.NEXTVAL, 1, '2D 상영관', 4,8);
INSERT INTO screen VALUES (screen_seq.NEXTVAL, 2, '3D 상영관', 4,8);
INSERT INTO screen VALUES (screen_seq.NEXTVAL, 3, '4DX 상영관', 4,8);
INSERT INTO screen VALUES (screen_seq.NEXTVAL, 4, '2D 상영관', 4,8);



INSERT INTO schedule VALUES (schedule_seq.NEXTVAL, 1, 1, '13:00');
INSERT INTO schedule VALUES (schedule_seq.NEXTVAL, 2, 2, '15:00');
INSERT INTO schedule VALUES (schedule_seq.NEXTVAL, 3, 3, '17:00');
INSERT INTO schedule VALUES (schedule_seq.NEXTVAL, 4, 4, '18:00');






----------------------------------------------------------------------------------------------------------------------------
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL,   '배송 무료 쿠폰');
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, '20% 세일 쿠폰');
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL,  '반값 세일 쿠폰');



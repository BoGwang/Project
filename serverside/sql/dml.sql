INSERT INTO user_type(id, type)
VALUES (user_type_seq.NEXTVAL, 'ADMIN');

INSERT INTO user_type(id, type)
VALUES (user_type_seq.NEXTVAL, 'USER');

INSERT INTO user_info(id, email, password, name, avatar)
VALUES (user_info_seq.NEXTVAL, 'admin@koitt.com', 
'$2a$10$b.Epv/t3IKT8v7oh4Byx8.1G7WszQRm42a6Nvbpne0o4BsA/4sUOi',
'관리자', NULL);

INSERT INTO user_info(id, email, password, name, avatar)
VALUES (user_info_seq.NEXTVAL, 'hoon@koitt.com', 
'$2a$10$b.Epv/t3IKT8v7oh4Byx8.1G7WszQRm42a6Nvbpne0o4BsA/4sUOi',
'이상훈', NULL);

INSERT INTO user_info_type(user_info_id, user_type_id)
VALUES (1, 1);

INSERT INTO user_info_type(user_info_id, user_type_id)
VALUES (1, 2);

INSERT INTO user_info_type(user_info_id, user_type_id)
VALUES (2, 2);

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

---------------------------------------------------------------------------------------------------------------------------------
INSERT INTO movie VALUES(movie_seq.NEXTVAL, '김용화','신과함께-죄와 벌','저승 법에 의하면, 모든 인간은 사후 49일 동안 7번의 재판을 거쳐야만 한다',NULL);
INSERT INTO movie VALUES(movie_seq.NEXTVAL, '양우석	','강철비','북한 쿠데타 발생,북한 1호가 남한으로 내려왔다!',NULL);
INSERT INTO movie VALUES(movie_seq.NEXTVAL, '마이클 그레이시','위대한 쇼맨','불가능한 꿈, 그 이상의 쇼!',NULL);
INSERT INTO movie VALUES(movie_seq.NEXTVAL, '유야마 쿠니히코','극장판 포켓몬스터 너로 정했다!','잡히기 싫은데 납치당하는 스토리',NULL);
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO reservation VALUES(reservation_seq.NEXTVAL, 1, '가산 시네마', SYSDATE, 1, NULL);
INSERT INTO reservation VALUES(reservation_seq.NEXTVAL, 2, '용산 시네마', SYSDATE, 1, NULL);
INSERT INTO reservation VALUES(reservation_seq.NEXTVAL, 3, '신촌 시네마', SYSDATE, 1, NULL);
INSERT INTO reservation VALUES(reservation_seq.NEXTVAL, 4, '불광 시네마', SYSDATE, 1, NULL);
----------------------------------------------------------------------------------------------------------------------------
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 1, 'koitt@koitt.com', '배송 무료 쿠폰');
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 1, 'hoon@naver.com', '20% 세일 쿠폰');
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 1, 's3cret@koitt.com' , '반값 세일 쿠폰');



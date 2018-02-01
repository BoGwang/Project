SELECT * FROM user_info;
SELECT * FROM user_type;
SELECT * FROM user_info_type;
SELECT * FROM board;
SELECT * FROM movie;
SELECT * FROM director;
SELECT * FROM seat;
SELECT * FROM schedule;
SELECT * FROM reservation;
SELECT * FROM coupon;



SELECT user_info_type.user_info_email, user_type.id, user_type.type
FROM user_info_type, user_type
WHERE user_type.id = user_info_type.user_type_id;

SELECT u.email, u.password, u.name, u.avatar, ut.id, ut.type
FROM user_info u,
	(SELECT user_info_type.user_info_email, user_type.id, user_type.type
	FROM user_info_type, user_type
	WHERE user_type.id = user_info_type.user_type_id) ut
WHERE u.email = ut.user_info_email;

SELECT u.email, u.password, u.name, u.avatar, ut.id, ut.type
FROM user_info u,
	(SELECT user_info_type.user_info_email, user_type.id, user_type.type
	FROM user_info_type, user_type
	WHERE user_type.id = user_info_type.user_type_id) ut
WHERE u.email = ut.user_info_email AND u.email = 'admin@koitt.com';

SELECT * FROM user_type WHERE user_type.id = 1;

SELECT * FROM user_info;



SELECT u.id as "UID", u.email, u.password, u.name, u.avatar, ut.id, ut.type
FROM user_info u,
	(SELECT user_info_type.user_info_id, user_type.id, user_type.type
	FROM user_info_type, user_type
	WHERE user_type.id = user_info_type.user_type_id) ut
WHERE u.id = ut.user_info_id;


SELECT u.id as "UID", u.email, u.password, u.name, u.avatar, ut.id, ut.type
FROM user_info u,
	(SELECT user_info_type.user_info_id, user_type.id, user_type.type
	FROM user_info_type, user_type
	WHERE user_type.id = user_info_type.user_type_id) ut
WHERE u.id = ut.user_info_id AND u.id = 1;


SELECT b.no, b.title, b.content, b.id, b.regdate, b.attachment, u.email, u.name, u.avatar
FROM user_info u, board b
WHERE b.id = u.id;

SELECT movie_seq.NEXTVAL mno FROM DUAL;

SELECT user_info_type.user_info_id, user_type.id, user_type.type
	FROM user_info_type, user_type
	WHERE user_type.id = user_info_type.user_type_id;
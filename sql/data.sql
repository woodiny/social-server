INSERT INTO users (email, passwd, login_count, last_login_at) VALUES ('alice.smith@example.com', 'AlicePassword123', 5, '2023-01-01 10:00:00');
INSERT INTO users (email, passwd, login_count, last_login_at) VALUES ('bob.jones@example.com', 'BobSecure456', 3, '2023-01-02 11:00:00');
INSERT INTO users (email, passwd, login_count, last_login_at) VALUES ('carol.white@example.com', 'CarolPwd789', 8, '2023-01-03 12:00:00');
INSERT INTO users (email, passwd, login_count, last_login_at) VALUES ('dave.brown@example.com', 'DavePassword321', 2, '2023-01-04 13:00:00');
INSERT INTO users (email, passwd, login_count, last_login_at) VALUES ('eve.davis@example.com', 'EveSecure654', 10, '2023-01-05 14:00:00');
INSERT INTO users (email, passwd, login_count, last_login_at) VALUES ('frank.miller@example.com', 'FrankPwd987', 4, '2023-01-06 15:00:00');
INSERT INTO users (email, passwd, login_count, last_login_at) VALUES ('grace.wilson@example.com', 'GracePassword132', 7, '2023-01-07 16:00:00');
INSERT INTO users (email, passwd, login_count, last_login_at) VALUES ('hank.moore@example.com', 'HankSecure465', 1, '2023-01-08 17:00:00');
INSERT INTO users (email, passwd, login_count, last_login_at) VALUES ('ivy.taylor@example.com', 'IvyPwd798', 6, '2023-01-09 18:00:00');
INSERT INTO users (email, passwd, login_count, last_login_at) VALUES ('jack.anderson@example.com', 'JackPassword231', 9, '2023-01-10 19:00:00');

INSERT INTO posts (user_seq, contents ,like_count, comment_count, create_at) VALUES (1, 'test01 first post', 1, 1, '2023-01-09 13:10:00');
INSERT INTO posts (user_seq, contents, like_count, comment_count, create_at) VALUES (1, 'test01 second post', 0, 0, '2023-01-09 09:45:00');
INSERT INTO posts (user_seq, contents, like_count, comment_count, create_at) VALUES (1, 'test01 third post', 0, 0, '2023-01-09 19:05:00');
INSERT INTO posts (user_seq, contents, like_count, comment_count, create_at) VALUES (2, 'test02 post', 0, 0, '2023-01-09 15:13:20');

INSERT INTO connections(user_seq, target_seq, granted_at, create_at) VALUES (1, 2, '2023-01-10 18:00:00', '2023-01-09 18:00:00');

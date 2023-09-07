INSERT INTO user (name, email, password) VALUES ('admin', 'admin@email.com', '$2y$10$cU0Xqq/thVsHrmEfSUHLse22pydsKKgEF1n5juSQDeTRnmsTkfgL2');
INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO user_role (user_id, role_id) VALUES (2,2)
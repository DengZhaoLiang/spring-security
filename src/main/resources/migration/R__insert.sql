-- 角色
INSERT INTO `role` (`id`, `name`, `created_at`, `updated_at`)
VALUES (1, 'admin', unix_timestamp(), unix_timestamp());

-- 权限
INSERT INTO `permission` (`id`, `name`, `created_at`, `updated_at`)
VALUES (1, 'all', unix_timestamp(), unix_timestamp());

-- 角色权限
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`, `created_at`, `updated_at`)
VALUES (1, 1, 1, unix_timestamp(), unix_timestamp());

-- 用户
INSERT INTO `userDetail` (`id`, `username`, `password`, `status`, `created_at`, `updated_at`)
VALUES (1, 'Liang', '$2a$10$0otjG.mA0BEGrGlTbB.Kuew7Mny0Ns3b2Q3AGnHtZN0MCXSQrgqtC', 1, unix_timestamp(), unix_timestamp());

-- 用户角色
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `created_at`, `updated_at`)
VALUES (1, 1, 1, unix_timestamp(), unix_timestamp());
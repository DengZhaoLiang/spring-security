-- 用户
CREATE TABLE `userDetail`
(
    `id`            BIGINT(13)          NOT NULL AUTO_INCREMENT,
    `username`      VARCHAR(64)        NOT NULL DEFAULT '' COMMENT '昵称',
    `password`      VARCHAR(64)         NOT NULL DEFAULT '' COMMENT '密码',
    `status`        INT(1)              NOT NULL DEFAULT 1 COMMENT '账号状态 1-启用 0-禁用',
    `created_at`    BIGINT(13)          NOT NULL DEFAULT 0 COMMENT '创建时间',
    `updated_at`    BIGINT(13)          NOT NULL DEFAULT 0 COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

-- 角色
CREATE TABLE `role`
(
    `id`            BIGINT(13)          NOT NULL AUTO_INCREMENT,
    `name`          VARCHAR(64)        NOT NULL DEFAULT '' COMMENT '角色名',
    `created_at`    BIGINT(13)          NOT NULL DEFAULT 0 COMMENT '创建时间',
    `updated_at`    BIGINT(13)          NOT NULL DEFAULT 0 COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

-- 用户角色中间表
CREATE TABLE `user_role`
(
    `id`            BIGINT(13)          NOT NULL AUTO_INCREMENT,
    `user_id`       BIGINT(13)          NOT NULL COMMENT '用户ID',
    `role_id`       BIGINT(13)          NOT NULL COMMENT '角色ID',
    `created_at`    BIGINT(13)          NOT NULL DEFAULT 0 COMMENT '创建时间',
    `updated_at`    BIGINT(13)          NOT NULL DEFAULT 0 COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

-- 权限表
CREATE TABLE `permission`
(
    `id`            BIGINT(13)          NOT NULL AUTO_INCREMENT,
    `name`          VARCHAR(64)        NOT NULL DEFAULT '' COMMENT '权限名',
    `created_at`    BIGINT(13)          NOT NULL DEFAULT 0 COMMENT '创建时间',
    `updated_at`    BIGINT(13)          NOT NULL DEFAULT 0 COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

-- 角色权限中间表
CREATE TABLE `role_permission`
(
    `id`            BIGINT(13)          NOT NULL AUTO_INCREMENT,
    `role_id`       BIGINT(13)          NOT NULL COMMENT '角色ID',
    `permission_id` BIGINT(13)          NOT NULL COMMENT '权限ID',
    `created_at`    BIGINT(13)          NOT NULL DEFAULT 0 COMMENT '创建时间',
    `updated_at`    BIGINT(13)          NOT NULL DEFAULT 0 COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
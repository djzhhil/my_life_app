-- MyLifeApp 数据库表补充设计 SQL
-- 执行前请确保 MySQL 服务已启动

-- ============================================================================
-- 一、user 表补充字段
-- ============================================================================
ALTER TABLE `user` ADD COLUMN `password` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '密码（加密存储）' AFTER `username`,
ADD COLUMN `created_at` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间' AFTER `coin`,
ADD COLUMN `updated_at` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间' AFTER `created_at`,
ADD UNIQUE KEY `uk_username` (`username`),
ADD KEY `idx_level` (`level`),
ADD KEY `idx_exp` (`exp`);

-- ============================================================================
-- 二、task 表补充字段
-- ============================================================================
ALTER TABLE `task` ADD COLUMN `description` VARCHAR(500) DEFAULT NULL COMMENT '任务描述（可选）' AFTER `title`,
ADD COLUMN `created_at` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间' AFTER `status`,
ADD COLUMN `completed_at` DATETIME(3) DEFAULT NULL COMMENT '完成时间' AFTER `created_at`,
ADD COLUMN `updated_at` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间' AFTER `completed_at`,
ADD KEY `idx_user_id` (`user_id`),
ADD KEY `idx_user_id_status` (`user_id`, `status`);

-- ============================================================================
-- 三、验证 SQL
-- ============================================================================
-- DESC `user`;
-- DESC `task`;
-- SHOW INDEX FROM `user`;
-- SHOW INDEX FROM `task`;

-- ============================================================================
-- 回滚 SQL（如需回滚）
-- ============================================================================
-- ALTER TABLE `user`
-- DROP COLUMN `password`,
-- DROP COLUMN `created_at`,
-- DROP COLUMN `updated_at`,
-- DROP KEY `uk_username`,
-- DROP KEY `idx_level`,
-- DROP KEY `idx_exp`;

-- ALTER TABLE `task`
-- DROP COLUMN `description`,
-- DROP COLUMN `created_at`,
-- DROP COLUMN `completed_at`,
-- DROP COLUMN `updated_at`,
-- DROP KEY `idx_user_id`,
-- DROP KEY `idx_user_id_status`;
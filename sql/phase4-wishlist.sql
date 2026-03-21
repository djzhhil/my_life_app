-- ============================================================
-- Phase 4: 心愿单功能 - 数据库设计
-- ============================================================

-- 心愿表
CREATE TABLE `wishlist` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '心愿ID',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `title` VARCHAR(100) NOT NULL COMMENT '心愿标题',
  `description` VARCHAR(500) DEFAULT NULL COMMENT '心愿描述',
  `target_amount` DECIMAL(10, 2) NOT NULL COMMENT '目标金额',
  `current_amount` DECIMAL(10, 2) NOT NULL DEFAULT 0.00 COMMENT '当前金额',
  `icon` VARCHAR(50) DEFAULT NULL COMMENT '图标名称',
  `color` VARCHAR(20) DEFAULT NULL COMMENT '颜色（十六进制）',
  `priority` TINYINT NOT NULL DEFAULT 2 COMMENT '优先级：1-高 2-中 3-低',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态：0-进行中 1-已实现 2-已放弃',
  `target_date` DATE DEFAULT NULL COMMENT '目标日期',
  `created_at` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `updated_at` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  `completed_at` DATETIME(3) DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`id`),
  INDEX `idx_user_id` (`user_id`),
  INDEX `idx_status` (`status`),
  INDEX `idx_priority` (`priority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='心愿表';

-- 心愿存钱记录表
CREATE TABLE `wishlist_deposit` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `wishlist_id` BIGINT NOT NULL COMMENT '心愿ID',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `amount` DECIMAL(10, 2) NOT NULL COMMENT '存入金额',
  `note` VARCHAR(200) DEFAULT NULL COMMENT '备注',
  `created_at` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  PRIMARY KEY (`id`),
  INDEX `idx_wishlist_id` (`wishlist_id`),
  INDEX `idx_user_id` (`user_id`),
  INDEX `idx_created_at` (`created_at`),
  CONSTRAINT `fk_deposit_wishlist` FOREIGN KEY (`wishlist_id`) REFERENCES `wishlist` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='心愿存钱记录表';

CREATE TABLE `category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `name` VARCHAR(50) NOT NULL COMMENT '分类名称',
  `type` TINYINT NOT NULL COMMENT '类型：1-收入 2-支出',
  `icon` VARCHAR(50) DEFAULT NULL COMMENT '图标名称',
  `color` VARCHAR(20) DEFAULT NULL COMMENT '颜色',
  `sort_order` INT NOT NULL DEFAULT 0 COMMENT '排序',
  `created_at` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `updated_at` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`id`),
  INDEX `idx_user_id` (`user_id`),
  INDEX `idx_user_id_type` (`user_id`, `type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分类表';

-- 插入默认分类（支出）
INSERT INTO `category` (`user_id`, `name`, `type`, `icon`, `color`, `sort_order`) VALUES
(1, '餐饮', 2, 'food', '#ff6b6b', 1),
(1, '交通', 2, 'transport', '#4ecdc4', 2),
(1, '购物', 2, 'shopping', '#ffe66d', 3),
(1, '娱乐', 2, 'entertainment', '#95e1d3', 4),
(1, '其他', 2, 'other', '#aa96da', 6);

-- 插入默认分类（收入）
INSERT INTO `category` (`user_id`, `name`, `type`, `icon`, `color`, `sort_order`) VALUES
(1, '工资', 1, 'salary', '#52c41a', 1),
(1, '奖金', 1, 'bonus', '#73d13d', 2),
(1, '其他', 1, 'other', '#b7eb8f', 4);

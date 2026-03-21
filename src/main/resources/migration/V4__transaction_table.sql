CREATE TABLE `transaction` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '交易ID',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `category_id` BIGINT NOT NULL COMMENT '分类ID',
  `type` TINYINT NOT NULL COMMENT '类型：1-收入 2-支出',
  `amount` DECIMAL(10, 2) NOT NULL COMMENT '金额',
  `note` VARCHAR(200) DEFAULT NULL COMMENT '备注',
  `transaction_date` DATE NOT NULL COMMENT '交易日期',
  `created_at` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `updated_at` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`id`),
  INDEX `idx_user_id` (`user_id`),
  INDEX `idx_user_id_type` (`user_id`, `type`),
  INDEX `idx_category_id` (`category_id`),
  CONSTRAINT `fk_transaction_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='交易记录表';

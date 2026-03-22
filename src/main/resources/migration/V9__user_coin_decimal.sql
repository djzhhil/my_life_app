-- ============================================================
-- Phase 5: 用户金币字段类型优化
-- ============================================================

-- 修改用户金币字段为 DECIMAL(10, 2)，支持小数金币
-- 这样可以保持与心愿单金额的类型一致性

ALTER TABLE `user` MODIFY COLUMN `coin` DECIMAL(10, 2) DEFAULT 0.00 COMMENT '金币';

-- 将现有整数值转换为小数（例如 100 → 100.00）
UPDATE `user` SET `coin` = `coin` * 1.00;

-- 添加注释说明金币最小单位是 0.01（1 分）
ALTER TABLE `user` MODIFY COLUMN `coin` DECIMAL(10, 2) DEFAULT 0.00 COMMENT '金币（最小单位：0.01，即1分）';

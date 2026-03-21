-- ============================================================
-- Transaction 表补充字段迁移
-- ============================================================
-- 说明：补充代码中需要但数据库中缺失的字段
-- 创建时间：2026-03-21
-- ============================================================

-- 添加 account（账户）字段
ALTER TABLE `transaction`
ADD COLUMN `account` VARCHAR(50) DEFAULT NULL COMMENT '账户（支付宝/微信/现金等）' AFTER `note`;

-- 添加 description（描述）字段
-- 注：数据库已有 note 字段（备注），但代码中用的是 description
-- 两个字段都保留，description 作为更通用的描述，note 作为详细备注
ALTER TABLE `transaction`
ADD COLUMN `description` VARCHAR(500) DEFAULT NULL COMMENT '描述' AFTER `note`;

-- 添加索引以优化查询
ALTER TABLE `transaction`
ADD INDEX `idx_user_id_account` (`user_id`, `account`);

-- ============================================================
-- 迁移完成
-- ============================================================

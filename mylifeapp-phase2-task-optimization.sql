-- Phase 2: 任务管理优化 - 数据库表扩展
-- 为 task 表添加分类、优先级、截止日期字段

ALTER TABLE `task`
  ADD COLUMN `category` VARCHAR(50) DEFAULT 'general' COMMENT '任务分类：general/study/work/life/health' AFTER `coin_reward`,
  ADD COLUMN `due_date` DATE NULL COMMENT '截止日期' AFTER `description`,
  ADD COLUMN `priority` INT DEFAULT 1 COMMENT '优先级：1-低、2-中、3-高' AFTER `due_date`,
  ADD INDEX `idx_category` (`category`),
  ADD INDEX `idx_priority` (`priority`);

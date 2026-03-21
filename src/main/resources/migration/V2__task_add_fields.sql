ALTER TABLE `task`
  ADD COLUMN `category` TINYINT NOT NULL DEFAULT 1 COMMENT '任务分类：1-学习 2-工作 3-运动 4-生活 5-创意' AFTER `description`,
  ADD COLUMN `priority` TINYINT NOT NULL DEFAULT 2 COMMENT '优先级：1-高 2-中 3-低' AFTER `category`,
  ADD COLUMN `due_date` DATETIME DEFAULT NULL COMMENT '截止日期' AFTER `priority`,
  ADD INDEX `idx_category` (`category`),
  ADD INDEX `idx_priority` (`priority`),
  ADD INDEX `idx_due_date` (`due_date`);

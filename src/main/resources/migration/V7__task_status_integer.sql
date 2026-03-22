-- 修改 task 表的 status 字段类型：VARCHAR(20) -> TINYINT
-- 0 = pending (待完成)
-- 1 = done (已完成)

UPDATE task SET status = '0' WHERE status = 'pending';
UPDATE task SET status = '1' WHERE status = 'done';

ALTER TABLE task
  MODIFY COLUMN status TINYINT NOT NULL DEFAULT 0 COMMENT '状态：0-待完成 1-已完成';

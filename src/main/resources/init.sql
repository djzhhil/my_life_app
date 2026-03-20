-- =========================
-- 创建数据库
-- =========================
CREATE DATABASE IF NOT EXISTS my_life_app
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

USE my_life_app;

-- =========================
-- 用户表（最终结构）
-- =========================
DROP TABLE IF EXISTS user;

CREATE TABLE user (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',

                      username VARCHAR(50) NOT NULL COMMENT '用户名',
                      password VARCHAR(100) NOT NULL DEFAULT '' COMMENT '密码（加密存储）',

                      level INT DEFAULT 1 COMMENT '等级',
                      exp INT DEFAULT 0 COMMENT '经验值',
                      coin INT DEFAULT 0 COMMENT '金币',

                      created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
                      updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',

                      UNIQUE KEY uk_username (username),
                      KEY idx_level (level),
                      KEY idx_exp (exp)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =========================
-- 任务表（最终结构）
-- =========================
DROP TABLE IF EXISTS task;

CREATE TABLE task (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '任务ID',

                      user_id BIGINT NOT NULL COMMENT '用户ID',
                      title VARCHAR(100) NOT NULL COMMENT '任务名称',
                      description VARCHAR(500) DEFAULT NULL COMMENT '任务描述',

                      exp_reward INT DEFAULT 10 COMMENT '经验奖励',
                      coin_reward INT DEFAULT 5 COMMENT '金币奖励',

                      status VARCHAR(20) DEFAULT 'pending' COMMENT '状态：pending/done',

                      created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
                      completed_at DATETIME(3) DEFAULT NULL COMMENT '完成时间',
                      updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',

                      KEY idx_user_id (user_id),
                      KEY idx_user_id_status (user_id, status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =========================
-- 初始化用户
-- =========================
INSERT INTO user (username, password, level, exp, coin)
VALUES ('yao', '123456', 1, 0, 0);

-- =========================
-- 初始化任务
-- =========================
INSERT INTO task (user_id, title, description, exp_reward, coin_reward)
VALUES
    (1, '练琴30分钟', '每天坚持练琴', 20, 10),
    (1, '看书10页', '阅读提升自己', 15, 8),
    (1, '运动15分钟', '保持健康', 10, 5);
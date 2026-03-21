-- ========================================
-- MyLifeApp Phase 3: Accounting/Bookkeeping
-- Database Schema for Categories and Transactions
-- ========================================

-- ========================================
-- 分类表（transaction 表的分类管理）
-- ========================================
CREATE TABLE IF NOT EXISTS `category` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '分类ID',
    name VARCHAR(50) NOT NULL COMMENT '分类名称',
    type VARCHAR(20) NOT NULL COMMENT '类型：income/expense',
    icon VARCHAR(50) DEFAULT NULL COMMENT '图标（可选）',
    color VARCHAR(20) DEFAULT NULL COMMENT '颜色（可选）',
    sort_order INT DEFAULT 0 COMMENT '排序',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    KEY idx_type (type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='交易分类表';

-- 插入默认分类
INSERT INTO `category` (name, type, icon, color) VALUES
-- 支出分类
('餐饮', 'expense', '🍔', '#ff6b6b'),
('交通', 'expense', '🚗', '#4ecdc4'),
('购物', 'expense', '🛒', '#45b7d1'),
('学习', 'expense', '📚', '#96ceb4'),
('娱乐', 'expense', '🎮', '#ffeaa7'),
('生活', 'expense', '🏠', '#fd79a8'),
('健康', 'expense', '💪', '#a29bfe'),
('其他', 'expense', '📝', '#dfe6e9'),
-- 收入分类
('工资', 'income', '💰', '#00b894'),
('奖金', 'income', '🎁', '#00cec9'),
('理财', 'income', '📈', '#6c5ce7'),
('其他', 'income', '💵', '#fdcb6e');

-- ========================================
-- 交易记录表
-- ========================================
CREATE TABLE IF NOT EXISTS `transaction` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '交易ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    type VARCHAR(20) NOT NULL COMMENT '类型：income/expense',
    amount DECIMAL(10, 2) NOT NULL COMMENT '金额',
    category_id BIGINT COMMENT '分类ID',
    category_name VARCHAR(50) COMMENT '分类名称（冗余，方便查询）',
    description VARCHAR(500) DEFAULT NULL COMMENT '备注',
    account VARCHAR(50) DEFAULT 'default' COMMENT '账户：default/wechat/alipay/bank/cash',
    date DATE NOT NULL COMMENT '交易日期',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    KEY idx_user_id (user_id),
    KEY idx_user_id_date (user_id, date),
    KEY idx_type (type),
    KEY idx_category_id (category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='交易记录表';

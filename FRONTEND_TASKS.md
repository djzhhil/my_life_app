# MyLife App 前端开发计划（系统化任务）

**项目位置：** `/root/.openclaw/workspace/my-life-app-frontend`
**技术栈：** UniApp 3.0 + Vue 3 + Vite + TypeScript + Pinia + Sass
**设计风格：** 游戏化、活力、年轻化

---

## 📋 开发策略

### 设计理念

**核心概念："生活 = 游戏"**

- ✅ **任务 = 打怪升级**（EXP + 金币奖励）
- ✅ **记账 = 资源管理**（收入/支出统计）
- ✅ **心愿 = 成就系统**（存钱解锁目标）

**视觉风格：**
- 🎨 主色调：渐变紫（#667eea → #764ba2）
- 🎨 辅助色：活力橙（#ff6b6b）、清爽绿（#51cf66）
- 🎨 卡片设计：圆角、阴影、渐变背景
- 🎨 动效：微动效、过渡动画、加载动画

**游戏化元素：**
- 🏆 等级进度条
- 💎 金币/EXP 数值
- 🎯 任务经验值
- 🌟 心愿进度环
- 🎉 完成任务庆祝动效

---

## 📦 分阶段任务清单

### 🚀 Phase 1：基础框架更新（优先级：🔴 最高）

#### 任务 1.1：更新 API 类型定义
**优先级：** 🔴 最高

**内容：**
1. 更新 `src/types/api.ts`
   - 添加 Task 相关新字段（category, priority, due_date）
   - 添加 Category, Transaction, Wishlist 相关类型
   - 添加枚举类型定义

2. 新增 `src/types/game.ts`
   - 等级系统类型定义
   - 游戏化 UI 组件类型

**预期产出：**
- `src/types/api.ts`（更新）
- `src/types/game.ts`（新建）

**预计时间：** 30 分钟

---

#### 任务 1.2：更新请求配置
**优先级：** 🔴 最高

**内容：**
1. 更新 `src/utils/request.ts`
   - 配置 baseURL（后端 API 地址）
   - 添加 JWT token 自动注入
   - 添加 401 自动重新登录
   - 添加请求/响应拦截器

2. 更新 `src/utils/storage.ts`
   - 添加 token 本地存储
   - 添加用户信息缓存

**预期产出：**
- `src/utils/request.ts`（更新）
- `src/utils/storage.ts`（更新）

**预计时间：** 20 分钟

---

#### 任务 1.3：更新 Store（状态管理）
**优先级：** 🔴 最高

**内容：**
1. 更新 `src/store/user.ts`
   - 添加 token 管理
   - 添加登录/登出逻辑
   - 添加 401 自动登出

2. 新建 `src/store/category.ts`
   - 分类列表管理
   - CRUD 操作

3. 新建 `src/store/transaction.ts`
   - 交易记录管理
   - 统计数据管理

4. 新建 `src/store/wishlist.ts`
   - 心愿单管理
   - 存钱记录管理

**预期产出：**
- `src/store/user.ts`（更新）
- `src/store/category.ts`（新建）
- `src/store/transaction.ts`（新建）
- `src/store/wishlist.ts`（新建）

**预计时间：** 1 小时

---

### 🎨 Phase 2：UI 组件库（优先级：🟠 高）

#### 任务 2.1：游戏化卡片组件
**优先级：** 🟠 高

**内容：**
1. 新建 `src/components/GameCard.vue`
   - 渐变背景
   - 圆角设计
   - 阴影效果
   - 点击动效

2. 新建 `src/components/StatCard.vue`
   - 统计数字展示
   - 趋势图标
   - 对比显示

3. 新建 `src/components/ProgressBar.vue`
   - 渐变进度条
   - 动画效果
   - 文字标签

**预期产出：**
- `src/components/GameCard.vue`
- `src/components/StatCard.vue`
- `src/components/ProgressBar.vue`

**预计时间：** 1.5 小时

---

#### 任务 2.2：游戏化图标和动效
**优先级：** 🟠 高

**内容：**
1. 新建 `src/components/GameIcon.vue`
   - 游戏化图标组件
   - 支持自定义颜色
   - 支持动画效果

2. 新建 `src/components/Confetti.vue`
   - 庆祝动效
   - 完成任务时的彩带效果

**预期产出：**
- `src/components/GameIcon.vue`
- `src/components/Confetti.vue`

**预计时间：** 1 小时

---

### 📝 Phase 3：页面更新与新增（优先级：🟡 中）

#### 任务 3.1：更新任务列表页面
**优先级：** 🟡 中

**内容：**
1. 更新 `src/pages/index/index.vue`
   - 添加分类筛选
   - 添加优先级标识
   - 添加截止日期显示
   - 优化任务卡片样式
   - 添加任务完成动效

2. 更新 `src/components/TaskCard.vue`
   - 添加分类图标
   - 添加优先级标签
   - 添加日期倒计时
   - 美化卡片样式

**预期产出：**
- `src/pages/index/index.vue`（更新）
- `src/components/TaskCard.vue`（更新）

**预计时间：** 2 小时

---

#### 任务 3.2：更新创建任务页面
**优先级：** 🟡 中

**内容：**
1. 更新 `src/pages/create/create.vue`
   - 添加分类选择
   - 添加优先级选择
   - 添加截止日期选择
   - 美化表单样式

**预期产出：**
- `src/pages/create/create.vue`（更新）

**预计时间：** 1.5 小时

---

#### 任务 3.3：新增记账页面
**优先级：** 🟡 中

**内容：**
1. 新建 `src/api/transaction.ts`
   - 交易记录 API
   - 统计 API

2. 新建 `src/pages/accounting/index.vue`
   - 交易列表
   - 收入/支出统计
   - 分类统计（饼图）
   - 日期统计（趋势图）

3. 新建 `src/pages/accounting/add.vue`
   - 记账表单
   - 分类选择
   - 金额输入
   - 账户选择

**预期产出：**
- `src/api/transaction.ts`
- `src/pages/accounting/index.vue`
- `src/pages/accounting/add.vue`

**预计时间：** 3 小时

---

#### 任务 3.4：新增心愿单页面
**优先级：** 🟡 中

**内容：**
1. 新建 `src/api/wishlist.ts`
   - 心愿单 API
   - 存钱记录 API

2. 新建 `src/pages/wishlist/index.vue`
   - 心愿列表
   - 进度展示
   - 状态筛选

3. 新建 `src/pages/wishlist/detail.vue`
   - 心愿详情
   - 存钱记录列表
   - 存钱操作

4. 新建 `src/components/WishlistCard.vue`
   - 心愿卡片
   - 进度环
   - 游戏化设计

5. 新建 `src/components/DepositRecord.vue`
   - 存钱记录
   - 时间线展示

**预期产出：**
- `src/api/wishlist.ts`
- `src/pages/wishlist/index.vue`
- `src/pages/wishlist/detail.vue`
- `src/components/WishlistCard.vue`
- `src/components/DepositRecord.vue`

**预计时间：** 3.5 小时

---

#### 任务 3.5：更新我的页面
**优先级：** 🟡 中

**内容：**
1. 更新 `src/pages/mine/mine.vue`
   - 游戏化个人信息
   - 等级进度条
   - 金币/EXP 展示
   - 成就展示（预留）
   - 设置入口

2. 更新 `src/components/UserInfoCard.vue`
   - 游戏化设计
   - 等级徽章
   - 统计数据

**预期产出：**
- `src/pages/mine/mine.vue`（更新）
- `src/components/UserInfoCard.vue`（更新）

**预计时间：** 2 小时

---

### 🔧 Phase 4：路由和导航（优先级：🟢 低）

#### 任务 4.1：更新页面配置
**优先级：** 🟢 低

**内容：**
1. 更新 `src/pages.json`
   - 添加记账页面路由
   - 添加心愿单页面路由
   - 更新底部 TabBar

**预期产出：**
- `src/pages.json`（更新）

**预计时间：** 30 分钟

---

#### 任务 4.2：导航组件
**优先级：** 🟢 低

**内容：**
1. 新建 `src/components/BottomNav.vue`（可选）
   - 自定义底部导航
   - 动画效果

**预期产出：**
- `src/components/BottomNav.vue`（可选）

**预计时间：** 1 小时

---

### 🎨 Phase 5：视觉优化和动效（优先级：🟢 低）

#### 任务 5.1：全局样式
**优先级：** 🟢 低

**内容：**
1. 新建 `src/styles/variables.scss`
   - 颜色变量
   - 字体变量
   - 间距变量

2. 新建 `src/styles/mixins.scss`
   - 混合样式
   - 渐变背景
   - 阴影效果

3. 新建 `src/styles/animations.scss`
   - 动画定义
   - 过渡效果

**预期产出：**
- `src/styles/variables.scss`
- `src/styles/mixins.scss`
- `src/styles/animations.scss`

**预计时间：** 1.5 小时

---

#### 任务 5.2：页面动效优化
**优先级：** 🟢 低

**内容：**
1. 添加页面切换动画
2. 添加列表加载动画
3. 添加按钮点击动效
4. 添加任务完成庆祝动效

**预期产出：**
- 全局动效优化

**预计时间：** 2 小时

---

## 📊 任务优先级总结

| Phase | 优先级 | 预计时间 | 状态 |
|-------|--------|---------|------|
| Phase 1: 基础框架更新 | 🔴 最高 | 2 小时 | ⏳ 待开始 |
| Phase 2: UI 组件库 | 🟠 高 | 2.5 小时 | ⏳ 待开始 |
| Phase 3: 页面更新与新增 | 🟡 中 | 12 小时 | ⏳ 待开始 |
| Phase 4: 路由和导航 | 🟢 低 | 1.5 小时 | ⏳ 待开始 |
| Phase 5: 视觉优化和动效 | 🟢 低 | 3.5 小时 | ⏳ 待开始 |

**总预计时间：** ~21.5 小时

---

## 🚀 执行建议

### 立即开始
**Phase 1 基础框架更新** - 这是后续开发的基础，必须先完成

### 并行开发
**Phase 2 UI 组件库** 可以与 Phase 3 并行进行

### 迭代优化
**Phase 4 和 Phase 5** 可以在 Phase 3 完成后分批次进行

---

## 📝 设计规范参考

### 颜色方案
```scss
// 主色调
$primary: #667eea;
$primary-gradient: linear-gradient(135deg, #667eea 0%, #764ba2 100%);

// 辅助色
$success: #51cf66;
$warning: #fcc419;
$danger: #ff6b6b;
$info: #339af0;

// 游戏化
$gold: #ffd43b;
$exp: #667eea;
$level: #51cf66;
```

### 间距规范
```scss
$spacing-xs: 8rpx;
$spacing-sm: 12rpx;
$spacing-md: 16rpx;
$spacing-lg: 24rpx;
$spacing-xl: 32rpx;
```

### 圆角规范
```scss
$radius-sm: 8rpx;
$radius-md: 12rpx;
$radius-lg: 16rpx;
$radius-xl: 24rpx;
```

---

**创建时间：** 2026-03-21 21:50
**创建者：** 小克 🐕💎

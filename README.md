# AICOMSOl — AI-Driven COMSOL Multiphysics Modeling Framework

AI 驱动的 COMSOL Multiphysics 建模知识库与协作规范。为 AI 编程助手（如 Claude Code）提供完整的 COMSOL 建模上下文和工作流。

## 设计理念

**不凭记忆，凭证据。** COMSOL API 庞大且易错，AICOMSOl 构建结构化知识库 + 铁律约束，让 AI 在建模前先检索参考、确认语法，而非盲目生成代码。

## 快速开始

### 1. 克隆仓库

```bash
git clone https://github.com/your-username/AICOMSOl.git
cd AICOMSOl
```

### 2. 部署到 AI 编程助手

将 `CLAUDE.md` 作为项目指令部署给 AI 助手（Claude Code 自动读取项目根目录的 CLAUDE.md），AI 将自动：

- 建模前检索 `database/` 知识库
- 写代码时逐行对照 API 参考添加注释
- 报错时按三级排查流程诊断
- 遵循双轨策略选择 Java batch 或 MPh

### 3. 配置 COMSOL 路径

```bash
export COMSOL_INSTALL_DIR="你的COMSOL安装目录"
# 如: D:/Program Files/COMSOL/COMSOL63/Multiphysics
```

## 项目结构

```
AICOMSOl/
├── CLAUDE.md                       ← AI 协作规范（6条铁律 + 双轨策略）
├── README.md                       ← 本文件
├── LICENSE                         ← MIT
├── .gitignore
│
├── database/                       ← 知识库（AI 的信息基础）
│   ├── api_reference/              ← COMSOL Java API 参考
│   │   ├── comsol_api_quickref.txt     按主题速查（597行）
│   │   ├── comsol_api_reference.json   完整方法签名（1470条）
│   │   ├── comsol_api_reference.txt    全文搜索版
│   │   └── comsol_mph_quickref.md      MPh 调试速查
│   ├── experience/                 ← 调试经验卡片
│   │   ├── comsol_debug_reference.*    通用调试手册
│   │   └── domain_cfd_debug.json       CFD 领域专属
│   ├── model_cards.json            ← 官方 1810 张模型卡片
│   └── applications/               ← 49 个官方模块 Java 源码
│
├── docs/                           ← 补充文档
│   └── architecture.md             ← 项目架构详解
│
└── result/                         ← （Git 忽略）建模输出目录
```

## 知识检索

```bash
# JSON 全文搜索（命中结构化字段：名称、描述、物理场、API）
grep -i "LaminarFlow" database/model_cards.json | head -20

# 搜索 API 参考
grep -i "HeatFlux" database/api_reference/comsol_api_reference.txt

# 按模块浏览官方源码
ls database/applications/CFD_Module/

# 搜索调试经验
grep -i "NLfail" database/experience/comsol_debug_reference.txt
```

## 建模途径：双轨互补

| 轨道 | 用途 | 方式 | 迭代速度 |
|------|------|------|----------|
| **Java batch** | 新建模型 | `.java` → javac → comsolbatch | ~30s/轮 |
| **MPh** | 调试已有模型 | Python 加载 `.mph` → 改参 → 求解 | ~5s/轮 |

两条途径底层调用同一个 COMSOL 引擎。新建走 Java batch，调试走 MPh。

## 核心规则（详见 [CLAUDE.md](CLAUDE.md)）

1. **每条语句对照 API 参考并加注释** — 禁止凭记忆写 API
2. **两轮修复失败必须检索网络** — 禁止试错循环
3. **修改已有模型时只改参数** — 禁止"综合优化"已验证代码
4. **复杂模型分步构建** — 几何确认 → 单场验证 → 逐步耦合
5. **求解不收敛按三级排查** — 物理场一致性 → Solver Log → 收敛改善
6. **batch 模式 API 限制** — 10 个不可用 API + 替代方案

## 依赖

- **COMSOL Multiphysics 6.3**（建模运行时，需正版许可）
- **MPh**（可选，调试已有模型时使用）：`pip install mph`

## 作者

**能动李老师**（全网同名）

- 📚 公众号：**通天达灵** — 关注获取更多 COMSOL 多物理场建模知识分享
- 📧 邮箱：Jacobgx@163.com — 欢迎项目合作与技术交流

## 许可

MIT License — 详见 [LICENSE](LICENSE)

---

🤖 知识库提供证据，铁律控制行为，双轨执行建模。Clone → 部署 CLAUDE.md → 即刻开始 AI 辅助建模。

# CLAUDE.md — AICOMSOl 项目规范

## 项目概述

COMSOL 多物理场建模代理项目。双轨互补：Java batch 新建模型，MPh 调试已有模型。

**📚 知识库位置：** `database/`（API 参考 + 经验卡片 + 模型索引 JSON）
- `api_reference/` — COMSOL Java API 参考（4 文件，1470 条方法签名）
- `experience/` — 调试经验与领域知识卡片（通用 + 各领域）
- `model_cards.json` — 官方 1810 张模型卡片（结构化 JSON，可检索）
- `applications/` — 官方模块 Java 源码（49 模块）

## 核心目录

- `database/` — 知识库（模型卡片、官方示例、API 参考、经验卡片）
  - `api_reference/` — COMSOL Java API 参考
    - `comsol_api_quickref.txt` — API 速查（按主题组织的代码模式）
    - `comsol_api_reference.json` — API 完整参考（1470条，JSON）
    - `comsol_api_reference.txt` — API 全文搜索（纯文本）
    - `comsol_mph_quickref.md` — MPh 调试速查（Java ↔ MPh 语法对照 + 调试工作流）
  - `experience/` — 调试经验与领域知识卡片（通用 + 各领域）
    - `comsol_debug_reference.txt` — 通用调试手册（9常见错误 + Solver Log + 三级排查）
    - `comsol_debug_reference.json` — 同上，结构化 JSON
    - `domain_*.json` — 各领域专属卡片（拓扑优化、CFD、电池等）
  - `applications/` — 官方模块参考（49 模块 Java 源码）
  - `model_cards.json` — 官方 1810 张模型卡片（结构化 JSON）
- `result/` — 输出目录（Java 源码、.mph 模型、结果图片）

## COMSOL 安装路径

编译执行时使用 `COMSOL_INSTALL_DIR` 环境变量，未设置时默认 `C:/Program Files/COMSOL/COMSOL63/Multiphysics`。

```bash
export COMSOL_INSTALL_DIR="D:/Program Files/COMSOL/COMSOL63/Multiphysics"
```

## 铁律

### 铁律一：查 API 参考，每行加注释

写任何 `model.xxx().yyy()` 前，必须先查 API 参考确认语法，每行加 `//` 注释说明作用。禁止凭记忆写。

API 参考文件：
- `database/api_reference/comsol_api_quickref.txt` — 按主题速查（597行）
- `database/api_reference/comsol_api_reference.json` — 完整方法签名（1470条）
- `database/api_reference/comsol_mph_quickref.md` — MPh 调试速查

> **Why:** 拼写/类型/路径错一个静默失败，不查参考即盲写。

### 铁律二：两轮修复失败必须检索网络

1. 第一次报错 → 查 `database/` 知识库，修复
2. 第二次仍失败 → **停止改代码** → 查内部资料 → WebSearch COMSOL 官方/论坛 → 总结根因后再动手

禁止连续修改 3 次以上而不查外部资料。

> **Why:** 2 轮后本地知识已穷尽，一次搜索比 10 轮试错快。

### 铁律三：修改已有模型时，只改参数

定位已验证源文件 → 完整复制 → 仅修改用户指定的参数值 → 编译运行。其余一字不改。

禁止「综合多来源代码」「自创公式」「替换求解器结构」。

> **Why:** 曾因拼凑多来源代码连续失败，偏离「只改参数」是根因。

### 铁律四：复杂模型分步构建

禁止一次性写完所有物理场再运行。必须：

1. **确认方案** → 几何布局 + BC 设置，先让用户确认
2. **单物理场起步** → 耦合问题先做主物理场，通过后再叠加
3. **逐步加复杂度** → 域条件 → 边界耦合 → 多物理场耦合
4. **编译前审计** → 按铁律五第一级检查清单自查，确认无误后编译

禁止跳过用户确认、跳过单物理场验证、编译报错后才思考逻辑。

> **Why:** 代码量越大错误定位成本指数增长，增量调试优于全量排错。

### 铁律五：求解不收敛按三级排查

禁止随机试参。按以下顺序逐级排查（每次只改一项）：

**第一级：物理场一致性**（70% 问题在此层）
- 边界条件冲突？（相邻边同时温度+热通量）
- 初始值与 BC 矛盾？（T₀=0 但入口 T=100）
- 材料参数零值/负值？
- 域选择错误？（流体域 ≠ 固体域）
- 变量作用域一致？（ODE 定义的变量引用必须限制在同一域）

**第二级：Solver Log 诊断**
- `NLfail`↑ → ↑牛顿迭代 / ↓阻尼 / 改善初始值
- `LinErr`↑ → 换 Direct 求解器 (PARDISO/MUMPS)
- `Tfail`↑ → ↓初始步长 / 查 BC 一致性

**第三级：收敛改善（逐项试，每次只改一项）**
1. 改善初始值 → 简化模型先解
2. 逐步求解 → 单场先解再耦合
3. 加密梯度区网格
4. 添加人工扩散
5. 手动设变量缩放
6. 转瞬态逼近稳态
7. 参数延拓 ramp
8. 切换求解器 (Segregated↔Fully Coupled, Iterative↔Direct)

禁止不读 Solver Log 直接改参、同时改多个参数、跳过第一级直接调求解器。

> **Why:** 70% 收敛问题源于物理场设置而非求解器，跳级改参是本末倒置。来源：KB 103/964/1262。

### 铁律六：batch 模式下不能用的 API

| 不能用的 | 替代方案 |
|---------|---------|
| `createAutoSequences("all")` | `study().run()` |
| `sol("sol1").runAll()` | `study().run()` |
| `ElectricCurrents` 物理接口 | `CoefficientFormPDE` |
| `HeatFlux` 类型名 | `HeatFluxBoundary` |
| `set("HeatFluxType", ...)` | `set("q0", "h_conv*(T_amb-T)")` |
| `(t>15)*(t<18)` 布尔乘 | `flc1hs()` |
| `global_()` (MPh) | `global()` (Java batch) |
| `ModelUtil.load()` | ❌ 沙箱阻止 |
| `getNElems()`, `getInfo()` 等查询 | ❌ 方法不存在 |
| `set("maxstep", ...)`, `set("atol", ...)` | ❌ API 不暴露 |

### 编译执行命令

```bash
# 编译
"${COMSOL_INSTALL_DIR:-D:/Program Files/COMSOL/COMSOL63/Multiphysics}/java/win64/jre/bin/javac.exe" \
  -encoding UTF-8 \
  -cp "${COMSOL_INSTALL_DIR:-D:/Program Files/COMSOL/COMSOL63/Multiphysics}/plugins/*" \
  -d result/<project>/ result/<project>/<Model>.java

# 执行（batch 模式）
cd <PROJECT_ROOT>
"${COMSOL_INSTALL_DIR:-D:/Program Files/COMSOL/COMSOL63/Multiphysics}/bin/win64/comsolbatch.exe" \
  -np 1 -graphics \
  -inputfile result/<project>/<Model>.class \
  -classpathadd result/<project>/
```

### comsolbatch 限制

- 有文件系统安全沙箱，`ModelUtil.load()` 被阻止
- `createAutoSequences` 和手动 StudyStep 创建可能失败
- .mph 文件需放在项目目录子文件夹内（ASCII 路径）

### 建模第一步：检索模型库

**每次接到建模任务，必须先检索 `database/` 找参考**，严禁从零猜测。

```bash
# JSON 全文搜索（命中结构化字段）
grep -i "LaminarFlow" database/model_cards.json | head -20

# 按模块浏览源码
ls database/applications/CFD_Module/

# 搜索 API 参考
grep -i "HeatFlux" database/api_reference/comsol_api_reference.txt
```

检索后：Read 最匹配的卡片 → Read 源 Java 文件 → 以此为基础修改。

## 常用 API 模式与易错点

### 网格：物理场控制

```java
// ✓ 正确三步：不需要手动建 FreeTri
model.component("comp1").mesh().create("mesh1");
model.component("comp1").mesh("mesh1").autoMeshSize(5);  // 5=常规 (1极细~9极粗)
model.component("comp1").mesh("mesh1").run();
```

### 边界选择：坐标框选

布尔操作后边界编号不可预测，必须用坐标框选：

```java
model.component("comp1").selection().create("sel_inlet", "Box");  // ⚠️ 是 "Box" 不是 "BoxSelection"
model.component("comp1").selection("sel_inlet").set("entitydim", 1);
model.component("comp1").selection("sel_inlet").set("xmin", "-0.5");
// ... xmax, ymin, ymax ...
model.component("comp1").physics("spf").feature("inl1").selection().named("sel_inlet");
```

### 瞬态求解：缓启动

高 Re 流动本质非定常，稳态不会收敛。必须入口缓启动避免初始冲击：

```java
model.component("comp1").physics("spf").feature("inl1").set("p0", "10[Pa]*flc1hs(t-0.15,0.15)");
```

### 压力边界条件

COMSOL 压力值为**表压**（相对 1 atm），非绝对压力：

```java
.feature("inl1").set("p0", "10[Pa]");   // 表压 10Pa
.feature("out1").set("p0", "0[Pa]");    // 大气压
```

## 建模途径：双轨互补

```
新建模型 → Java batch
调试已有模型 → MPh
```

两条途径底层调用同一个 COMSOL 引擎，共享同一套 API 参考。按任务选轨道，不混用。

### 轨道一：Java batch（新建模型）

```
手写 .java → javac 编译 → comsolbatch 执行
```

适用场景：
- 从零创建新模型（几何 + 物理场 + 网格 + 求解）
- 需要完整的模型构建控制
- 铁律三：复制已有 .java 代码，只改参数

### 轨道二：MPh（调试已有模型）

```
Python 加载 .mph → model.java 检查/修改 → model.solve() → model.evaluate()
```

适用场景：
- 加载已有 .mph，不重新编译
- 检查模型状态（边界选择、参数值、求解器配置）
- 迭代调试收敛问题（改参数 → 求解 → 看结果，秒级反馈）
- 参数扫描和灵敏度分析

**MPh 调试的核心优势**：改一个参数后直接 `model.solve()` 看效果，不用走 javac 编译链路。一次调试迭代从「改代码→编译→batch→看报错」的 30 秒缩短到 5 秒。

API 速查：**`database/api_reference/comsol_mph_quickref.md`** — Java ↔ MPh 语法对照 + 调试工作流模板

### 参数覆盖（改参数任务最高效，免代码）

```bash
comsolbatch -inputfile source.mph -outputfile output.mph -pname "w1,w2,Re" -plist "0.5,0.5,150"
```

### 选择原则

| 任务 | 用 | 原因 |
|------|----|------|
| 新建模型 | Java batch | 完整建模控制，铁律已验证 |
| 已有 .mph 改参数后求解 | MPh 或 comsolbatch -pname | 免编译 |
| 调试收敛失败 | MPh | 快速迭代改参→求解→评估 |
| 检查边界选择/物理场配置 | MPh | Python 交互式探针 |
| 参数扫描 | MPh | `for Re in [10,50,100,200]: model.parameter('Re', Re); model.solve()` |
| 从零建几何+布尔操作 | Java batch | MPh 几何 API 不如 Java 成熟 |
| 拓扑优化 | Java batch | 已知 MPh batch 模式矩阵分解失败 |
| 结果数据导出 | MPh | `model.evaluate()` 直接返回 numpy 数组 |

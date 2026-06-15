# COMSOL MPh 调试速查

MPh 调试途径的核心用法——加载已有 .mph → 检查 → 改参数 → 求解 → 评估结果，免编译。

> 来源：MPh 1.3.1 内置文档（`help(mph)`）+ AICOMSOl 项目实战经验

---

## 零、启动

```python
import mph

# 方式 1：mph.start() — 推荐，简洁
client = mph.start(cores=1)              # 限制核心数
client = mph.start()                     # 全部核心

# 方式 2：mph.Client() — 等价
client = mph.Client(cores=1)

# Windows 下用 stand-alone 模式启动更快、API 调用开销更低
mph.option('session', 'stand-alone')     # 在 start() 之前设置
client = mph.start()
```

---

## 一、核心 Pythonic API（MPh 独有的快捷方法）

MPh 的真正优势不是 `model.java`（那和 Java batch 一样），而是这些 Pythonic 封装：

### 模型检查

```python
# 参数
model.parameter('Re')                   # 读，返回字符串表达式如 "500"
model.parameter('Re', '500')            # 写
model.parameter('Re', evaluate=True)    # 评估为数值
model.parameters()                      # dict: {name: expression}
model.parameters(evaluate=True)         # dict: {name: numeric_value}

# 结构列表
model.components()     # ['comp1']
model.geometries()     # ['geom1']
model.physics()        # ['Laminar Flow (spf)', 'Heat Transfer in Solids (ht)']
model.materials()      # ['Water', 'Silicon']
model.meshes()         # ['mesh1']
model.studies()        # ['std1']
model.solutions()      # ['sol1']
model.datasets()       # ['dset1']
model.selections()     # ['sel_inlet', 'sel_outlet']
model.functions()      # ['step1', 'an1']
model.exports()        # ['plot1', 'data1']
model.plots()          # ['pg1']
model.modules()        # 所需许可证模块
```

### 操作

```python
model.solve()              # 求解所有 study
model.solve("std1")        # 指定 study
model.mesh()               # 生成所有网格
model.mesh("mesh1")        # 指定网格
model.build()              # 构建所有几何
model.build("geom1")       # 指定几何
model.clear()              # 清除解/网格/绘图数据（不删模型结构）
model.reset()              # 重置建模历史
model.save()               # 保存到原路径
model.save("debug.mph")    # 另存为
model.save("model.java", format="Java")  # 🆕 导出 Java 源码！
```

### 结果评估

```python
model.evaluate('T')                       # 温度场 → numpy array
model.evaluate('spf.U')                   # 速度场
model.evaluate('T', unit='degC')          # 指定单位
model.evaluate('T', inner='last')         # 瞬态最后一步
model.evaluate('T', inner=[0, 5, 10])     # 指定时间步索引
model.evaluate('T', outer=[0, 1])         # 参数扫描的 outer 解
model.inner(dataset)                      # (indices, time_values)
model.outer(dataset)                      # (indices, param_values)
```

### 属性读写（Pythonic，无需 model.java）

```python
# 读节点属性
model.property('physics/spf', 'U0in')     # 读属性值

# 写节点属性
model.property('physics/spf/inl1', 'U0in', '1[m/s]')

# 读全部属性
model.properties('physics/spf/inl1')      # dict
```

---

## 二、模型树导航（/ 操作符）

MPh 用 `/` 操作符导航模型树，替代 Java 的长链式调用：

```python
# 访问节点
model / 'physics'                          # 物理场节点
model / 'physics' / 'spf'                  # 层流接口
model / 'physics' / 'spf' / 'inl1'         # 入口边界条件
model / 'geometries' / 'geom1'             # 几何序列

# 保存为变量
spf = model / 'physics' / 'spf'
inlet = spf / 'inl1'

# 检查是否存在
if (model / 'physics' / 'ht').exists():
    print("传热接口已配置")
```

### Node 常用方法

```python
node = model / 'physics' / 'spf'

node.name()                   # 'spf'
node.label()                  # 'Laminar Flow'
node.children()               # [Node('inl1'), Node('out1'), ...]
node.parent()                 # Node('physics')
node.exists()                 # True/False
node.is_group()               # 是否为内置组节点
node.run()                    # 执行节点的"运行"操作
node.remove()                 # 从模型树删除
node.rename('new_name')       # 重命名
node.retag('new_tag')         # 重新打 tag

# 属性读写（节点级）
node.property('U0in')         # 读属性
node.property('U0in', '2[m/s]')  # 写属性
node.properties()             # 所有属性 → dict

# 选择
node.selection()              # 返回选择 → Node 或 numpy array
node.select([1, 2, 3])       # 手动选择边界/域
node.select(model/'selections'/'sel_inlet')  # 命名选择
```

---

## 三、模型树可视化（调试利器）

```python
# 查看整个模型树
mph.tree(model)

# 只看物理场分支
mph.tree(model / 'physics')

# 限制深度
mph.tree(model, max_depth=2)
```

输出示例：
```
physics
├── electrostatic
│   ├── Laplace equation
│   ├── zero charge
│   ├── initial values
│   ├── anode
│   └── cathode
└── electric currents
    ├── current conservation
    ├── insulation
    ├── initial values
    ├── anode
    └── cathode
```

---

## 四、模型问题诊断

```python
# 检查整个模型的警告/错误
problems = model.problems()
if problems:
    for p in problems:
        print(f"[{p['category']}] {p['message']}")
        print(f"  Node: {p['node']}, Selection: {p['selection']}")

# 检查特定节点的问题
node = model / 'physics' / 'spf'
node.problems()
```

---

## 五、Java API 探索（mph.inspect）

在交互式调试时，用于探索 Java API 方法列表：

```python
# 查看某个 Java 对象有哪些方法
mph.inspect((model/'studies').java)

# 输出：
# name:    StudyList
# tag:     study
# methods:
#   create
#   remove
#   index
#   ...
```

---

## 六、Java ↔ MPh 语法对照

MPh 通过 `model.java` 直接暴露 COMSOL Java API，语法几乎相同，差异仅在于 Python/Java 类型转换：

```
Java batch                         MPh (Python)
────────────────────────────────────────────────────────────
new String[]{"a", "b"}     →     ["a", "b"]
new int[]{1, 2, 3}          →     [1, 2, 3]
new double[]{0.1, 0.2}      →     [0.1, 0.2]
Bollean.parseBoolean(…)     →     True / False
```

### 新建模型用 Java API（与 Java batch 写法一致）

```python
jm = model.java
comp = jm.component("comp1")

# 几何
comp.geom().create("geom1", 2)
comp.geom("geom1").feature().create("r1", "Rectangle")
comp.geom("geom1").feature("r1").set("size", ["0.1", "0.05"])

# 物理场
comp.physics().create("spf", "LaminarFlow", "geom1")
spf = comp.physics("spf")
spf.feature().create("inl1", "InletBoundary")
spf.feature("inl1").selection().set([1])
spf.feature("inl1").set("U0", "1[m/s]")

# 网格
comp.mesh().create("mesh1")
comp.mesh("mesh1").autoMeshSize(5)
comp.mesh("mesh1").run()
```

---

## 七、调试工作流

### 加载并全量检查模型

```python
import mph
client = mph.start()
model = client.load('result/PackedBed_V3/PackedBed_V3_latest.mph')

# 快速概览
mph.tree(model, max_depth=3)
model.parameters()
model.problems()
```

### 检查边界选择（最常见需求）

```python
# 方法 1：Java API 遍历
jm = model.java
comp = jm.component('comp1')
for p in comp.physics():
    print(f"\n=== {p.tag()} ({p.label()}) ===")
    for feat in p.feature():
        sel = list(feat.selection().entities()) if feat.selection().entities() else []
        print(f"  {feat.tag()}: selection={sel}")

# 方法 2：MPh Node API（更简洁）
for phys_name in model.physics():
    phys = model / 'physics' / phys_name.split(' ')[-1].strip('()')
    for child in phys.children():
        sel = child.selection()
        print(f"{child.name()}: sel={sel}")
```

### 收敛调试迭代

```python
# 改参数 → 求解 → 看结果，秒级反馈
model.parameter('Re', '10')
model.solve()
u = model.evaluate('spf.U')
print(f"Re=10: U range {u.min():.2f}-{u.max():.2f}")

# 参数延拓
for Re in [10, 50, 100, 200, 500]:
    model.parameter('Re', str(Re))
    model.solve()
    T = model.evaluate('T')
    print(f"Re={Re}: T max={T.max():.1f}")
```

### 求解后验证

```python
T = model.evaluate('T')
print(f"T range: {T.min():.2f} - {T.max():.2f} K")

# 导出图像
model.export("pg1", "result.png")
```

---

## 八、已知限制与对策

| 限制 | 说明 | 对策 |
|------|------|------|
| 单 Client | Python 进程只能有一个 `mph.Client()` | `client.clear()` 清模型而非重建 Client |
| 单进程 | Stand-alone 模式下 client 和 backend 同进程，不可被外部连接 | 用 client-server 模式以支持远程 |
| `mph.tree()` 性能 | Client-server 模式下很慢（跨进程遍历） | Stand-alone 模式或无此需求则忽略 |
| batch 沙箱 | MPh 底层也是 Java batch | `ModelUtil.load()` 不可用 |
| 不覆盖 GUI 操作 | 无法操作 Desktop UI | 复杂问题先在 GUI 调通 |
| Image 导出有限 | 不支持直接截图 | `model.export()` 导出数据后 Python 绘图 |

---

## 九、性能优化

```python
# Windows 下 stand-alone 模式：启动更快、API 调用开销更低
mph.option('session', 'stand-alone')
client = mph.start()

# 启用模型缓存：同路径重复加载返回内存中的模型，避免磁盘 I/O
client.caching(True)
```

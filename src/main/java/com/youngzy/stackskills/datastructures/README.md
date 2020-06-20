# 数据机构

## 复杂度

## LinkedList
时间复杂度
- 在末尾添加一个元素：O(N)
- 在头部添加：O(1)
- 查找：O(N)
- 删除第一个元素：O(1)
- 删除任意的一个：O(N)


### LinkedList VS Array
|  |LinkedList|Array|
|---|--- |---|
|大小|不固定|在定义时指定大小，且不可改变|
|插入新元素|简单方便|复杂（复制，交换，移动）
|读取指定元素|遍历整个列表|可以迅速得到
|空间|需要额外的空间存储next指针|只存储数据本身
|缓存|没有|有。更有利于读取

LinkedList 适用场景
1. 有大量插入/删除操作 
2. 元素数量未知

Array 适用场景
1. 快速的随机读取
2. 对空间敏感的

## Stack
时间复杂度：O(1)
空间复杂度：O(N)

## Queue
时间复杂度：O(1)
空间复杂度：O(N)

QueueBuiltWithTwoStacks 时间复杂度：
如果全是一个操作（都是入队或出队）：O(1)
其余的：O(M)，M 为操作次数

## Binary Tree
广度优先：用一个队列实现  
深度优先：递归

## Binary Search Tree/Ordered Binary Tree
left child <= node  
right child > node

应用：插入和查找
插入：O(logN)
查找：O(logN)

## Binary Heap
两种类型：
- Minimum Heap：node <= child
- Maximum Hap: node >= child

H-1(倒数第二层)必须都有左右叶子节点

当成一个数组：  
node index : i  
left child : 2i + 1  
right child : 2i + 2  
parent : (i - 1)/2  

复杂度：  
insertion：O(logN)  
access: O(1)  
remove: O(logN)  

## Graph
Vertex
Edge

types：有向图 无向图

图的三种表现形式：
1 相邻矩阵 Adjacent Matrix
2 相邻列表 list
3 相邻集合 set

 -| Matrix | List | Set|
 |---|---|---|---|
空间| V^2 | E + V | E + V|
X，Y是否有连接 | 1 | degree of V | lg(degree of V)
遍历某个点的边 | V | degree of V | degree of V |

## Minimum Spanning Tree 最小生成树
是图的一部分，包含图的所有顶点，但没有环。  
最小：权重最小的边
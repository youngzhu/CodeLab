# 最短路径问题

## Greedy 贪婪算法
关注小局，把当下做到最优，不考虑以后。

### Dijkstra
复杂度：  
O(E logV)，binary heap  
O(E log V*V), 数组

## Bellman Ford
非贪婪  
适用于有正有负的图

复杂度：
O(E*V): adjacency list
O(V^3): adjacency matrix
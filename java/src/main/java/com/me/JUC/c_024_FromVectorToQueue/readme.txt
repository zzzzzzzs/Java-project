同步容器类
1.Vector HashTable：早期使用synchronized实现
2.ArrayList HashSet： 未考虑多线程安全（未实现同步）
3.HashSet Vs Hash Table Vs StringBuffer
4.Collections.synchronized***工厂方法使用的也是synchronized

使用早期的同步容器以及Collections.synchronized***方法的不足之处，请阅读：
https://blog.csdn.net/itm_hadf/article/details/7506529

使用新的并发容器
https://www.iteye.com/blog/xuganggogo-321630
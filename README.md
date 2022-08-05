# Pureversh / 序列元素后置

这个小游戏是我基于力扣 2022.08.05 的每日一题 [899. 有序队列](https://leetcode.cn/problems/orderly-queue/) 写出的.

游戏内容如下:

1. 给你一个长度为 ```length``` 的整数序列;
2. 里面每个整数范围为 0 ~ ```kind```.
3. 每次可以选择前 ```control``` 个数字, 将其拿出并插入到序列末尾, 其它元素向左补齐;
4. 目的是将这些整数按照升序排列.

游戏中, 难度系数的计算公式为: $$diff = \frac {length \cdot kind}{5 \cdot control}$$

没错, 游戏就这么简单. 但是现在问题是还有许多功能没有实现 ( 如调整难度等 ), 只完成了基础功能 ( 顺便还加了个翻译 ).

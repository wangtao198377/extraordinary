package com.xitao.algorithm.difficulty;

/**
 * 作者：Syangguang
 * 链接：https://www.nowcoder.com/discuss/69405?type=0&order=0&pos=8&page=1
 * 来源：牛客网
 *
 * 两个数组中任一元素大小不超过10000，数组长度在1000以内，
 * 现在按照以下规则将A数组中的元素插入到B数组中进行合并：
 * 对于A数组中任一元素可以插入到B数组中任意位置。
 * 对于A数组中任一元素a(i)，在合并之后的数组中a(i)对应的下标小于a(i+1)对应的下标。（如果a(i+1)存在的话）
 * 问题：
 * 对合并之后的数组中相邻元素两两相乘，然后求其累加值，请给出所有合并可能形成的数组对应累加值的最小值。
 * 以长度为4的两个数组为例：
 * A：{a1，a2，a3，a4}
 * B：{b1，b2，b3，b4}
 * 插入完成后的数组可能为：
 * {a1，a2，b1，b2，b3，b4，a3，a4}、
 * {b1，a1，b2，b3，a2，a3，b4，a4}等。
 * 其对应的累加值分别对应为
 * a1*a2 + b1*b2 + b3*b4 + a3*a4、
 * b1*a1 + b2*b3 + a2*a3 + b4*a4等。
 */

public class DifficultyIssue {

}

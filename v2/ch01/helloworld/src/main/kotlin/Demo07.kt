package com.zhangdapeng

// 定义一个函数，实现2个数相加
fun add(a: Int, b: Int): Int {
    return a + b
}

// 主函数
fun main() {
    // 调用函数，得到结果
    val result = add(1, 2)
    println("1 + 2 = $result")
}
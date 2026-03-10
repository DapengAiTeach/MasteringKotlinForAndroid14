package com.zhangdapeng

fun main(){
    // 整数可以通过 _ 符号来分隔
    val money = 100_0000_0000
    println("今年要赚: $money")

    // 十六进制的数也可以用 _ 符号来分隔
    val hex = 0xFF_FF_FF_FF
    println("十六进制数: $hex")

    // 二进制数也可以用 _ 符号来分隔
    val binary = 0b1010_1010_1010_1010
    println("二进制数: $binary")

    // 浮点数也可以通过 _ 符号来分隔
    val pi = 3.14_15_92_65
    println("圆周率: $pi")

    // 浮点数可以通过科学计数法来表示
    val num = 8.88e8
    println("科学计数法: $num")
}
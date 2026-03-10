package com.zhangdapeng

// 无返回值函数
fun hello1() {
    println("hello world")
}

// 无返回值函数，返回Uint也可以
fun hello2(name: String): Unit {
    println("hello $name")
}

fun main() {
    hello1()
    hello2("张三")
}
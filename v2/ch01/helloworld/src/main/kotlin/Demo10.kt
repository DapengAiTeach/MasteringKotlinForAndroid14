package com.zhangdapeng

// 定义一个打招呼的函数
fun greet(name: String, prefix: String = "您好", suffix: String = "，欢迎来到 Kotlin!"): String{
    return "$prefix $name$suffix"
}

fun main() {
    // 我们可以使用默认参数，只传一个参数
    println(greet("张三"))

    // 也可以覆盖其中的某一个参数
    println(greet("张三", "早上好"))

    // 也可以通过命名参数，覆盖后缀
    println(greet("张三", suffix = "，今天天气不错!"))

    // 也可以使用多个命名参数
    println(greet("张三", prefix = "晚上好", suffix =  "，今天作业做了没？"))
}
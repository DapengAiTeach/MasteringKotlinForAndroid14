package com.zhangdapeng

fun main(){
    // 1、val 只读变量，只能赋值一次，之后不能修改
    val name = "zhangdapeng"

    // 2、var 可变变量，可以多次修改。
    var age = 18
    age = 19

    // 输出结果
    println("name: $name, age: $age")
}

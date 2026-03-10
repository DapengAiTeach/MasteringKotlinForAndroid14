package com.zhangdapeng

fun main(){
    // 显式指定变量的类型
    val name: String = "zhangdapeng"
    val age: Int = 18
    println("name:$name age:$age")

    // 自动类型推断
    val name1 = "zhangdapeng"
    val age1 = 18
    println("name:$name1 age:$age1")
}
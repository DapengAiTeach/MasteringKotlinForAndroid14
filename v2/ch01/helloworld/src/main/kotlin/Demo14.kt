package com.zhangdapeng

fun main(){
    // 直接调用Java类
    val javaList = ArrayList<String>()
    javaList.add("张三")
    javaList.add("李四")
    javaList.add("王五")
    javaList.forEach { println(it) }
}
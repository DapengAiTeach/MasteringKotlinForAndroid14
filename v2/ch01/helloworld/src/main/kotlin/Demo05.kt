package com.zhangdapeng

fun main() {
    println("--- 编码检测 ---")
    println("file.encoding = ${System.getProperty("file.encoding")}")
    println("stdout.encoding = ${System.getProperty("stdout.encoding")}")
    println("sun.stdout.encoding = ${System.getProperty("sun.stdout.encoding")}")
    println("Charset.defaultCharset = ${java.nio.charset.Charset.defaultCharset()}")
    println("--- 测试中文 ---")
    println("字节类型：127")
}
package com.zhangdapeng

// 定义一个学生类
class Student(
    val id: String, // 只读属性，val自动生成getter
    var name: String, // 可读可写属性，var自动生成getter和setter
    var age: Int = 18 // 带默认值的属性，同时也是可读可写属性
) {
    // 初始化代码块，类似于Java的构造代码块
    init {
        // 对属性的值进行校验
        require(age >= 0) { "年龄不能小于0！" }
        println("学生 $name 对象已经创建成功！")
    }
    // 成员函数
    fun introduce(): String {
        return "我是$name，今年 $age 岁了。"
    }
}

fun main() {
    // 创建学生对象
    val student = Student("1001", "张三", 20)
    // 调用成员方法
    println(student.introduce())
    // 修改成员属性
    student.name = "李四"
    student.age = 25
    println(student.introduce())
}
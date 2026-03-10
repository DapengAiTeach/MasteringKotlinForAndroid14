package com.zhangdapeng

// 定义一个商品数据类
data class Product(val id: String, var name: String, var price: Double) {}


fun main() {
    // 创建商品对象
    val p1 = Product("P001", "MacBook Pro", 20000.0)
    val p2 = Product("P001", "MacBook Pro", 20000.0)

    // 1、data数据类会自动生成 equals() 方法
    // 判断两个对象是否相等
    println(p1 == p2)

    // 2、data数据类会自动生成 hashCode() 方法
    // 获取对象的哈希码
    println(p1.hashCode())
    println(p2.hashCode())

    // 3、data数据类会自动生成 toString() 方法
    // 获取对象的字符串表示形式
    println(p1)

    // 4、data数据类会自动生成 copy() 方法
    // 创建新的对象，并复制属性值，可以覆盖某些属性
    val p3 = p1.copy(name = "MacBook Air")
    println(p3)

    // 5、data数据类会自动生成 componentN() 方法
    // 获取对象的属性值，N 表示属性的索引，从 1 开始
    println(p1.component1())
    println(p1.component2())

    // 6、解构赋值
    val (id, name, price) = p1
    println("id: $id, name: $name, price: $price")

    // 7、data数据类会自动生成 getter 和 setter 方法
    println(p1.name)
    p1.name = "MacBook Pro 16"
    println(p1.name)
}
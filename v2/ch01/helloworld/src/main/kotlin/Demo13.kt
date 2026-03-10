package com.zhangdapeng

// 菜谱类
class Recipe(private val name: String){
    // 私有属性，外部不可以直接访问，食材列表
    private val ingredients = mutableListOf<String>()
    // 烹饪步骤
    private val steps = mutableListOf<String>()

    // 添加食材
    fun addIngredient(ingredient: String) {
        ingredients.add(ingredient)
    }

    // 添加步骤
    fun addStep(order:Int, description: String) {
        // 索引是从0开始的，而步骤是从1开始，所以需要减1
        steps.add(order-1, "$order. $description")
    }

    // 获取完整的菜谱信息
    fun getDetails(): String {
        val builder = StringBuilder()
        builder.appendLine("菜名：$name")
        builder.appendLine("食材列表：")
        ingredients.forEach { ingredient ->
            builder.appendLine("- $ingredient")
        }
        builder.appendLine("烹饪步骤：")
        steps.forEachIndexed { index, step ->
            builder.appendLine(step)
        }
        return builder.toString()
    }
}

fun main() {
    // 菜谱：宫保鸡丁
    val gongBaoJiDin = Recipe("宫保鸡丁")
    // 1、添加食材
    gongBaoJiDin.addIngredient("鸡胸肉")
    gongBaoJiDin.addIngredient("花生米")
    gongBaoJiDin.addIngredient("干辣椒")
    gongBaoJiDin.addIngredient("葱姜蒜")
    // 2、添加步骤
    gongBaoJiDin.addStep(1, "鸡肉切丁，加料酒腌制")
    gongBaoJiDin.addStep(2, "热锅凉油，爆香干辣椒")
    gongBaoJiDin.addStep(3, "下鸡丁，滑炒至变色")
    gongBaoJiDin.addStep(4, "加入调好的宫保汁，撒花生米")
    // 3、打印菜谱信息
    println(gongBaoJiDin.getDetails())
}
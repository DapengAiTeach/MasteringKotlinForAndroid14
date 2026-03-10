import java.util.*

class KotlinDemo // 按 alt + enter 可以快速生成属性
    (var name: String?, var age: Int) {
    override fun equals(o: Any?): Boolean {
        if (o == null || javaClass != o.javaClass) return false
        val that = o as KotlinDemo
        return age == that.age && name == that.name
    }

    override fun hashCode(): Int {
        return Objects.hash(name, age)
    }

    override fun toString(): String {
        return "KotlinDemo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}'
    }
}
